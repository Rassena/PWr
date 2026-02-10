def stringEncryption(text, key):
    cipherText = ""
    cipher = []

    for i in range(len(text)):
        cipher.append(ord(text[i]) - ord('A') + ord(key[i%len(key)])-ord('A'))

    for i in range(len(text)):
        if cipher[i] > 25:
            cipher[i] = cipher[i] - 26
 
    for i in range(len(text)):
        x = cipher[i] + ord('A')
        cipherText += chr(x)
 
    return cipherText
 
 
def stringDecryption(text, key):
 
    plainText = ""
    plain = []
 
    for i in range(len(text)):
        plain.append(ord(text[i]) - ord('A') - (ord(key[i%len(key)]) - ord('A')))
 
    for i in range(len(text)):
        if (plain[i] < 0):
            plain[i] = plain[i] + 26
 
    for i in range(len(text)):
        x = plain[i] + ord('A')
        plainText += chr(x)
 
    return plainText
 
plainText = "kryptografia one time prod jest bardzo niepraktyczna pod wzgledem pisania go na komputerze poniewaz on nie ogarnia zadnych znakow innych niz alfabet lacinski lacznie z spacjami "
key = "krypto"
 
encryptedText = stringEncryption(plainText.upper(), key.upper())
 
print(encryptedText)
print("Message - " + stringDecryption(encryptedText, key.upper()))
