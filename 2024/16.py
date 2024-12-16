from aocd import get_data
from collections import deque
f = get_data(day=16,year=2024)

def part1():
    def checkAndAdd(d,cache,p,score):
        if p in cache and cache[p] <= score:
            return
        
        cache[p] = score

        if not score in d:
            d[score] = []
        d[score] +=  [p]
    
    b = f.split("\n")
    for i in range(len(b)):
        for j in range(len(b[i])):
            if b[i][j] == "S":
                sx,sy = j,i
    
    d = {0:[(sx,sy,0)]}
    cache = {(sx,sy,0):0}

    dirs = [(1,0),(0,1),(-1,0),(0,-1)]

    e = -1
    sc = 0
    while e == -1:
        if not sc in d:
            sc += 1
            continue
        
        for i in d[sc]:
            x,y,dir = i
            if b[y][x] == "E":
                e = sc
                break
            newx,newy = x + dirs[dir][0],y + dirs[dir][1]
            if b[newy][newx] != "#":
                checkAndAdd(d,cache,(newx,newy,dir),sc + 1)
            checkAndAdd(d,cache,(x,y,(dir + 1)%4),sc + 1000)
            checkAndAdd(d,cache,(x,y,(dir - 1)%4),sc + 1000)
        sc += 1
    return e



def part2():
    def checkAndAdd(d,cache,p,score,prev):
        if p in cache:
            if cache[p][0] < score:
                return
            if cache[p][0] == score:
                cache[p][1] += [prev]
                return
        else:
            cache[p] = [score,[prev]]

        if not score in d:
            d[score] = []
        d[score] +=  [p]
    
    b = f.split("\n")
    for i in range(len(b)):
        for j in range(len(b[i])):
            if b[i][j] == "S":
                sx,sy = j,i
    
    d = {0:[(sx,sy,0)]}
    cache = {(sx,sy,0):(0,[])}

    dirs = [(1,0),(0,1),(-1,0),(0,-1)]

    check = []
    sc = 0
    while len(check) == 0:
        if not sc in d:
            sc += 1
            continue
        
        for i in d[sc]:
            x,y,dir = i
            if b[y][x] == "E":
                check += [i]
                continue
            newx,newy = x + dirs[dir][0],y + dirs[dir][1]
            if b[newy][newx] != "#":
                checkAndAdd(d,cache,(newx,newy,dir),sc + 1,i)
            checkAndAdd(d,cache,(x,y,(dir + 1)%4),sc + 1000,i)
            checkAndAdd(d,cache,(x,y,(dir - 1)%4),sc + 1000,i)
        sc += 1
    
    q = deque(check)
    s = set()
    while q:
        curr = q.pop()
        x,y,dir = curr
        s.add((x,y))
        for i in cache[curr][1]:
            q.append(i)
    
    return len(s)


print("Part 1:", part1())
print("Part 2:", part2())