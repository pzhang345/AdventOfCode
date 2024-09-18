min0 = -1
val = 0
line = open("text.txt").read().strip()
for i in range(int(len(line)/(25 * 6))):
    n = [0,0,0]
    for j in range((25 * 6)):
        n[int(line[i * 25 * 6 + j])] += 1
    if min0 == -1 or min0 > n[0]:
        min0 = n[0]
        val = n[1] * n[2]
print("Answer:",val)