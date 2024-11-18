from aocd import get_data
f = get_data(day=3,year=2017)
num = int(f)

def part1():
    close = x if (x := int(-(num ** 0.5//-1))) and x % 2 == 1 else (x := x + 1)
    return (close - 1)//2 + abs((close ** 2 - num) % (close - 1) - (close - 1)//2)

def part2():
    def calc(p):
        s = 0
        for dpi in dp:
            if (p[0] + dpi[0], p[1] + dpi[1]) in d:
                s += d[(p[0] + dpi[0], p[1] + dpi[1])]
        d[(p[0],p[1])] = s
        return s
    
    d = {(0,0):1}
    dir = [(0,-1),(-1,0),(0,1),(1,0)]
    dp = [(-1,-1),(-1,0),(-1,1),(0,-1),(0,1),(1,-1),(1,0),(1,1)]
    p = [0,0]
    dist = 0

    while True:
        for di in dir:
            l = dist if di != (0,-1) else dist - 1
            for _ in range(l):
                p[0] += di[0]
                p[1] += di[1]
                s = calc(p)
                if s > num:
                    return s
        p[0] += 1
        s = calc(p)
        if s > num:
            return s
        dist += 2
        
print("Part 1:", part1())
print("Part 2:", part2())