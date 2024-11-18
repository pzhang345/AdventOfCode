from aocd import get_data
f = get_data(day=8,year=2017)

def part1():
    lines = f.split("\n")
    var = {}
    for line in lines:
        i = line.split(" ")
        if eval(str(var.get(i[-3],0)) + "".join(i[-2:])):
            if not i[0] in var:
                var[i[0]] = 0
            if i[1] == "inc":
                var[i[0]] += int(i[2])
            elif i[1] == "dec":
                var[i[0]] -= int(i[2])
    return max(list(var.values()))

def part2():
    lines = f.split("\n")
    var = {}
    values = []
    for line in lines:
        i = line.split(" ")
        if eval(str(var.get(i[-3],0)) + "".join(i[-2:])):
            if not i[0] in var:
                var[i[0]] = 0
            if i[1] == "inc":
                var[i[0]] += int(i[2])
            elif i[1] == "dec":
                var[i[0]] -= int(i[2])
            values += [var[i[0]]]
    return max(values)

print("Part 1:", part1())
print("Part 2:", part2())