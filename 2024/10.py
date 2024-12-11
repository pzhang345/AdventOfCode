from aocd import get_data
from collections import deque
f = get_data(day=10,year=2024)

def part1():
    map = [[int(j) for j in i]for i in f.split("\n")]
    
    l = []
    for i,iv in enumerate(map):
        for j,jv in enumerate(iv):
            if(jv == 0):
                l += [(j,i)]
    s = 0
    dirs = [(0,-1),(1,0),(0,1),(-1,0)]
    for i in l:
        q = deque([i])
        done = set([i])
        while q:
            curr = q.pop()
            for dir in dirs:
                x = curr[0] + dir[0]
                y = curr[1] + dir[1]
                if not (0 <= x < len(map[0]) and 0 <= y < len(map)) or (x,y) in done:
                    continue


                if map[y][x] == map[curr[1]][curr[0]] + 1:
                    done.add((x,y))
                    if map[y][x] == 9:
                        s += 1
                    else:
                        q.append((x,y))
    return s



def part2():
    map = [[int(j) for j in i]for i in f.split("\n")]
    
    l = []
    for i,iv in enumerate(map):
        for j,jv in enumerate(iv):
            if(jv == 0):
                l += [(j,i)]
    s = 0
    dirs = [(0,-1),(1,0),(0,1),(-1,0)]
    for i in l:
        q = deque([i])
        while q:
            curr = q.pop()
            for dir in dirs:
                x = curr[0] + dir[0]
                y = curr[1] + dir[1]
                if not (0 <= x < len(map[0]) and 0 <= y < len(map)):
                    continue


                if map[y][x] == map[curr[1]][curr[0]] + 1:
                    if map[y][x] == 9:
                        s += 1
                    else:
                        q.append((x,y))
    return s

print("Part 1:", part1())
print("Part 2:", part2())