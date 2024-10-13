s = open("text.txt").read().split(" ")
p,m = int(s[0]), int(s[6]) * 100

curr = 0
parr = [0] * p
marr = {0: [0,0]}
for i in range(1,m + 1):
    if i % 23 == 0:
        for j in range(7):
            curr = marr[curr][0]
        parr[i % p] += i + curr
        marr[marr[curr][0]][1] = marr[curr][1]
        marr[marr[curr][1]][0] = marr[curr][0]
        d = curr
        curr = marr[curr][1]
        del d
    else:
        curr = marr[curr][1]
        marr[i] = [curr,marr[curr][1]]

        marr[curr][1] = i
        curr = i
        marr[marr[curr][1]][0] = i

print("Answer:",max(parr))