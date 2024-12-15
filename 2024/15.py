from aocd import get_data
from collections import deque
f = get_data(day=15,year=2024)

def part1():
    b,m = f.split("\n\n")
    m = m.replace("\n","")
    b = [list(i) for i in b.split("\n")]
    x = -1
    for py,i in enumerate(b):
        for px,j in enumerate(i):
            if j == "@":
                x,y = px,py
                break
        if x != -1:
            break
    dirs = {"^":(0,-1),">":(1,0),"v":(0,1),"<":(-1,0)}
    for i in m:
        newx = x
        newy = y
        move = True
        done = False
        while not done:
            newx += dirs[i][0]
            newy += dirs[i][1]
            if b[newy][newx] == "#":
                move = False
                done = True
            elif b[newy][newx] == ".":
                move = True
                done = True
        
        if move:
            b[newy][newx] = "O"
            b[y][x] = "."
            x,y = x + dirs[i][0],y + dirs[i][1]
            b[y][x] = "@"

    s = 0
    for y,i in enumerate(b):
        for x,j in enumerate(i):
            if j == "O":
                s += 100 * y + x
    return s

def part2():
    board,m = f.split("\n\n")
    m = m.replace("\n","")
    b = []
    boxes = []
    for py,i in enumerate(board.split("\n")):
        b += [""]
        for px,j in enumerate(i):
            if j == "#":
                b[py] += "##"
            else:
                b[py] += ".."
            
            if j == "@":
                x,y = 2*px,py
            if j == "O":
                boxes += [[2*px,py]]
    dirs = {"^":(0,-1),">":(1,0),"v":(0,1),"<":(-1,0)}
    for i in m:
        check = deque([(x,y)])
        move = True
        bi = set()
        while move and check:
            newx,newy = check.pop()
            newx += dirs[i][0]
            newy += dirs[i][1]
            if b[newy][newx] == "#":
                move = False
            elif [newx,newy] in boxes or [newx - 1,newy] in boxes:
                if [newx - 1,newy] in boxes:
                    newx = newx - 1
                idx = boxes.index([newx,newy])
                if not idx in bi: 
                    bi.add(idx)
                    check.append((newx,newy))
                    check.append((newx + 1,newy))
        
        if move:
            for j in bi:
                boxes[j][0] += dirs[i][0]
                boxes[j][1] += dirs[i][1]
            x += dirs[i][0]
            y += dirs[i][1]

    s = 0
    for i in boxes:
        s += 100 * i[1] + i[0]
    return s

print("Part 1:", part1())
print("Part 2:", part2())