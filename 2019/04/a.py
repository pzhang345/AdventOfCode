min,max = [int(i) for i in open("text.txt").read().split("-")]
print(min,max)
count = 0
for i in range(min,max + 1):
    digits = [int(j) for j in list(str(i))]
    prev = 0
    notDec = True
    hasDoub = False
    for j in digits:
        if prev == j:
            hasDoub = True
        if prev > j:
            notDec = False
            break
        prev = j
    if notDec and hasDoub:
        count += 1
print("Answer:",count)