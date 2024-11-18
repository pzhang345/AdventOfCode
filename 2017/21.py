from aocd import get_data
f = get_data(day=21,year=2017)
d = {}

for i in f.split("\n"):
    k,re = [tuple(j.split("/")) for j in i.split(" => ")]
    ks = [[] for j in range(8)]
    for y in range(len(k)):
        for j in ks:
            j += [""]
        for x in range(len(k)):
            ks[0][y] += k[y][x]
            ks[1][y] += k[y][-1-x]
            ks[2][y] += k[-1-y][x]
            ks[3][y] += k[-1-y][-1-x]

            ks[4][y] += k[x][y]
            ks[5][y] += k[-1-x][y]
            ks[6][y] += k[x][-1-y]
            ks[7][y] += k[-1-x][-1-y]
    for j in ks:
        d[tuple(j)] = re
sp = ".#.\n..#\n###".split("\n")

def solve(iter):
    p = list(sp)
    for it in range(iter):
        new = []
        if len(p) % 2 == 0:
            i = 2
        else:
            i = 3
        for y in range(len(p)//i):
                new += ["" for j in range(i+1)]
                for x in range(len(p)//i):
                    for k,sv in enumerate(d[tuple([j[i*x:i*x+i] for j in p[i*y:i*y+i]])]):
                        new[(i+1)*y + k] += sv
        p = new
    c = 0
    for y in p:
        for x in y:
            if x == '#':
                c += 1
    return c

def part1():
    return solve(5)
def part2():
    return solve(18)

print("Part 1:", part1())
print("Part 2:", part2())