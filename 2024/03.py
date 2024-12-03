from aocd import get_data
f = get_data(day=3,year=2024)
print(f)

def part1():
    t = 0
    for i in range(len(f)):
        if f[i:i+4] == "mul(":
            j = i + 4
            s = ""
            while j < len(f) and f[j] != ")":
                s += f[j]
                j += 1
            print(s)
            if j >= len(f):
                continue
            if len(s.split(",")) != 2:
                continue
            try:
                t += int(s.split(",")[0]) * int(s.split(",")[1])
            except:
                pass
    return t
def part2():
    t = 0
    do = True
    for i in range(len(f)):
        if f[i:i+4] == "do()":
            do = True
        if f[i:i+7] == "don't()":
            do = False
        if do and f[i:i+4] == "mul(":
            j = i + 4
            s = ""
            while j < len(f) and f[j] != ")":
                s += f[j]
                j += 1
            print(s)
            if j >= len(f):
                continue
            if len(s.split(",")) != 2:
                continue
            try:
                t += int(s.split(",")[0]) * int(s.split(",")[1])
            except:
                pass
    return t

print("Part 1:", part1())
print("Part 2:", part2())