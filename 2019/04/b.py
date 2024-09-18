min,max = [int(i) for i in open("text.txt").read().split("-")]
print(min,max)
count = 0
for i in range(min,max + 1):
    digits = [int(j) for j in list(str(i))]
    prev = 0
    leg = 1
    notDec = True
    hasDoub = False
    for j in digits:
        if prev == j:
            leg += 1
        else:
            if leg == 2:
                hasDoub = True
            leg = 1
        if prev > j:
            notDec = False
            break
        prev = j
    if notDec and (hasDoub or leg == 2):
        count += 1
print("Answer:",count)