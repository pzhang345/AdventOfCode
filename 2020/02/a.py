count = 0
for line in open("text.txt"):
    min, max, char, rest = line.strip().replace(": ","-").replace(" ", "-").split("-")
    print(min + " " + max + " " + char + " " + rest)
    if(int(min) <= rest.count(char) <= int(max)):
        count += 1

print("Answer:",count)
