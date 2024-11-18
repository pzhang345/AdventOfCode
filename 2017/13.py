from aocd import get_data
f = get_data(day=13,year=2017)
d = {int(j[0]):int(j[1]) for i in f.split("\n") if (j := i.split(": "))}

def part1():
    sum = 0
    for k,v in d.items():
        if k % (v * 2 - 2) == 0:
            sum += k * v
    return sum

def part2():
    curr = 0
    done = False
    while not done:
        done = True
        for k,v in d.items():
            if (k + curr) % (v * 2 - 2) == 0:
                done = False
                break
        if not done:
            curr += 1
    return curr

print("Part 1:", part1())
print("Part 2:", part2())