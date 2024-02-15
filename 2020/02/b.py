count = 0
for line in open("text.txt"):
    min, max, char, rest = line.strip().replace(": ","-").replace(" ", "-").split("-")
    print(min + " " + max + " " + char + " " + rest)
    con1 = rest[int(min) - 1] == char
    con2 = rest[int(max) - 1] == char
    if((con1 or  con2) and not (con1 and con2)):
        count += 1

print("Answer:",count)
