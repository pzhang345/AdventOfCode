max = 0
for line in open("text.txt"):
    row = int(line.strip()[:7].replace("F","0").replace("B","1"),2)
    column = int(line.strip()[-3:].replace("L","0").replace("R","1"),2)
    if(row * 8 + column > max):
        max = row * 8 + column
print("Answer:",max)