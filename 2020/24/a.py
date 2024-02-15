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
for line in open("text.txt"):
    temp = getpos(line.strip())
    print(temp)
    if temp in color:
        color[temp] = not color[temp]
        print("a")
    else:
        color[temp] = True
b = 0
for k,v in color.items():
    if v:
        b += 1
print(color)
print("Answer:",b)
    