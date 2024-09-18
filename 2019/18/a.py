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
    allkeys = findkeys(pos,hasGot)
    intList = []
    for i in allkeys:
        temp = list(hasGot)
        temp[ord(i) - ord("a")] = True
        intList += [find(keys[i],tuple(temp)) + allkeys[i]]
    return min(intList)


grid = [list(i) for i in open("text.txt").read().strip().split("\n")]
print(grid)
keys = {}
size = 0
hasGot = {}
for i in range(len(grid)):
    for j in range(len(grid[i])):
        if grid[i][j] == "@":
            grid[i][j] = "."
            pos = (j,i)
        if grid[i][j] != grid[i][j].upper():
            keys[grid[i][j]] = (j,i)
            size += 1
for i in grid:
    print("".join(i))
hasGot = tuple([False] * size)
print(pos,keys,hasGot)
print("Answer:",find(pos,hasGot))