num = int(open("text.txt").read())
e = [0,1]
r = [3,7]
while len(r) < num + 10:
    de = [r[e[0]],r[e[1]]]
    new = r[e[0]] + r[e[1]]
    r += [int(i) for i in str(new)]
    for i in range(len(e)):
        e[i] = (e[i] + 1 + de[i]) % len(r)
print("Answer:","".join([str(i) for i in r[num:num+10]]))