def getArea(arr):
    x = [i[0] for i in arr]
    y = [i[1] for i in arr]
    return (max(x) - min(x)) * (max(y) - min(y))
p = []
v = []
for i in open("text.txt"):
    p += [[int(j) for j in i.strip().split("=<")[1].split(">")[0].split(",")]]
    v += [[int(j) for j in i.strip().split("=<")[2].split(">")[0].split(",")]]
print(p)
print(v)
while True:
    temp = [list(i) for i in p]
    for i in range(len(p)):
        temp[i][0] += v[i][0]
        temp[i][1] += v[i][1]
    if(getArea(temp) > getArea(p)):
        break
    p = temp

print(p)
x = [i[0] for i in p]
y = [i[1] for i in p]
p = [tuple(i) for i in p]
print("Answer:")
for iy in range(min(y),max(y) + 1):
    s = ""
    for ix in range(min(x),max(x) + 1):
        if (ix,iy) in p:
            s += "#"
        else:
            s += " "
    print(s)