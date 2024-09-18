sum = 0
for line in open("text.txt"):
    mass = int(line)
    while int(mass/3) - 2 > 0:
        mass = int(mass/3) - 2
        sum += mass
print("Answer:",sum)