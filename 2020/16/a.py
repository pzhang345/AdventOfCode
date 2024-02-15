fields, pticket, oticket = list(map(lambda str: str.split("\n"),open("text.txt").read().split("\n\n")))
del pticket[0],oticket[0]
print(fields,pticket)
isVaild = [False] * 1000
for i in fields:
    for j in i.split(": ")[1].split(" or "):
        min,max = j.split("-")
        for k in range(int(min),int(max) + 1):
            isVaild[k] = True
total = 0
for i in oticket:
    for j in i.split(","):
        if not isVaild[int(j)]:
            total += int(j)
print("Answer:",total)