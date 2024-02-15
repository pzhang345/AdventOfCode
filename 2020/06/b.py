count = 0
for line in open("text.txt").read().split("\n\n"):
    list = set(line.split("\n")[0])
    for i in line.split("\n"):
        temp = set(i)
        list = list & temp
    count += len(list)
print("Answer:",count)