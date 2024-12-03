from aocd import get_data
f = get_data(day=1,year=2024)

def part1():
    l1 = []
    l2 = []
    for i in f.split("\n"):
        n = [int(j) for j in i.split("   ")]
        l1 += [n[0]]
        l2 += [n[1]]
    l1.sort()
    l2.sort()
    s = 0
    for i in range(len(l1)):
        s += abs(l1[i]-l2[i])
    return s

def part2():
    l1 = []
    l2 = {}
    
    for i in f.split("\n"):
        n = [int(j) for j in i.split("   ")]
        l1 += [n[0]]
        if not n[1] in l2:
            l2[n[1]] = 0
        l2[n[1]] += 1
    
    s = 0
    for i in l1:
        if i in l2:
            s += l2[i] * i
    return s    

print("Part 1:", part1())
print("Part 2:", part2())