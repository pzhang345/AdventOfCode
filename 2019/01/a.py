sum = 0
for line in open("text.txt"):
    sum += int(int(line)/3) - 2
print("Answer:",sum)