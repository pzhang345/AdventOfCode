from aocd import get_data
f = get_data(day=9,year=2017)

def part1():
    sum = 0
    count = 0
    inG = False
    i = 0
    while i < len(f):
        c = f[i]
        if not inG:
            if c == "{":
                count += 1
            elif c == "}" and count > 0:
                sum += count
                count -= 1
        if c == "!":
            i += 1
        elif c == "<":
            inG = True
        elif c == ">":
            inG = False
        i += 1
    return sum

def part2():
    count = 0
    inG = False
    i = 0
    while i < len(f):
        c = f[i]
        if inG and c != "!" and c != ">":
            count += 1

        if c == "!":
            i += 1
        elif c == "<":
            inG = True
        elif c == ">":
            inG = False

        i += 1
    return count

print("Part 1:", part1())
print("Part 2:", part2())