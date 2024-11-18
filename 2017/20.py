from aocd import get_data
f = get_data(day=20,year=2017)
#f = open("test.txt").read()
p = [[[int(k) for k in j.split(",")] for j in i[2:].replace(" v","").replace(" a","").replace("<","").replace(">","").split(",=")]for i in f.split("\n")]

def part1():
    for i,iv in enumerate(p):
        s = sum([abs(j) for j in iv[2]])
        if i == 0 or s < m:
            m = s
            ind = i
    return ind

def part2():
    pts = {i:list([list(j) for j in iv]) for i,iv in enumerate(p)}
    for it in range(1000):
        d = {}
        for i,pt in pts.items():
            pt[1][0] += pt[2][0]
            pt[1][1] += pt[2][1]
            pt[1][2] += pt[2][2]
            pt[0][0] += pt[1][0]
            pt[0][1] += pt[1][1]
            pt[0][2] += pt[1][2]
            pos = tuple(pt[0])
            if not pos in d:
                d[pos] = []
            d[pos] += [i]
        for k in d:
            if len(d[k]) > 1:
                for i in d[k]:
                    del pts[i]
    return len(pts)

print("Part 1:", part1())
print("Part 2:", part2())