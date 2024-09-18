pos = [[int(j) for j in i.replace("=",",").replace(">","").split(",")[1::2]] for i in open("text.txt").read().split("\n")]
vel = [[0 for j in range(3)] for i in range(4)]
for i in range(1000):
    for j in range(len(pos)):
        for k in range(j + 1,(len(pos))):
            for l in range(3):
                if pos[j][l] > pos[k][l]:
                    vel[k][l] += 1
                    vel[j][l] -= 1
                elif pos[j][l] < pos[k][l]:
                    vel[j][l] += 1
                    vel[k][l] -= 1
    for j in range(len(pos)):
        for k in range(len(pos[j])):
            pos[j][k] += vel[j][k]

ans = 0         
for i in range(len(pos)):
    sp = 0
    sv = 0
    for j in range(len(pos[i])):
        sp += abs(pos[i][j])
        sv += abs(vel[i][j])
    ans += sp * sv
print(ans)