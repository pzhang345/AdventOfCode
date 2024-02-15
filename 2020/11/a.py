map = [list(i) for i in open("text.txt").read().split("\n")]

def move(map):
    tempmap = [["."] * len(map[0]) for i in range(len(map))]
    hasChanged = False
    for i in range(len(map)):
        for j in range(len(map[i])):
            count = 0
            for iin in range(-1,2):
                if i + iin == -1 or i + iin == len(map):
                    continue
                for jin in range(-1,2):
                    if j + jin == -1 or j + jin == len(map[i]) or iin == jin == 0:
                        continue
                    if map[i + iin][j + jin] == "#":
                        count += 1
            if map[i][j] == "L" and count == 0:
                hasChanged = True
                tempmap[i][j] = "#"
            elif map[i][j] == "#" and count >= 4:
                hasChanged = True
                tempmap[i][j] = "L"
            else:
                tempmap[i][j] = map[i][j]
    #print(tempmap)
    return [tempmap,hasChanged]
count = 0
while True:
    map, con = move(map)
    if not con:
        break
    print(count)
    count += 1

count = 0
for i in map:
    print(i)
    for j in i:
        if j == "#":
            count += 1
print("Answer:",count)