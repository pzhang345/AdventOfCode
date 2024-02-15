count = 0
for line in open("text.txt").read().split("\n\n"):
    list = set(line.replace("\n",""))
    count += len(list)
print("Answer:",count)