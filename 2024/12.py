from aocd import get_data
from collections import deque
f = get_data(day=12,year=2024)
m = f.split("\n")

def part1():
    def findV(p,s):
        d = set([p])
        q = deque([p])
        a = 0
        p = 0
        dirs = [(0,-1),(1,0),(0,1),(-1,0)]

        while q:
            x,y = q.pop()
            a += 1
            s.remove((x,y))
            for i in dirs:
                nx,ny = x + i[0], y + i[1]
                if not 0 <= nx < len(m[0]) or not 0 <= ny < len(m):
                    p += 1
                    continue
                if (nx,ny) in d:
                    continue
                
                if m[y][x] == m[ny][nx]:
                    q.append((nx,ny))
                    d.add((nx,ny))
                else:
                    p += 1
        return p * a

    nd = set([(i,j) for j in range(len(m[0])) for i in range(len(m))])
    s = 0
    while len(nd) != 0:
        s += findV(list(nd)[0],nd)
    return s

def part2():
    def findV(p,s):
        d = set([p])
        q = deque([p])
        a = 0
        dirs = [(0,-1),(1,0),(0,1),(-1,0)]
        p = {i:[] for i in dirs}
    

        while q:
            x,y = q.pop()
            a += 1
            s.remove((x,y))
            for i in dirs:
                nx,ny = x + i[0], y + i[1]
                if not 0 <= nx < len(m[0]) or not 0 <= ny < len(m):
                    p[i] += [(nx,ny)]
                    continue
                if (nx,ny) in d:
                    continue
                
                if m[y][x] == m[ny][nx]:
                    q.append((nx,ny))
                    d.add((nx,ny))
                else:
                    p[i] += [(nx,ny)]
        
        pm = 0
        for k in p:
            nd = set(p[k])
            while len(nd) != 0:
                q = deque([nd.pop()])
                while q:
                    x,y = q.pop()
                    for i in dirs:
                        nx,ny = x + i[0], y + i[1]
                        if (nx,ny) in nd:
                            q.append((nx,ny))
                            nd.remove((nx,ny))
                pm += 1
        return a * pm

    nd = set([(i,j) for j in range(len(m[0])) for i in range(len(m))])
    s = 0
    while len(nd) != 0:
        s += findV(list(nd)[0],nd)
    return s


print("Part 1:", part1())
print("Part 2:", part2())