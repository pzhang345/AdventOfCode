from aocd import get_data
f = get_data(day=6,year=2024)

def part1():
    m = f.split("\n")
    x,y = -1,0
    for i in range(len(m)):
        for j in range(len(m[i])):
            if m[i][j] == "^":
                y = i
                x = j
                break
        if x != -1:
            break
    dirs = [(0,-1),(1,0),(0,1),(-1,0)]
    d = 0
    hasBeen = set()
    
    while True:

        hasBeen.add((x,y))

        newx = dirs[d][0] + x
        newy = dirs[d][1] + y
        
        if not (0 <= newx < len(m[0]) and 0 <= newy < len(m)):
            break

        if(m[newy][newx] == "#"):
            d = (d + 1) % len(dirs)
        else:
            x = newx
            y = newy
    return len(hasBeen)


def part2():
    m = f.split("\n")
    sx,sy = -1,0
    for i in range(len(m)):
        for j in range(len(m[i])):
            if m[i][j] == "^":
                sy = i
                sx = j
                break
        if sx != -1:
            break
    dirs = [(0,-1),(1,0),(0,1),(-1,0)]
    d = 0
    c = 0

    po = set()
    x,y = sx,sy
    while True:
        
        po.add((x,y))

        newx = dirs[d][0] + x
        newy = dirs[d][1] + y

        if not (0 <= newx < len(m[0]) and 0 <= newy < len(m)):
            break

        if m[newy][newx] == "#":
            d = (d + 1) % len(dirs)
        else:
            x = newx
            y = newy

    for o in po:
        x,y,d = sx,sy,0
        hasBeen = set()
        loop = False
        while True:

            if (x,y,d) in hasBeen:
                loop = True
                break

            hasBeen.add((x,y,d))

            newx = dirs[d][0] + x
            newy = dirs[d][1] + y
            if not(0 <= newx < len(m[0]) and 0 <= newy < len(m)):
                break

            if m[newy][newx] == "#" or (newx,newy) == o:
                d = (d + 1) % len(dirs)
            else:
                x = newx
                y = newy

        if loop:
            c += 1
    return c

print("Part 1:", part1())
print("Part 2:", part2())