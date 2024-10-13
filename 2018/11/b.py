serial = int(open("text.txt").read())
def getSize(x,y,size):
    size -= 1
    tr = bl = tl = 0
    if x != 0:
        bl = sumarr[y + size][x-1]
    if y != 0:
        tr = sumarr[y-1][x + size]
    if x != 0 and y != 0:
        tl = sumarr[y-1][x-1]
    return sumarr[y + size][x + size] - tr - bl + tl
grid = []
for y in range(1,301):
    grid += [[]]
    for x in range(1,301):
        id = 10 + x
        grid[y-1] += [int(((id * y + serial) * id)/100)%10 - 5]
print(grid)

topx = 0
topy = 0
tops = 0
top = 0
sumarr = []
for y in range(len(grid)):
    sumarr += [[]]
    for x in range(len(grid[y])):
        t = l = tl = 0
        if x != 0:
            l = sumarr[y][x-1]
        if y != 0:
            t = sumarr[y-1][x]
        if x != 0 and y != 0:
            tl = sumarr[y-1][x-1]
        sumarr[y] += [grid[y][x] + l + t - tl]
print(sumarr)


for y in range(len(grid)):
    for x in range(len(grid[y])):
        # print(x,y)
        for size in range(300 - max(y,x)):
            s = getSize(x,y,size)
            if s > top:
                print(s)
                top = s
                topx = x + 1
                topy = y + 1
                tops = size

print(getSize(90,269,1))

print("Answer:",str(topx) + "," + str(topy) + "," + str(tops))


