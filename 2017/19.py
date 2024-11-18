from aocd import get_data
f = get_data(day=19,year=2017)
grid = f.split("\n")

def part1():
    y = 0
    for i in range(len(grid[0])):
        if grid[0][i] == '|':
            x = i
            break
    dirs = {(-1,0):'-',(1,0):'-',(0,-1):'|',(0,1):'|'}
    dir = (0,1)
    s = ""
    done = False
    while not done:
        while grid[y][x] != '+':
            if not 0 <= y < len(grid) or not 0 <= x < len(grid[0]) or grid[y][x] == ' ':
                done = True
                break
            if grid[y][x].isalpha():
                s += grid[y][x]
            x += dir[0]
            y += dir[1]

        for i in dirs:
            if (-dir[0],-dir[1]) == i or not 0 <= y + i[1] < len(grid) or not 0 <= x + i[0] < len(grid[0]):
                continue
            if grid[y + i[1]][x + i[0]] == dirs[i]:
                dir = i
                x += i[0]
                y += i[1]
                break
    return s

def part2():
    y = 0
    for i in range(len(grid[0])):
        if grid[0][i] == '|':
            x = i
            break
    dirs = {(-1,0):'-',(1,0):'-',(0,-1):'|',(0,1):'|'}
    dir = (0,1)
    done = False
    c = 0
    while not done:
        while grid[y][x] != '+':
            if not 0 <= y < len(grid) or not 0 <= x < len(grid[0]) or grid[y][x] == ' ':
                done = True
                break
            x += dir[0]
            y += dir[1]
            c += 1

        for i in dirs:
            if (-dir[0],-dir[1]) == i or not 0 <= y + i[1] < len(grid) or not 0 <= x + i[0] < len(grid[0]):
                continue
            if grid[y + i[1]][x + i[0]] == dirs[i]:
                dir = i
                x += i[0]
                y += i[1]
                c += 1
                break
    return c
print("Part 1:", part1())
print("Part 2:", part2())