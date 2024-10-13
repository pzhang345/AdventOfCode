grid = open("text.txt").read().split("\n")
print(grid)

d = [(0,-1),(0,1),(-1,0),(1,0),(-1,-1),(-1,1),(1,-1),(1,1)]
for c in range(10):
    newgrid = []
    for i,iv in enumerate(grid):
        s = ""
        for j,jv in enumerate(iv):
            count = {".":0,"|":0,"#":0}
            for di,dj in d:
                newi = i + di
                newj = j + dj
                if not 0 <= newi < len(grid) or not 0 <= newj < len(iv):
                    continue
                count[grid[newi][newj]] += 1
            if jv == "." and count["|"] >= 3:
                s += "|"
            elif jv == "|" and count["#"] >= 3:
                s += "#"
            elif jv == "#" and (count["|"] == 0 or count["#"] == 0):
                s += "."
            else:
                s += jv
        newgrid += [s]
    grid = newgrid
    print("\n".join(grid))
    print()
# print("\n".join(grid))
ct = 0
cl = 0
for i in grid:
    for j in i:
        if j == "|":
            ct += 1
        elif j == "#":
            cl += 1
print("Answer:",ct*cl)