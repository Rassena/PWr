signal(SIGINT, signal_handler);

if (strcmp(argv[1], "writer") == 0) {
    /* PROCES PISARZA */
    printf("Pisarz: Tworzę FIFO...\n");
    
    /* mkfifo() tworzy plik FIFO */
    if (mkfifo(FIFO_PATH, 0666) == -1 && errno != EEXIST) {
        perror("mkfifo");
        return 1;
    }
    
    /* Otwarcie FIFO do pisania */
    int fd = open(FIFO_PATH, O_WRONLY);
    if (fd == -1) {
        perror("open writer");
        unlink(FIFO_PATH);
        return 1;
    }
    
    printf("Pisarz: Piszę dane...\n");
    
    /* Wysłanie 10 wiadomości */
    for (int i = 0; i < 10 && !interrupted; i++) {
        char buffer[BUFFER_SIZE];
        snprintf(buffer, sizeof(buffer), 
                "Wiadomość %d: PID=%ld\n", i, (long)getpid());
        
        ssize_t bytes_written = write(fd, buffer, strlen(buffer));
        if (bytes_written == -1) {
            perror("write");
            break;
        }
        
        printf("Pisarz: Wysłano %zd bajtów\n", bytes_written);
        sleep(1); /* Opóźnienie między wiadomościami */
    }
    
    close(fd);
    printf("Pisarz: Zakończono pisanie\n");
    unlink(FIFO_PATH); /* Usuń FIFO */
    
} else if (strcmp(argv[1], "reader") == 0) {
    /* PROCES CZYTNIKA */
    printf("Czytnik: Oczekuję na dane...\n");
    
    /* Otwarcie FIFO do czytania (bez blokowania) */
    int fd = open(FIFO_PATH, O_RDONLY | O_NONBLOCK);
    if (fd == -1) {
        perror("open reader");
        return 1;
    }
    
    char buffer[BUFFER_SIZE];
    
    while (!interrupted) {
        ssize_t bytes_read = read(fd, buffer, sizeof(buffer) - 1);
        
        if (bytes_read > 0) {
            /* Odczytano dane */
            buffer[bytes_read] = '\0';
            printf("Czytnik: Odczytano: %s", buffer);
            
        } else if (bytes_read == 0) {
            /* Koniec strumienia (pisarz zamknął FIFO) */
            printf("Czytnik: Koniec strumienia\n");
            break;
            
        } else if (errno == EAGAIN) {
            /* Brak danych dostępnych */
            printf("Czytnik: Brak danych, czekam...\n");
            sleep(1);
            
        } else {
            perror("read");
            break;
        }
    }
    
    close(fd);
    
} else if (strcmp(argv[1], "test") == 0) {
    /* AUTOMATYCZNE TESTY */
    printf("Test: Uruchamiam zestaw testów...\n\n");
    
    printf("=== Test 1: Pisarz bez czytnika ===\n");
    printf("Pisarz będzie oczekiwać na otworzenie FIFO przez czytnika.\n");
    printf("To ilustruje zachowanie blokujące open(O_WRONLY).\n\n");
    
    printf("=== Test 2: Czytnik bez pisarza ===\n");
    printf("Czytnik może być uruchomiony bez pisarza.\n");
    printf("Otrzyma EOF (koniec pliku) natychmiast.\n\n");
    
    printf("=== Test 3: Wiele procesów piszących/czytających ===\n");
    printf("Kilka procesów może czytać z tego samego FIFO.\n");
    printf("Każdy wpis będzie przeczytany przez dokładnie jeden czytnik.\n\n");
    
} else {
    fprintf(stderr, "Nieznany tryb: %s\n", argv[1]);
    return 1;
}

return 0;
