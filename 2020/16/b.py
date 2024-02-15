fields, pticket, oticket = list(map(lambda str: str.split("\n"),open("text.txt").read().split("\n\n")))
del pticket[0],oticket[0]
pticket = list(map(lambda a: int(a),pticket[0].split(",")))
isVaild = [False] * 1000
isVaildField = [[False] * 1000 for i in fields]
departFields = []
for i,iv in enumerate(fields):
    if "departure" in iv:
        departFields += [i]
    for j in iv.split(": ")[1].split(" or "):
        min,max = j.split("-")
        for k in range(int(min),int(max) + 1):
            isVaild[k] = True
            isVaildField[i][k] = True
print(isVaildField)
for i in range(len(oticket) - 1,-1,-1):
    for j in oticket[i].split(","):
        if not isVaild[int(j)]:
            del oticket[i]
            break
print(len(oticket))
col = [[] for i in fields]
print(col)
for i in oticket:
    for j,v in enumerate(i.split(",")):
        col[j] += [int(v)]
print(col)

canBe = [[] for i in fields]
for i,iv in enumerate(col):
    for j,jv in enumerate(isVaildField):
        inRange = True
        for k in iv:
            if not jv[k]:
                inRange = False
                break
        if inRange:
            canBe[i] += [j]

hasAssigned = [False] * len(fields)
while not all(hasAssigned):
    for i,iv in enumerate(canBe):
        if not hasAssigned[i] and len(iv) == 1:
            hasAssigned[i] = True
            value = iv[0]
            iv = value
            break
    for i,iv in enumerate(canBe):
        if not hasAssigned[i] and value in iv:
            iv = iv.remove(value)
    #print(canBe)
print(canBe)
total = 1
for i in departFields:
    print(canBe.index([i]))
    total *= pticket[canBe.index([i])]
print("Answer:",total)