lines = open("text.txt").read().split("\n")
hasVisited = [False] * len(lines)
count = 0
acc = 0
while count < len(lines) and not hasVisited[count]:
    hasVisited[count] = True
    cmd, rest = lines[count].split()
    rest = int(rest)
    if cmd == "acc":
        acc += rest
    elif cmd == "jmp":
        count += rest - 1
    count += 1
print("Answer:",acc)