from collections import deque
maze = [list(i) for i in open("text.txt").read().split("\n")]
print(maze)
letters = {}

for i,iv in enumerate(maze):
    for j,jv in enumerate(iv):
        if (i == len(maze) - 1 or j == len(iv) - 1) and ord("A") <= ord(jv) <=  ord("Z"):
            maze[i][j] = "#"
        elif ord("A") <= ord(jv) <=  ord("Z"):
            if ord("A") <= ord(maze[i + 1][j]) <=  ord("Z"):
                if not jv + maze[i + 1][j] in letters:
                    letters[jv + maze[i + 1][j]] = []

                if maze[i - 1][j] == ".":
                    letters[jv + maze[i + 1][j]] += [(j,i - 1)]
                elif maze[i + 2][j] == ".":
                    letters[jv + maze[i + 1][j]] += [(j,i + 2)]

            elif ord("A") <= ord(maze[i][j + 1]) <=  ord("Z"):
                if not jv + maze[i][j + 1] in letters:
                        letters[jv + maze[i][j + 1]] = []

                if maze[i][j - 1] == ".":
                    letters[jv + maze[i][j + 1]] += [(j - 1,i)]
                elif maze[i][j + 2] == ".":
                    letters[jv + maze[i][j + 1]] += [(j + 2,i)]
            maze[i][j] = "#"
portal = {}
for k,v in letters.items():
    if k != "AA" and k != "ZZ":
        if v[0][0] == 2 or v[0][1] == 2 or v[0][0] == len(maze[0]) - 3 or v[0][1] == len(maze) - 3:
            portal[v[0]] = (v[1],-1)
            portal[v[1]] = (v[0],1)
        else:
            portal[v[0]] = (v[1],1)
            portal[v[1]] = (v[0],-1)
        # for coord in v:
    #     maze[coord[1]][coord[0]] = k[0]
print("\n".join(["".join(i) for i in maze]))
print(letters)
print(portal)

hasGone = [[[False] * len(maze[0]) for i in maze]]
hasGone[0][letters["AA"][0][1]][letters["AA"][0][0]] = True
q = deque()
q.append((letters["AA"][0],0,0))
possible = [[0,-1],[0,1],[-1,0],[1,0]]
foundZZ = False
while q and not foundZZ:
    (x,y),level,count = q.popleft()
    #print(level)
    for i in possible:
        nx = x + i[1]
        ny = y + i[0]
        if letters["ZZ"][0] == (nx,ny) and level == 0:
            answer = count + 1
            foundZZ = True
            break
        if not hasGone[level][ny][nx] and maze[ny][nx] == ".":
            hasGone[level][ny][nx] = True
            q.append(((nx,ny),level,count + 1))
    if (x,y) in portal:
        (nx,ny),inc = portal[(x,y)]
        print(level + inc)
        if level + inc >= len(hasGone):
            hasGone += [[[False] * len(maze[0]) for i in maze]]
        if level + inc != -1 and not hasGone[level + inc][ny][nx]:
            hasGone[level + inc][ny][nx] = True
            q.append(((nx,ny),level + inc,count + 1))

# for i in hasGone:
#     for j in i:
#         s = ""
#         for k in j:
#             if k:
#                 s += "#"
#             else:
#                 s += "."
#         print(s)
#     print()

print("Answer:",answer)