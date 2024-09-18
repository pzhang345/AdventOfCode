grid = [list(i) for i in open("text.txt").read().split("\n")]
print(grid)
levels = {0:grid}
levelsnums = set([0])
for i in range(200):
    count = {}
    for k,v in levels.items():
        for i in range(len(v)):
            for j in range(len(v[i])):
                for l in [(-1,0),(1,0),(0,-1),(0,1)]:
                    newi = i + l[0]
                    newj = j + l[1]
                    if v[i][j] == "#":
                        if newi == newj == 2:
                            for num in range(len(v)):
                                if i == 1 or j == 1:
                                    key = (k - 1, num * l[1], num * l[0])
                                else:
                                    if l[1] == 0:
                                        key = (k - 1 ,len(v) - 1,num)
                                    else:
                                        key = (k - 1 ,num, len(v) - 1)
                                if not (k-1) in levelsnums:
                                    levelsnums.add(k-1)
                                # print(key,l,i,j)
                                if key not in count:
                                    count[key] = 0
                                count[key] += 1
                        elif 0 <= newi < 5 and 0 <= newj < 5:
                            key = (k,newi,newj)
                            if key not in count:
                                count[key] = 0
                            count[key] += 1
                        else:
                            key = (k + 1,2 + l[0],2 + l[1])
                            if key not in count:
                                count[key] = 0
                            count[key] += 1
                            if not (k+1) in levelsnums:
                                    levelsnums.add(k+1)
    print(levelsnums)
    newlevels = {}
    for i in levelsnums:
        newlevels[i] = [["."] * 5 for j in range(5)]
        for j,jv in enumerate(newlevels[i]):
            for k,kv in enumerate(jv):
                sym = "."
                if i in levels:
                    sym = levels[i][j][k]

                c = 0
                if (i,j,k) in count:
                    c = count[(i,j,k)]

                if sym == "#" and c != 1:
                    newlevels[i][j][k] = "."
                elif sym == "." and (c == 1 or c == 2): 
                    newlevels[i][j][k] = "#"
                else:
                    newlevels[i][j][k] = sym
    # print(newlevels)
    levels = newlevels
ans = 0
for k,v in levels.items():
    for i in v:
        for j in i:
            if j == "#":
                ans += 1
print("Answer:",ans)