def getpos(str):
    x = 0
    y = 0
    while(len(str) != 0):
        if(str[0] == "e"):
            x += 1
            str = str[1:]
        elif(str[0:2] == "se"):
            x += 0.5
            y += 1
            str = str[2:]
        elif(str[0:2] == "sw"):
            x -= 0.5
            y += 1
            str = str[2:]
        elif(str[0] == "w"):
            x -= 1
            str = str[1:]
        elif(str[0:2] == "nw"):
            x -= 0.5
            y -= 1
            str = str[2:]
        elif(str[0:2] == "ne"):
            x += 0.5
            y -= 1
            str = str[2:]
        # print(len(str),str)
    return (x,y)
color = {}
nextTo = [(1,0),(0.5,1),(-0.5,1),(-1,0),(-0.5,-1),(0.5,-1)]
for line in open("text.txt"):
    temp = getpos(line.strip())
    print(temp)
    if temp in color:
        color[temp] = not color[temp]
        print("a")
    else:
        color[temp] = True
b = 0
for i in range(100):
    adj = {}
    print(i,len([k for k,v in color.items() if v]))
    for j in [k for k,v in color.items() if v]:
        if not j in adj:
            adj[j] = 0
        for k in nextTo:
            pos = (k[0] + j[0], k[1] + j[1])
            if not pos in adj:
                adj[pos] = 0
            adj[pos] += 1
    for k,v in adj.items():
        if not k in color:
            color[k] = False
        if color[k] and not (v == 1 or v == 2):
            color[k] = False
        elif not color[k] and v == 2:
            color[k] = True

print("Answer:",len([k for k,v in color.items() if v]))
    