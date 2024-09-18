line1, line2 = [i.split(",") for i in open("text.txt").read().split("\n")]
print(line1)
print(line2)
points = {}
x = 0
y = 0
count = 0
for i in line1:
    for j in range(int(i[1:])):
        count += 1
        if(i[0] == "L"):
            x -= 1
        elif(i[0] == "R"):
            x += 1
        if(i[0] == "U"):
            y -= 1
        elif(i[0] == "D"):
            y += 1
        if(j != 0 and j != int(i[1:]) - 1):
            if not (x,y) in points: 
                points[(x,y)] = count
        
x = 0
y = 0
inter = []
count = 0
for i in line2:
    for j in range(int(i[1:])):
        count += 1
        if i[0] == "L":
            x -= 1
        elif i[0] == "R":
            x += 1
        if i[0] == "U":
            y -= 1
        elif i[0] == "D":
            y += 1
        if(j != 0 and j != int(i[1:]) - 1 and (x,y) in points and not (x,y) in inter):
            inter += [(x,y)]
            points[(x,y)] += count
print(inter)
min = points[inter[0]]
for i in inter:
    if min > points[i]:
        min = points[i]
print("Answer:",min)