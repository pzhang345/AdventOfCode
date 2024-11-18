from aocd import get_data
f = get_data(day=11,year=2017)
moves = f.split(",")
def part1():
    x = 0
    y = 0
    for i in moves:
        if i == "n":
            y -= 1
        elif i == "s":
            y += 1
        elif i == "ne":
            x -= 1
            y -= 0.5
        elif i == "se":
            x -= 1
            y += 0.5
        elif i == "nw":
            x += 1
            y -= 0.5
        elif i == "sw":
            x += 1
            y += 0.5
    x = abs(x)
    y = abs(y)
    if y > x * 0.5:
        return int(x + (y - x * 0.5))
    else:
        return x

def part2():
    x = 0
    y = 0
    max = 0
    for i in moves:
        if i == "n":
            y -= 1
        elif i == "s":
            y += 1
        elif i == "ne":
            x -= 1
            y -= 0.5
        elif i == "se":
            x -= 1
            y += 0.5
        elif i == "nw":
            x += 1
            y -= 0.5
        elif i == "sw":
            x += 1
            y += 0.5

        absx = abs(x)
        absy = abs(y)
        if absy > absx * 0.5:
            dist = int(absx + (absy - absx * 0.5))
        else:
            dist = absx
        if dist > max:
            max = dist
    return max

print("Part 1:", part1())
print("Part 2:", part2())