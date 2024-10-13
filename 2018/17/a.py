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
def water(pos):
    count = 0
    q = deque([pos])
    prev = deque([])
    while q:
        currPos = q.popleft()
        below = (currPos[0], currPos[1] + 1)

        if below[1] > bounds[1][1] or (below in d and d[below] == "."):
            return (True,count)
        
        prev.append(currPos)
        if not below in d:
            q.append(below)
            d[below] = "."
            count += 1
    done = False
    q.append(prev.pop())
    still = []
    while q:
        currPos = q.popleft()
        still += [currPos]
        below = (currPos[0], currPos[1] + 1)
        left = (currPos[0] - 1, currPos[1])
        right = (currPos[0] + 1, currPos[1])

        if not below in d:
            out = water(currPos)
            done = out[0]
            count += out[1]
        else:
            if not left in d:
                q.append(left)
                d[left] = "."
                count += 1
            if not right in d:
                q.append(right)
                d[right] = "."
                count += 1
        if not q and not done:
            for i in still:
                d[i] = "+"
            still = []
            if not prev:
                return (False,count)
            q.append(prev.pop())
    return (True,count)

out = water((500,bounds[1][0]-1))
printd()
print("Answer:",out[1])