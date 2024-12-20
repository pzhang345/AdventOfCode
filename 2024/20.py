from aocd import get_data
from collections import deque
f = get_data(day=20,year=2024)

def part1():
    b = f.split("\n")
    
    for i,iv in enumerate(b):
        for j,jv in enumerate(iv):
            if jv == "S":
                x,y = j,i
    
    dirs = [(1,0),(0,1),(-1,0),(0,-1)]
    q = deque([(x,y,0)])
    p = {(x,y):0}
    while q:
        curr = q.popleft()
        for i in dirs:
            nx,ny = curr[0] + i[0],curr[1] + i[1]

            if b[ny][nx] == "#" or (nx,ny) in p:
                continue
            
            
            q.append((nx,ny,curr[2] + 1))
            p[(nx,ny)] = curr[2] + 1

            if b[ny][nx] == "E":
                break
    
    c = 0
    for (x,y) in p:
        for i in dirs:
            nx,ny = x + 2 * i[0], y + 2 * i[1]
            if not (nx,ny) in p:
                continue
            if p[(nx,ny)] - (p[(x,y)] + 2) >= 100:
                c += 1
    return c



def part2():
    b = f.split("\n")
    for i,iv in enumerate(b):
        for j,jv in enumerate(iv):
            if jv == "S":
                x,y = j,i
    
    dirs = [(1,0),(0,1),(-1,0),(0,-1)]
    q = deque([(x,y,0)])
    p = {(x,y):0}
    while q:
        curr = q.popleft()
        for i in dirs:
            nx,ny = curr[0] + i[0],curr[1] + i[1]

            if b[ny][nx] == "#" or (nx,ny) in p:
                continue
            
            q.append((nx,ny,curr[2] + 1))
            p[(nx,ny)] = curr[2] + 1

            if b[ny][nx] == "E":
                break

    cl = 20
    c = 0
    for (x,y) in p:
        for dx in range(-cl, cl + 1):
            mj = (cl - abs(dx))
            for dy in range(-mj, mj+1):
                nx,ny =  x + dx, y + dy
                if not (nx,ny) in p:
                    continue
                if p[(nx,ny)] - (p[(x,y)] + abs(dx) + abs(dy)) >= 100:
                    c += 1
    return c

print("Part 1:", part1())
print("Part 2:", part2())