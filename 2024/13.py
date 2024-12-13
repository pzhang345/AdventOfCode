from aocd import get_data
from z3 import *
f = get_data(day=13,year=2024)

def part1():
    t = f.split("\n\n")
    s = 0
    for i in t:
        a,b,p = i.split("\n")
        a = [int(j) for j in a[12:].split(", Y+")]
        b = [int(j) for j in b[12:].split(", Y+")]
        p = [int(j) for j in p[9:].split(", Y=")]
        c = -1
        for ab in range(100):
            for bb in range(100):
                x = a[0] * ab + b[0] * bb
                y = a[1] * ab + b[1] * bb
                if(p == [x,y] and (c < ab * 3 + bb * 1 or c == -1)):
                    c = ab * 3 + bb * 1
        if c != -1:
            s += c
    return s

def part2():
    t = f.split("\n\n")
    c = 0
    inc = 10000000000000
    for i in t:
        a,b,p = i.split("\n")
        a = [int(j) for j in a[12:].split(", Y+")]
        b = [int(j) for j in b[12:].split(", Y+")]
        p = [int(j) + inc for j in p[9:].split(", Y=")]
        ab = Int('ab')
        bb = Int('bb')
        s = Optimize()
        s.add(a[0] * ab + b[0] * bb == p[0])
        s.add(a[1] * ab + b[1] * bb == p[1])
        s.minimize(3*ab + bb)
        if s.check() == sat:
            m = s.model()
            c += m.evaluate(3*ab+bb).as_long()
    return c

print("Part 1:", part1())
print("Part 2:", part2())