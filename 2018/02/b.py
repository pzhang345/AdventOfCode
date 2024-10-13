boxes = open("text.txt").read().split("\n")
for i in boxes:
    diff = 0
    for j in boxes:
        if i == j:
            continue
        diff = 0
        for k in range(len(i)):
            if i[k] != j[k]:
                diff += 1
                pos = k
                if diff == 2:
                    break
        if diff == 1:
            ans = i[0:pos] + i[pos+1:]
            break
    if diff == 1:
        break
print("Answer:",ans)