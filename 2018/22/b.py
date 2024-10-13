from collections import deque
[depth], [tx,ty] = [[int(j) for j in i.split(": ")[1].split(",")] for i in open("text.txt").read().split("\n")]

numpos = {}
rtype = {}
def findType(x,y):
    if (not (x,y) in numpos):
        if y == 0:
            numpos[(x,y)] = (depth + 16807 * x) % cmod
        elif x == 0:
            numpos[(x,y)] = (depth + 48271 * y) % cmod
        elif x == tx and y == ty:
            numpos[(x,y)] = (depth) %  cmod
        else:
            numpos[(x,y)] = (findType(x - 1,y) * findType(x,y - 1) + depth) % cmod
        rtype[(x,y)] = (numpos[(x,y)] % mod) % 3
    return numpos[(x,y)]

def find(x,y):
    findType(x,y)
    return rtype[(x,y)]

mod = 20183
smod = 3
cmod = mod * smod
print(depth,tx,ty)
pos = {(0,0):[-1,0,-1]}
d = {0:[(0,0,1)]}

step = 0
done = False
dpos = [(0,-1),(0,1),(-1,0),(1,0)]
while not done:
    if step in d:
        for i in d[step]:
            # print(i)
            cx,cy,idx = i
            if i == (tx,ty,1):
                done = True
                break
            for dx,dy in dpos:
                x = cx + dx
                y = cy + dy
                if x < 0 or y < 0:
                    continue
                
                if idx != find(x,y):
                    if not (x,y) in pos:
                        pos[(x,y)] = [-1,-1,-1]


                    if pos[(x,y)][idx] == -1 or pos[(x,y)][idx] > step + 1:
                        if pos[(x,y)][idx] > step + 1:
                            # print(d)
                            # print(x,y,idx)
                            # print(pos)
                            d[pos[(x,y)][idx]].remove((x,y,idx))

                        pos[(x,y)][idx] = step + 1
                        if not (step + 1) in d:
                            d[step + 1] = []
                        d[step + 1] += [(x,y,idx)]
            # print(pos)
            for j in range(3):
                if j != find(cx,cy):
                    if pos[(cx,cy)][j] == -1:
                        if not (step + 7) in d:
                            d[step + 7] = []
                        d[step + 7] += [(cx,cy,j)]
                        pos[(cx,cy)][j] = step + 7
    print(step)
    if not done:
        step += 1

print("Answer:",step)