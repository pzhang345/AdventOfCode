canBe = {}
for line in open("text.txt"):
    ing = line.split(" (")[0].split(" ")
    al = line.split(" (contains ")[1].strip().replace(")","").split(", ")
    for i in al:
        if not i in canBe:
            canBe[i] = ing
        else:
            temp = list(canBe[i])
            for j in canBe[i]:
                if not j in ing:
                    temp.remove(j)

            canBe[i] = temp
hasDone = [False] * len(canBe)
while not all(hasDone):
    for i,k in enumerate(canBe):
        if hasDone[i]:
            continue
        print(canBe[k])
        if len(canBe[k]) == 1:
            hasDone[i] = True
            ing = canBe[k][0]
            for j,jv in enumerate(canBe):
                if ing in canBe[jv] and not hasDone[j]:
                    canBe[jv].remove(ing)
        # print(hasDone)
ans = ""
for i in sorted(canBe):
    ans += canBe[i][0] + ","

print("Answer:",ans[:-1])