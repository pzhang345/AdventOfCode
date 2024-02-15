tiles = {int(i.split("\n")[0]):i.split("\n")[1:] for i in open("text.txt").read().strip().replace("Tile ","").replace(":","").split("\n\n")}
num = {}
inverse = {}
eToN = {}
nums = []
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
    nums += [k]

for k in num:
    for i,iv in enumerate(num[k]):
        if not iv in eToN:
            eToN[iv] = []
        eToN[iv] += [(k,0,i)]
    for i,iv in enumerate(inverse[k]):
        if not iv in eToN:
            eToN[iv] = []
        eToN[iv] += [(k,1,i)]
for k in num:
    count = 0
    for v in num[k]:
        if len(eToN[v]) == 1:
            count += 1
        if len(eToN[v]) > 2:
            print(v)
    if(count == 2):
        corner = k
        for i in num[k]:
            print(i,len(eToN[i]))
        break
vals = {}
for k,v in num.items():
    vals[k] = [v]
for k,v in inverse.items():
    vals[k] += [v]
print(vals)
#     0      0i      #..  4,1,6,3   .##  ..#
#    3 1   1i  3i    #..  1,4,3,6   #..  ..#
#     2      2i      .##            #..  ##.
print(corner)
grid = []
ord = [[(0,0),(0,1),(0,2),(0,3)],
       [(1,0),(1,3),(1,2),(1,1)]]
comb = {}
for i,iv in enumerate(ord):
    for j,jv in enumerate(iv):
        nextOrd = iv[(j+1)%4]
        if not (jv,nextOrd) in comb:
            comb[(jv,nextOrd)] = []
        comb[(jv,nextOrd)] += (i,(j + 1) % 4)
print(comb)
for i in range(int(len(num) ** 0.5)):
    grid += [[]]
    for j in range(int(len(num) ** 0.5)):
        if i == j == 0:
            for k in range(len(num[corner])):
                if len(eToN[num[corner][k]]) == 1 and len(eToN[num[corner][(k + 1) % 4]]) == 1:
                    grid[0] += [(corner,0,k + 1)]
                    print(grid,num[corner])
            continue
        print(i,j)
        if j != 0:
            prevl = grid[i][j - 1]
            pos = ord[prevl[1]][(prevl[2] + 1) % 4]
            left = vals[prevl[0]][not pos[0]][pos[1]]
            print(left,vals[prevl[0]])
        else:
            left = -1
        if i != 0:
            prevu = grid[i - 1][j]
            pos = ord[prevu[1]][(prevu[2] + 2) % 4]
            up = vals[prevu[0]][not pos[0]][pos[1]]
            print(up,vals[prevu[0]])
        else:
            up = -1
        print(up,left)
        if left != -1:
            for k in eToN[left]:
                if not prevl[0] == k[0]:
                    thisnum = k[0]
                    print("left:",k,vals[k[0]])
                    pleft = [(k[1],k[2])]
                    print(pleft)
        if up != -1:
            for k in eToN[up]:
                if not prevu[0] == k[0]:
                    thisnum = k[0]
                    print("up:",k,vals[k[0]])
                    pup = [(k[1],k[2])]
                    print(pup)
        if left == -1:
            pleft = []
            for il,iv in enumerate(vals[thisnum]):
                for jl,jv in enumerate(iv):
                    if len(eToN[jv]) == 1:
                        pleft += [(il,jl)]
        if up == -1:
            pup = []
            for iu,iv in enumerate(vals[thisnum]):
                for ju,jv in enumerate(iv):
                    if len(eToN[jv]) == 1:
                        pup += [(iu,ju)]
        for l in pleft:
            for u in pup:
                print(l,u)
                if (l,u) in comb:
                    grid[i] += [(thisnum,comb[(l,u)][0],comb[(l,u)][1])]
    print(grid)
    for g in grid[i]:
        print(vals[g[0]])
        s = ""
        for j in range(4):
            pos = ord[g[1]][(g[2] + j) % 4]
            s  += str(vals[g[0]][pos[0]][pos[1]]) + " "
        print(s)

def alterArr(arr,flip,rot):
    newarr = []
    if(flip):
        for i in arr:
            newarr += [i[::-1]]
    else:
        newarr = list(arr)
    for r in range(rot):
        temp = [[] for a in newarr[0]]
        for i in range(len(newarr[0])):
            for j in range(len(newarr)):
                temp[i] += newarr[j][len(newarr[0]) - i - 1]
            temp[i] = "".join(temp[i])
        newarr = temp
    return newarr

def check(arr,arr2,iin,jin):
    temp = []
    for i, iv in enumerate(arr2):
        for j, jv in enumerate(iv):
            if(jv == "#"):
                if(arr[i + iin][j + jin] == "#"):
                    temp += [(i + iin,j + jin)]
                else:
                    if iin == 2 and jin == 2:
                        print(iin + i, jin + j)
                    return (False,[])
    return (True,temp)
arr = []
for i,iv in enumerate(grid):
    arr += ["" for k in range(len(tiles[iv[0][0]]) - 2)]
    for j,jv in enumerate(iv):
        newarr = [[] for k in range(len(tiles[jv[0]]) - 2)]
        for k,kv in enumerate(tiles[jv[0]]):
            if k == 0 or k == len(tiles[jv[0]]) - 1:
                continue
            newarr[k - 1] = kv[1:-1]
        newarr = alterArr(newarr,jv[1],jv[2])
        for k,kv in enumerate(newarr):
            arr[(len(tiles[jv[0]]) - 2) * i + k] += kv
# arr = alterArr(arr,1,1)
print("\n".join(arr))
sea = [["                  # ", 
        "#    ##    ##    ###",
        " #  #  #  #  #  #   "]]
print("\n".join(sea[0]))
for i in range(2):
    for j in range(4):
        print()
        if i == j == 0:
            continue
        sea += [alterArr(sea[0],i,j)]
        print("\n".join(sea[i * 2 + j]))
print(sea)
hpos = {}
for i,iv in enumerate(arr):
    for j, jv in enumerate(iv):
        if jv == '#':
            hpos[(i,j)] = False


for i,iv in enumerate(arr):
    for j, jv in enumerate(iv):
        for s in sea:
            if not (len(s) + i >= len(arr) or len(s[0]) + j >= len(arr[0])):
                v = check(arr,s,i,j)
                if i == 2 and j == 2:
                    print(v)
                if(v[0]):
                    print(i,j)
                    for k in v[1]:
                        hpos[k] = True
count = 0
#print(hpos)
for k,v in hpos.items():
    if v == False:
        count += 1
print("Answer:",count)
