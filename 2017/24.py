from aocd import get_data
f = get_data(day=24,year=2017)
parts = [tuple([int(j) for j in i.split("/")]) for i in f.split("\n")]

def part1():
    def findBest(port,available):
        l = []
        for i in available:
            
            if i[0] == port:
                newl = list(available)
                newl.remove(i)
                l += [findBest(i[1],newl) + i[0] + i[1]]
            elif i[1] == port:
                newl = list(available)
                newl.remove(i)
                l += [findBest(i[0],newl) + i[0] + i[1]]

        if l == []:
            return 0
        else:
            return max(l)
    return findBest(0,list(parts))

def part2():
    def findBest(port,available):
        li = []
        for i in available:
            
            if i[0] == port:
                newl = list(available)
                newl.remove(i)
                r = findBest(i[1],newl)
                li += [(r[0] + 1,r[1] + i[0] + i[1])]
            elif i[1] == port:
                newl = list(available)
                newl.remove(i)
                r = findBest(i[0],newl)
                li += [(r[0] + 1,r[1] + i[0] + i[1])]

        if li == []:
            return (0,0)
        else:
            return max(li)
    return findBest(0,list(parts))[1]

print("Part 1:", part1())
print("Part 2:", part2())