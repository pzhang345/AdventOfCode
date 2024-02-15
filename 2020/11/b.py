map = [list(i) for i in open("text.txt").read().split("\n")]

def move(map):
    tempmap = [["."] * len(map[0]) for i in range(len(map))]
    hasChanged = False
    for i in range(len(map)):
        for j in range(len(map[i])):
            count = 1
            arr = [False] * 8
            pos = [[-1,-1],[-1,0],[-1,1],[0,-1],[0,1],[1,-1],[1,0],[1,1]]
            while not all(arr):
                for index,p in enumerate(pos):
                    toti = p[0] * count + i
                    totj = p[1] * count + j
                    if arr[index] == False:
                        if((not 0 <= toti < len(map)) or (not 0 <= totj < len(map[0]))):
                            arr[index] = "L"
                        elif(map[toti][totj] != "."):
                            arr[index] = map[toti][totj]
                count += 1
            if map[i][j] == "L" and arr.count("#") == 0:
                hasChanged = True
                tempmap[i][j] = "#"
            elif map[i][j] == "#" and arr.count("#") >= 5:
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