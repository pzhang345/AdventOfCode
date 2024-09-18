grid = open("text.txt").read().split("\n")
bin = ""
for i in grid:
    for j in i:
        if j == ".":
            bin += "0"
        else:
            bin += "1"
print(bin)
hasDone = set()
while True:
    s = ""
    for i in range(5):
        for j in range(5):
            if bin[i * 5 + j] == "1":
                s += "#"
            else:
                s += "."
        s += "\n"
    print(s)
    score = int(bin,2)
    print(score)
    if score in hasDone:
        break
    hasDone.add(score)

    newbin = ""
    for i in range(len(bin)):
        count = 0
        for j in [-len(grid[0]),-1,1,len(grid[0])]:
            if 0 <= i + j < len(bin) and abs((i % 5) - ((i + j) % 5)) != 4 and bin[i+j] == "1":
                count += 1
        if bin[i] == "1" and count != 1:
            newbin += "0"
        elif bin[i] == "0" and (count == 1 or count == 2): 
            newbin += "1"
        else:
            newbin += bin[i]
    bin = newbin
print("Answer:",int("".join(reversed(bin)),2))