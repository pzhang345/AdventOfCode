from aocd import get_data
f = get_data(day=25,year=2017)
p = [i for i in f.split("\n\n")]
prog = []
for i in range(len(p)):
    if i == 0:
        continue
    lines = [j.strip() for j in p[i].strip().split("\n")]
    prog += [((int(lines[2].split()[-1][0]),lines[3].split()[-1][:-1],lines[4].split()[-1][0]), (int(lines[6].split()[-1][0]),lines[7].split()[-1][:-1],lines[8].split()[-1][0]))]

def part1():
    curr = p[0].split("\n")[0][-2]
    iter = int(p[0].split("\n")[1].split()[-2])
    t = {}
    pos = 0
    dirs = {"left":-1,"right":1}
    for i in range(iter):
        if not pos in t:
            t[pos] = 0
        instr = ord(curr) - ord('A')
        val = t[pos]
        t[pos] = prog[instr][val][0]
        pos += dirs[prog[instr][val][1]]
        curr = prog[instr][val][2]
    c = 0
    for k,v in t.items():
        if v == 1:
            c += 1
    return c

print("Part 1:", part1())