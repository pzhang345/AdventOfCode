t = open("text.txt").read()[1:-1]

def findP(r):
    p = 0
    s = ""
    for c in r:
        s += c
        if c == "(":
            p += 1
        elif c == ")":
            p -= 1
        if p == 0:
            return s

def step(r,x,y,co):
    i = 0
    le = []
    currx = x
    curry = y
    count = co
    while i < len(r):
        c = r[i]

        if c == "(":
            s = findP(r[i:])
            i += len(s) - 1
            step(s[1:-1],currx,curry,count)

        elif c == "|":
            le += [(currx,curry,i)]
            currx = x
            curry = y
            count = co
        else:
            dx,dy = dir[c]
            currx += dx
            curry += dy
            count += 1
            if not (currx,curry) in pos or pos[(currx,curry)] > count:
                pos[(currx,curry)] = count

        i += 1
    print(r)
    print(le)
    return 0

dir = {
    "N":(0,-1),
    "S":(0,1),
    "W":(-1,0),
    "E":(1,0)
}
pos = {(0,0):0}

print(step(t,0,0,0))
print(pos)

print("Answer:",len([v for k,v in pos.items() if v >= 1000]))