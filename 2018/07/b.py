d = {}
for i in open("text.txt"):
    j,k = i.split(" ")[1],i.split(" ")[7]
    if not j in d:
        d[j] = []
    if not k in d:
        d[k] = []
    d[k] += [j]
c = [False] * len(d)
print(d)

elves = [0] * 5
elvesi = [-1] * 5
t = 0
while not all(c):
    for i in range(ord("A"),ord("A") + len(d)):
        if(c[i - ord("A")]) or (i - ord("A")) in elvesi:
            continue
        ch = chr(i)
        add = True
        for j in d[ch]:
            if not c[ord(j)-ord("A")]:
                add = False
                break
        if add:
            for j in range(len(elves)):
                if elves[j] == 0:
                    print(ch)
                    elves[j] = 61 + i - ord("A")
                    elvesi[j] = i - ord("A")
                    break
    print(t)
    print(elvesi)
    print(elves)
    print(c)
    for i in range(len(elves)):
        if elves[i] > 0:
            elves[i] -= 1
            if elves[i] == 0:
                c[elvesi[i]] = True
    t += 1
print("Answer:",t)