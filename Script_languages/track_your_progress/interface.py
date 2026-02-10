'''
Simple GUI to display covid data
'''
import os
import tkinter
import kcal_calculator
import add_new_data
import sys
from tkinter import ttk

import yaml_test
import graph_from_yaml

class interface:

    s_about = " Program ma na celu ułatwienia kontroli i podglądu postępów w wybranych \n " \
            "przez użytkownika czynnościach lub ćwiczeniach. \n" \
            "Wybrane zapisane dane mogą być wyświetlone za pomocą wykresu lub listy, która także zawiera komentarza. \n\n" \
            "Program Umożliwia obliczenie BMI i BMR z możliwością zapisania dla danego dnia. \n\n" \
            "Użytkownik także ma mozliwość stworzenia swojej własnej grupy danych. Dane te muszą mieć wartości liczbowe.\n\n" \
            "Ważne jest to żeby upewnić się przy dodawaniu nowych wartości, czy wybrana jest w liście grupa danych do których chce się dodać. \n" \
            "Także to istotne jest przy dodawania poprzez skróty klawiszowe \n\n" \
            "Program zapisuje dane za każdą dodaną nową wartością. Zagrożenie utraty danych jest prawdopodobne jedynie podczas zawieszenia się \n " \
            "prorgramu/systemu podczas zapisywynia (dodania) nowej wartości."

    s_help = "Show elected graph: Otwiera interakcyjny wykres wybranej grupy danych w combobox. \n\n" \
             "Show data list: Wypisuje dane wraz z komentarzami wybranej grupy danych w combobox. \n\n" \
             "Add new Value: Otwiera nowe okno, gdzie jest mozliwość dodania nowej wartości do danej grupy danych. \n\n" \
             "Calculate my kcal: Otwiera kalkulator który umożliwia obliczanie BMI i BMR, a także ma możliwość zapisania 'spalonych' kalorii danego wybranego dnia. \n\n" \
             "Add new group: daje możliwość utworzenia nowej grupy danych do śledzenia i zapisywania przez użytkownika." \

    arr = None
    select_graph =None
    status = None

    def __init__(self):
        window = tkinter.Tk()
        window.title("Graphs")

        def add_new_val():
            self.add_new_val(self)

        def add_new_group():
            self.add_new_group(self)


        def show_list():
            self.show_list(self)


        def show_graph_select():
            self.show_graph_select(self)


        def about():
            self.about(self)

        def help():
            self.help(self)

        def exit():
            self.exit(self)

        #Tworzenie GUI

        self.select_graph = ttk.Combobox(window, state="readonly")
        self.select_graph.grid(column=0, row=0)
        status_frame = tkinter.Frame(window)
        self.status = tkinter.Label(status_frame)
        self.status.pack(fill="both", expand=True)
        self.refresh_list(self)
        self.select_graph.current(0)
        status_frame.grid(row=10, column=0, columnspan=2, sticky="ew")
        self.select_graph.bind("<<ComboboxSelected>>", self.refresh_list)
        self.status.config(text="current selected: {}".format(self.select_graph.get()))
        tkinter.Button(window, text="Add new Value", command=add_new_val).grid(column=0, row=3)
        tkinter.Button(window, text="Add new group", command=add_new_group).grid(column=0, row=5)
        tkinter.Button(window, text="Show data list", command=show_list).grid(column=0, row=2)
        tkinter.Button(window, text="Show selected graph", command=show_graph_select).grid(column=0, row=1)
        tkinter.Button(window, text="Calculate my kcal", command=kcal_calculator.kcal_calculator).grid(column=0, row=4)
        tkinter.Button(window, text="exit", command=exit, fg="red").grid(column=0, row=6)

        self.select_graph.config(value=self.arr)
        menubar = tkinter.Menu(window)
        filemenu = tkinter.Menu(menubar, tearoff=0)
        filemenu.add_command(label="New Group", command=add_new_group,accelerator="Ctrl+N")

        filemenu.add_separator()

        filemenu.add_command(label="Exit", command=exit,accelerator="Ctrl+Q")
        menubar.add_cascade(label="File", menu=filemenu)
        editmenu = tkinter.Menu(menubar, tearoff=0)


        editmenu.add_command(label="Open graph", command=show_graph_select,accelerator="Ctrl+G")
        editmenu.add_command(label="Open list", command=show_list,accelerator="Ctrl+L")
        editmenu.add_command(label="Add value", command=add_new_val,accelerator="Ctrl+V")

        menubar.add_cascade(label="Edit", menu=editmenu)
        helpmenu = tkinter.Menu(menubar, tearoff=0)
        helpmenu.add_command(label="About...", command=about,accelerator="Ctrl+A")
        helpmenu.add_command(label="Help", command=help,accelerator="Ctrl+H")
        menubar.add_cascade(label="Help", menu=helpmenu)

        window.config(menu=menubar)
        window.bind('<Control-q>',self.exit)
        window.bind('<Control-n>',self.add_new_group)
        window.bind('<Control-a>',self.about)
        window.bind('<Control-l>', self.show_list)
        window.bind('<Control-v>', self.add_new_val)
        window.bind('<Control-g>', self.show_graph_select)
        window.bind('<Control-h>', self.help)



        window.mainloop()


    def run(self):
        self.__init__()



    """
    commands for shortcuts and other
    """
    #Upewnienie się co do wyjscia z aktualnego okna
    def exit(self, event):
        exitsure = tkinter.Tk()

        areyousure = tkinter.Label(exitsure, text="Are you sure you want to exit?")
        areyousure.grid(column=0, row=0, columnspan=4)

        ExitYes = tkinter.Button(exitsure, text="Yes", fg="red",command=sys.exit)
        ExitYes.grid(column=0, row=2)

        NoYes = tkinter.Button(exitsure, text="No", command=exitsure.destroy)
        NoYes.grid(column=2, row=2)

    #Wyswietlenie okna zawierającego informacje o programie
    def about(self, event):
        windowAbout = tkinter.Toplevel()
        windowAbout.title('About')
        labelAbout = tkinter.Label(windowAbout, text=self.s_about)
        labelAbout.grid(column=0, row=0, columnspan=3, rowspan=3)

        ok = tkinter.Button(windowAbout, text="Ok", command=windowAbout.destroy)
        ok.grid(column=1, row=10)

    #Wyświetlenie podstawowych informacji o danych funkcjach
    def help(self, event):
        windowHelp = tkinter.Toplevel()
        windowHelp.title('Help')
        labelHelp = tkinter.Label(windowHelp, text=self.s_help)
        labelHelp.grid(column=0, row=0, columnspan=3, rowspan=3)

        ok = tkinter.Button(windowHelp, text="Ok", command=windowHelp.destroy)
        ok.grid(column=1, row=10)

    #Wywołanie funcji dodającej nową wartość do danej grupy danych
    def add_new_val(self, event):
        add_new_data.add_new_data(self.select_graph.get())

    #Funkcja dodająca nowe grupy
    def add_new_group(self, event):
        def new_group():
            if not os.path.isfile("data/{0}.yaml".format(value.get())):
                with open("data/{0}.yaml".format(value.get()), "a") as fo:
                    fo.write("---\n")
                for i in range(len(self.arr)):
                    self.arr[i] = self.arr[i].split('.')[0]
                self.refresh_list(self)
                label_result.config(text='Added {0}'.format(value.get()))
            else:
                label_result.config(text='{0} already exist'.format(value.get()))

        # Upewnienie się co do wyjscia z dodawania noych grup
        def exit_add_group():
            exitsure = tkinter.Toplevel()

            areyousure = tkinter.Label(exitsure, text="Are you sure you want to exit?")
            areyousure.grid(column=0, row=0, columnspan=4)

            ExitYes = tkinter.Button(exitsure, text="Yes", fg="red",
                                     command=lambda: [window_a.destroy(), exitsure.destroy()])
            ExitYes.grid(column=0, row=2)

            NoYes = tkinter.Button(exitsure, text="No", command=exitsure.destroy)
            NoYes.grid(column=2, row=2)

        #GUI do okna do dodawania nowych grup
        window_a = tkinter.Tk()
        window_a.title('New group')
        tkinter.Label(window_a, text="Name of new group").grid(column=0, row=0)
        value = tkinter.Entry(window_a, width=40)
        value.grid(column=1, row=0,columnspan=2)

        tkinter.Button(window_a, text="Add", command=new_group).grid(column=0, row=2)
        tkinter.Button(window_a, text="exit", command=exit_add_group, fg="red").grid(column=1, row=2)

        label_result = tkinter.Label(window_a, text="")
        label_result.grid(column=1, row=1)

    #Wyświetlenie zapisanych danych za pomocą listy w nowym oknie
    def show_list(self, event):

        window_s = tkinter.Tk()
        window_s.title(self.select_graph.get())

        data_from_yaml = yaml_test.get_yaml(self.select_graph.get())

        #Upewnienie się czy dana grupa nie jest pusta
        if data_from_yaml is not None:
            my_list = tkinter.Listbox(window_s)
            my_list.grid(column=0, row=0)

            for key in sorted(data_from_yaml):
                my_list.insert(tkinter.END,"{0}: {1}: {2}; {3}".format(key, self.select_graph.get(),
                                                           data_from_yaml[key].get(self.select_graph.get()),
                                                           data_from_yaml[key].get('comment')))

            my_list.config(width=100)

        else:
            label_result = tkinter.Label(window_s, text="No data to show")
            label_result.grid(column=0, row=0)


        tkinter.Button(window_s, text="exit", command=window_s.destroy, fg="red").grid(column=0, row=1)
        window_s.mainloop()

    #Wyświetlenie zapisanych danych za pomocą wykresu w nowym oknie
    def show_graph_select(self, event):
        graph_from_yaml.graph_from_yaml(self.select_graph.get())


    #Odświeżanie listy aktualnie posiadanych grup danych
    def refresh_list(self,event):

        yaml_test.ensure_dir("data/")
        self.arr = os.listdir("data/")
        for i in range(len(self.arr)):
            self.arr[i] = self.arr[i].split('.')[0]
        self.select_graph.config(value=self.arr)
        self.status.config(text="current selected: {}".format(self.select_graph.get()))