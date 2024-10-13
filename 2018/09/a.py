s = open("text.txt").read().split(" ")
p,m = int(s[0]), int(s[6])

ind = 0
parr = [0] * p
marr = [0]
for i in range(1,m + 1):
    if i % 23 == 0:
        ind = (ind - 7) % len(marr)
        parr[i % p] += i + marr[ind]
        del marr[ind]
    else:
        ind = (ind + 2) % len(marr)
        marr[ind:ind] = [i]

print("Answer:",max(parr))