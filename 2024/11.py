from aocd import get_data
f = get_data(day=11,year=2024)


def part1():
    l = f.split(" ")
    for i in range(25):
        nl = []
        for j in l:
            if j == '0':
                nl += ['1']
            elif len(j) % 2 == 0:
                nl += [j[0:len(j)//2],str(int(j[len(j)//2:]))]
            else:
                nl += [str(int(j) * 2024)]
        l = nl
    return len(l)

def part2():
    def add(d,k,v):
        if not k in d:
            d[k] = 0
        d[k] += v
    
    l = f.split(" ")
    d = {}

    for i in l:
        add(d,i,1)
    for i in range(75):
        nd = {}
        for k,v in d.items():
            if k == '0':
                add(nd,'1',v)
            elif len(k) % 2 == 0:
                add(nd,k[0:len(k)//2],v)
                add(nd,str(int(k[len(k)//2:])),v)
            else:
                add(nd,str(int(k) * 2024),v)
        d = nd

    s = 0
    for k,v in d.items():
        s += v
    return s

print("Part 1:", part1())
print("Part 2:", part2())