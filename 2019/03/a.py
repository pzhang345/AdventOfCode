line1, line2 = [i.split(",") for i in open("text.txt").read().split("\n")]
print(line1)
print(line2)
points = {}
x = 0
y = 0
for i in line1:
    for j in range(int(i[1:])):
        if(i[0] == "L"):
            x -= 1
        elif(i[0] == "R"):
            x += 1
        if(i[0] == "U"):
            y -= 1
        elif(i[0] == "D"):
            y += 1
        if(j != 0 and j != int(i[1:]) - 1):
            points[(x,y)] = True
x = 0
y = 0
inter = []
for i in line2:
    for j in range(int(i[1:])):
        if i[0] == "L":
            x -= 1
        elif i[0] == "R":
            x += 1
        if i[0] == "U":
            y -= 1
        elif i[0] == "D":
            y += 1
        if(j != 0 and j != int(i[1:]) - 1 and (x,y) in points):
            inter += [(x,y)]
print(inter)
min = abs(inter[0][0]) + abs(inter[0][1])
for i in inter:
    if min > abs(i[0]) + abs(i[1]):
        min = abs(i[0]) + abs(i[1])
print("Answer:",min)