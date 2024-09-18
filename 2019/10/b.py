import numpy
arr = open("text.txt").read().split("\n")
print(arr)
print(numpy.arctan2(-1,-1) * 180 / numpy.pi)
def count(x,y):
    m = {}
    for i in range(len(arr)):
        for j in range(len(arr[i])):
            if (i == y and j == x) or arr[i][j] == '.':
                continue
            dy = y - i
            dx = x - j
            com = 1
            s = (numpy.arctan2(-dx,dy) * 180 / numpy.pi)
            if(s < 0):
                 s += 360
            if not s in m:
                m[s] = []
            m[s] += [(abs(dx) + abs(dy),j,i)]
    return m

max = 0
degs = {}
for i in range(len(arr)):
        for j in range(len(arr[i])):
            a = count(j,i)
            if(max < len(a)):
                max = len(a)
                degs = a

degs = dict(sorted(degs.items()))
for i in degs:
     degs[i] = sorted(degs[i])

count = 1
while count != 200:
    for i in list(degs.keys()):
        print(count,degs[i][0])
        if(count == 200):
            answer = degs[i][0]
            break
        degs[i] = degs[i][1:]
        if(len(degs[i]) == 0):
            del degs[i]
        count += 1
print("Answer:",answer[1] * 100 + answer[2])