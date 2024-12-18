from aocd import get_data
from collections import deque
f = get_data(day=18,year=2024)

def part1():
    size = 1024
    bounds = [(0,0),(70,70)]
    b = [tuple([int(j) for j in f.split("\n")[i].split(",")]) for i in range(size)]
    
    check = set([(0,0)])
    q = deque([(0,0,0)])
    dirs = [(1,0),(0,1),(-1,0),(0,-1)]
    while q:
        curr = q.popleft()
        for i in dirs:
            newx,newy = curr[0] + i[0],curr[1] + i[1]

            if not (bounds[0][0] <= newx <= bounds[1][0] and bounds[0][1] <= newy <= bounds[1][1]) or (newx,newy) in check or (newx,newy) in b:
                continue
            
            if (newx,newy) == (70,70):
                return curr[2] + 1
            
            q.append((newx,newy,curr[2] + 1))
            check.add((newx,newy))
            
def part2():
    idx = 1024
    bounds = [(0,0),(70,70)]
    lines = f.split("\n")
    b = set([tuple([int(j) for j in lines[i].split(",")]) for i in range(idx)])
    path = set()
    while True:
        new = tuple([int(j) for j in lines[idx].split(",")])
        b.add(new)
        if not new in path and path != set():
            idx += 1
            continue
        check = set([(0,0)])
        q = deque([(0,0)])
        dirs = [(1,0),(0,1),(-1,0),(0,-1)]
        prev = {}
        end = False

        while q:
            curr = q.popleft()
            for i in dirs:
                newx,newy = curr[0] + i[0],curr[1] + i[1]

                if not (bounds[0][0] <= newx <= bounds[1][0] and bounds[0][1] <= newy <= bounds[1][1]) or (newx,newy) in check or (newx,newy) in b:
                    continue
                prev[(newx,newy)] = (curr[0],curr[1])
                if (newx,newy) == (70,70):
                    end = True
                    break
                
                q.append((newx,newy))
                check.add((newx,newy))
            if end:
                break
        
        if not end:
            return lines[idx]
        
        x,y = 70,70
        path = [(70,70)]
        while (x,y) != (0,0):
            x,y = prev[(x,y)]
            path += [(x,y)]
        path = set(path)
        idx += 1

print("Part 1:", part1())
print("Part 2:", part2())