from math import gcd
arr = open("text.txt").read().split("\n")
print(arr)
def count(x,y):
    m = set()
    for i in range(len(arr)):
        for j in range(len(arr[i])):
            if (i == y and j == x) or arr[i][j] == '.':
                continue
            dy = y - i
            dx = x - j
            com = gcd(dy,dx)
            m.add((int(dx/com),int(dy/com)))
    return len(m)

ma = 0
for i in range(len(arr)):
        for j in range(len(arr[i])):
            ma = max(ma,count(j,i))
print("Answer:",ma)