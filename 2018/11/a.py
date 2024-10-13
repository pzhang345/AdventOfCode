serial = int(open("text.txt").read())
grid = []
for y in range(1,301):
    grid += [[]]
    for x in range(1,301):
        id = 10 + x
        grid[y-1] += [int(((id * y + serial) * id)/100)%10 - 5]
print(grid)

topx = 0
topy = 0
top = 0
for y in range(298):
    for x in range(298):
        s = 0
        for y1 in range(3):
            for x1 in range(3):
                s += grid[y + y1][x + x1]
        if s > top:
            top = s
            topx = x + 1
            topy = y + 1
print("Answer:",str(topx) + "," + str(topy))


