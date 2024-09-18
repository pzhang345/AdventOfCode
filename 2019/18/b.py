from collections import deque
from functools import cache

def findkeys(pos,unlock):
    hasGone = [[False] * len(grid[0]) for i in range(len(grid))]
    keys = {}
    q = deque()
    q.append((pos,0))
    hasGone[pos[1]][pos[0]] = True
    possible = [[0,-1],[0,1],[-1,0],[1,0]]
    while q:
        (x,y),count = q.popleft()
        #print(x,y)
        for i in possible:
            nx = x + i[1]
            ny = y + i[0]
            if ord("a") <= ord(grid[ny][nx]) <= ord("z") and not unlock[ord(grid[ny][nx]) - ord("a")]:
                keys[grid[ny][nx]] = count + 1
            elif not hasGone[ny][nx] and grid[ny][nx] != "#" and (grid[ny][nx] == "." or unlock[ord(grid[ny][nx].lower()) - ord("a")]):
                hasGone[ny][nx] = True
                q.append(((nx,ny),count + 1))
    return keys

@cache
def find(pos,hasGot):
    if all(hasGot):
        return 0
    allkeys = []
    for i,iv in enumerate(pos):
        allkeys += [findkeys(iv,hasGot)]
    intList = []
    for i,iv in enumerate(allkeys):
        for j in iv:
            temp = list(hasGot)
            temp[ord(j) - ord("a")] = True
            newpos = list(pos)
            newpos[i] = keys[j]
            intList += [find(tuple(newpos),tuple(temp)) + iv[j]]
    return min(intList)


grid = [list(i) for i in open("text.txt").read().strip().split("\n")]
print(grid)
keys = {}
size = 0
hasGot = {}
pos = []
old = [[".",".","."],[".","@","."],[".",".","."]]
mid = [[".","#","."],["#","#","#"],[".","#","."]]
for i in range(len(grid)):
    for j in range(len(grid[i])):
        if grid[i][j] == "@":
            needsChange = True
            for y in range(-1,1):
                for x in range(-1,1):
                    if grid[i + y][j + x] != old[y + 1][x + 1]:
                        needsChange = False
                        break
            if needsChange:
                for y in range(-1,2):
                    for x in range(-1,2):
                        grid[i + y][j + x] = mid[y + 1][x + 1]
                        if x != 0 and y != 0:
                            pos += [(i + y,j + x)]
            else:    
                grid[i][j] = "."
                pos += [(j,i)]
        if grid[i][j] != grid[i][j].upper():
            keys[grid[i][j]] = (j,i)
            size += 1
for i in grid:
    print("".join(i))
pos = tuple(pos)
hasGot = tuple([False] * size)
print(pos,keys,hasGot)
print("Answer:",find(pos,hasGot))