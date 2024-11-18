from aocd import get_data
f = get_data(day=22,year=2017)
# f = open("test.txt").read()
lx,ly = len(f.split("\n")),len(f.split("\n")[0])
sx,sy = lx//2,ly//2
sd = {(p%ly, p//ly):i for p,i in enumerate(f.replace("\n",""))}
dirs = [(0,-1),(1,0),(0,1),(-1,0)]

def part1():
    x,y = sx,sy
    d = dict(sd)
    dir = 0
    c = 0
    for i in range(10000):
        if (x,y) in d and d[(x,y)] == '#':
            dir = (dir + 1)%len(dirs)
            d[(x,y)] = '.'
        else:
            c += 1
            dir = (dir - 1)%len(dirs)
            d[(x,y)] = '#'
        x,y = x + dirs[dir][0], y + dirs[dir][1] 
    return c

def part2():
    x,y = sx,sy
    d = dict(sd)
    dir = 0
    ddir = {'.':-1,'W':0,'#':1,'F':2}
    next = {'.':'W','W':'#','#':'F','F':'.'}
    c = 0
    for i in range(10000000):
        if not (x,y) in d:
            d[(x,y)] = '.'
        ch = d[(x,y)]
        dir = (dir + ddir[ch]) % len(dirs)
        if(next[ch] == '#'):
            c += 1
        d[(x,y)] = next[ch]
        x,y = x + dirs[dir][0], y + dirs[dir][1] 
    return c

print("Part 1:", part1())
print("Part 2:", part2())