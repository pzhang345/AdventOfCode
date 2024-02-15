s = {}
canBe = {}
for line in open("text.txt"):
    ing = line.split(" (")[0].split(" ")
    al = line.split(" (contains ")[1].strip().replace(")","").split(", ")
    for i in ing:
        if not i in s:
            s[i] = 0
        s[i] += 1
    for i in al:
        if not i in canBe:
            canBe[i] = ing
        else:
            temp = list(canBe[i])
            for j in canBe[i]:
                if not j in ing:
                    temp.remove(j)

            canBe[i] = temp
canCon = set()
for k,v in canBe.items():
    for i in v:
        canCon.add(i)
print(s)
print(canBe)
count  = 0
for i in s:
    if not i in canCon:
        count += s[i]
print("Answer:",count)