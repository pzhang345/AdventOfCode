from math import lcm
from functools import reduce
pos = [[int(j) for j in i.replace("=",",").replace(">","").split(",")[1::2]] for i in open("text.txt").read().split("\n")]
st = [[pos[j][i] for j in range(4)] for i in range(3)]
pos = [list(i) for i in st]
vel = [[0 for j in range(4)] for i in range(3)]
loop = [0,0,0]
for i,iv in enumerate(pos):
    count = 1
    while loop[i] == 0:
        for j in range(len(iv)):
            for k in range(j + 1,(len(iv))):
                if pos[i][j] > pos[i][k]:
                    vel[i][k] += 1
                    vel[i][j] -= 1
                elif pos[i][j] < pos[i][k]:
                    vel[i][j] += 1
                    vel[i][k] -= 1

        for j in range(len(iv)):
            pos[i][j] += vel[i][j]
        if pos[i] == st[i] and vel[i] == [0,0,0,0]:
            loop[i] = count
            print(count)
        count += 1

print(loop)

print(reduce(lambda a,b: lcm(a,b),loop))