def dist(point):
    sum = 0
    for i in points:
        sum += abs(point[0] - i[0]) + abs(point[1] - i[1])
    return sum

points = [tuple([int(j) for j in i.split(", ")]) for i in open("text.txt").read().split("\n")]
maxd = 10000
x = [i[0] for i in points]
y = [i[1] for i in points]
bounds = [(min(x),max(x)),(min(y),max(y))]
print(bounds)

sum = 0
for y in range(bounds[1][0],bounds[1][1] + 1):
    s = ""
    for x in range(bounds[0][0],bounds[0][1] + 1):
        d = dist((x,y))
        if (x == bounds[0][0] or x == bounds[0][1] or y == bounds[1][0] or y == bounds[1][1]) and d < maxd - 1:
            print("ERROR")
        if d < maxd:
            sum += 1
print("Answer:",sum)
        