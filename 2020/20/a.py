tiles = {int(i.split("\n")[0]):i.split("\n")[1:] for i in open("text.txt").read().strip().replace("Tile ","").replace(":","").split("\n\n")}
num = {}
inverse = {}
done = {}
for k,v in tiles.items():
    dirs = [0,0,0,0]
    invdirs = [0,0,0,0]
    for i in range(len(v)):
        for j in range(len(dirs)):
            dirs[j] *= 2
            invdirs[j] *= 2
        if v[0][i] == '#':
            dirs[0] += 1
        if v[0][len(v) - 1 - i] == '#':
            invdirs[0] += 1
        if v[i][len(v) - 1] == '#':
            dirs[1] += 1
        if v[len(v) - 1 - i][len(v)- 1] == '#':
            invdirs[1] += 1
        if v[len(v) - 1][len(v) - 1 - i] == '#':
            dirs[2] += 1
        if v[len(v) - 1][i] == '#':
            invdirs[2] += 1
        if v[len(v) - 1 - i][0] == '#':
            dirs[3] += 1
        if v[i][0] == '#':
            invdirs[3] += 1
    num[k] = dirs
    inverse[k] = invdirs
for k in num:
    print(k)
    print(num[k])
    print(inverse[k])

for k in num:
    for v in inverse[k]:
        if not v in done:
            done[v] = 0
        done[v] += 1
    for v in num[k]:
        if not v in done:
            done[v] = 0
        done[v] += 1
answer = 1
for k in num:
    count = 0
    for v in num[k]:
        if done[v] == 1:
            count += 1
    if(count >= 2):
        print(k,count)
        answer *= k
print("Answer: ",answer)