from collections import deque
grid = [sorted(i.split(", ")) for i in open("text.txt").read().split("\n")]
def parseRange(str):
    b = [int(i) for i in str.split("..")]
    if len(b) == 1:
        return b
    else:
        num = []
        for i in range(b[0],b[1] + 1):
            num += [i]
        return num
def printd():
    for y in range(bounds[1][0],bounds[1][1] + 1):
        s = ""
        for x in range(bounds[0][0],bounds[0][1] + 1):
            if (x,y) in d:
                s += d[(x,y)]
            else:
                s += " "
        print(s)
def water(pos):
    count = 0
    q = deque([pos])
    prev = deque()
    while q:
        currPos = q.popleft()
        below = (currPos[0], currPos[1] + 1)

        if below[1] > bounds[1][1] or (below in d and d[below] == "."):
            return (True,0)
        
        prev.append(currPos)
        if not below in d:
            q.append(below)
            d[below] = "."
    if len(prev) == 1:
        return(True,0)
    done = False
    print("prev:",prev)
    q.append(prev.pop())
    still = []
    while q:
        currPos = q.popleft()
        still += [currPos]
        below = (currPos[0], currPos[1] + 1)
        left = (currPos[0] - 1, currPos[1])
        right = (currPos[0] + 1, currPos[1])

        if not below in d:
            d[below] = "."
            out = water(below)
            done = out[0]
            count += out[1]
            if not done:
                still.remove(currPos)
                q.append(currPos)
        else:
            if not left in d:
                q.append(left)
                d[left] = "."
            if not right in d:
                q.append(right)
                d[right] = "."

        if not q and not done:
            for i in still:
                d[i] = "+"
            count += len(still)
            still = []
            if not prev:
                return (False,count)
            q.append(prev.pop())
    return (True,count)

d = {}
allx = set()
ally = set()
for i in grid:
    xarr = parseRange(i[0][2:])
    yarr = parseRange(i[1][2:])
    for x in xarr:
        for y in yarr:
            d[(x,y)] = "#"
            allx.add(x)
            ally.add(y)

bounds = [[min(allx),max(allx)],[min(ally),max(ally)]]
out = water((500,bounds[1][0]-1))
printd()
count = 0
for k,v in d.items():
    if v == "+":
        count += 1
print(out)
print("Answer:",count)