p = [[int(j) for j in i.split(",")]for i in open("text.txt").read().split("\n")]

def dist(p1,p2):
    s = 0
    for i in range(4):
        s += abs(p1[i] - p2[i])
    return s

c = []

for i in p:
    connect = []
    for j,jv in enumerate(c):
        for k in jv:
            if dist(i,k) <= 3:
                connect += [j]
                break
    if connect == []:
        c += [[i]]
    else:
        c[connect[0]] += [i]
        for j in connect[1:][::-1]:
            c[connect[0]] += c[j]
            del c[j]
print("Answer:",len(c))