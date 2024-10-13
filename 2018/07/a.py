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

o = ""
while not all(c):
    for i in range(ord("A"),ord("A") + len(d)):
        if(c[i - ord("A")]):
            continue
        ch = chr(i)
        add = True
        for j in d[ch]:
            if not c[ord(j)-ord("A")]:
                add = False
                break
        if add:
            o += ch
            c[i - ord("A")] = True
            break
print("Answer:", o)