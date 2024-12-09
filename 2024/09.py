from aocd import get_data
f = get_data(day=9,year=2024)

def part1():
    id = 0
    files = []
    for i in range(0,len(f),2):
        n = int(f[i])
        if(i + 1 != len(f)):
            s = int(f[i+1])
        else:
            s = 0
        files += [id] * n
        files += [" "] * s
        id += 1

    b = len(files) - 1
    fr = 0
    s = 0
    while fr <= b:
        if files[fr] != " ":
            s += files[fr] * fr
            fr += 1
        else:
            if files[b] != " ":
                s += files[b] * fr
                fr += 1
            b -= 1
    return s

def part2():
    files = {}
    keys = []
    spaces = []
    id = 0
    for i in range(0,len(f),2):
        if(i + 1 != len(f)):
            spaces += [int(f[i+1])]
        files[id] = int(f[i])
        keys += [id]
        id += 1
    i = 0
    s = 0
    idx = 0
    while keys != []:
        if i in keys:
            s += (idx * files[i] + ((files[i] - 1) * files[i]//2)) * i
            keys.remove(i)
        
        idx += files[i]
        a = spaces[i]

        for k in range(len(keys) - 1,-1,-1):
            key = keys[k]
            if files[key] <= a:
                a = a - files[key]
                s += (idx * files[key] + ((files[key] - 1) * files[key]//2)) * key
                idx += files[key]
                del keys[k]
        idx += a
        i += 1

    return s


print("Part 1:", part1())
print("Part 2:", part2())