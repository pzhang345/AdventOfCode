def test(input):
    hasVisited = [False] * len(input)
    count = 0
    acc = 0
    while count < len(input) and not hasVisited[count]:
        hasVisited[count] = True
        cmd, rest = input[count].split()
        rest = int(rest)
        if cmd == "acc":
            acc += rest
        elif cmd == "jmp":
            count += rest - 1
        count += 1
    if count == len(input):
        return acc
    return -1
lines = open("text.txt").read().split("\n")
for num,i in enumerate(lines):
    cmd, rest = i.split()
    temp = list(lines)
    result = -1

    if cmd == "nop":
        temp[num] = "jmp " + rest
        result = test(temp)
    elif cmd == "jmp":
        temp[num] = "nop " + rest
        result = test(temp)

    if result != -1:
        break
print("Answer:",result)