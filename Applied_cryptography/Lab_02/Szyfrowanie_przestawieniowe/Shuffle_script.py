def stringEncryption(plainText, key):
  table = createTable(len(key), plainText)
  keySequence = getKeySequence(key)

  cipherText = "";
  for num in range(len(keySequence)):
    pos = keySequence.index(num+1)
    for row in range(len(table)):
      if len(table[row]) > pos:
        cipherText += table[row][pos]
  return cipherText


def createTable(szerokosc, plainText):
  row = 0
  col = 0
  table = [[]]
  for _, ch in enumerate(plainText):
    table[row].append(ch)
    col += 1
    if col >= szerokosc:
      col = 0
      row += 1
      table.append([])

  return table


def getKeySequence(key):
  sequence = []
  for pos, ch in enumerate(key):
    previousLetters = key[:pos]
    newNumer = 1
    for prevPos, prevCh in enumerate(previousLetters):
      if prevCh > ch:
        sequence[prevPos] += 1
      else:
        newNumer += 1
    sequence.append(newNumer)
  return sequence



def stringDecryption(cipherText, key):
  table = createDecryptTable(getKeySequence(key), cipherText)

  plainText = "";
  for row in range(len(table)):
    for col in range (len(table[row])):
      plainText += table[row][col]
  return plainText


def createDecryptTable(keySequence, plainText):
  columns = len(keySequence)
  rows = int(len(plainText) / columns)
  if rows * columns < len(plainText):
    rows += 1

  table = createEmptyTable(columns, rows, len(plainText))

  pos = 0
  for num in range(len(keySequence)):
    column = keySequence.index(num+1)

    row = 0
    while (row < len(table)) and (len(table[row]) > column):
      table[row][column] = plainText[pos]
      row += 1
      pos += 1

  return table


def createEmptyTable(columns, rows, plainTextLen):
  table = []
  added = 0
  for row in range(rows):
    table.append([])
    for c in range(columns):
      if added >= plainTextLen:
        return table
      table[row].append('')
      added += 1
  return table


plainText = "kryptografia one time prod jest bardzo niepraktyczna pod wzgledem pisania go na komputerze poniewaz on nie ogarnia zadnych znakow innych niz alfabet lacinski lacznie z spacjami "
key = "krypto"

encryptedText = stringEncryption(plainText.upper(), key.upper())
 
print(encryptedText)
print("Message - " + stringDecryption(encryptedText, key.upper()))



