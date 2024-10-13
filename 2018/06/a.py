points = [tuple([int(j) for j in i.split(", ")]) for i in open("text.txt").read().split("\n")]
x = [i[0] for i in points]
y = [i[1] for i in points]
bounds = [(min(x),max(x)),(min(y),max(y))]
print(bounds)

d = {}
newXY = set()
for i,iv in enumerate(points):
    d[iv] = [i]
    newXY.add(iv)
    

de = [(-1,0),(1,0),(0,-1),(0,1)]
inf = set()

while len(newXY) != 0:
    ad = {}
    for k in newXY:
        if (k[0] == bounds[0][0] or k[0] == bounds[0][1] or k[1] == bounds[1][0] or k[1] == bounds[1][1]) and len(d[k]) == 1:
            inf.add(d[k][0])
        for j in de:
            newK = (k[0] + j[0],k[1] + j[1])
            if not newK in d and (bounds[0][0] <= newK[0] <= bounds[0][1] and bounds[1][0] <= newK[1] <= bounds[1][1]):
                if not newK in ad:
                    ad[newK] = []
                for i in d[k]:
                    if not i in ad[newK]:
                        ad[newK] += [i]
        
    if len(ad) == 0:
        break
    d.update(ad)
    newXY = set()
    for i in ad:
        newXY.add(i)
#     print(newXY)
# print(d)
# print(inf)

a = [0] *len(points)

for y in range(bounds[1][0],bounds[1][1] + 1):
    for x in range(bounds[0][0],bounds[0][1] + 1):
        if len(d[(x,y)]) == 1 and not d[(x,y)][0] in inf:
            a[d[(x,y)][0]] += 1
print("Answer:",max(a))
        