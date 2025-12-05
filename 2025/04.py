from utils import run

def part1(input: str):
    grid = input.split("\n")
    count = 0
    for y in range(len(grid)):
        for x in range(len(grid[y])):
            if grid[y][x] == ".":
                continue
            adj = 0
            for dy in [-1, 0, 1]:
                for dx in [-1, 0, 1]:
                    if dy == 0 and dx == 0:
                        continue
                    ny = y + dy
                    nx = x + dx
                    if 0 <= ny < len(grid) and 0 <= nx < len(grid[y]):
                        if grid[ny][nx] == "@":
                            adj += 1
            if adj < 4:
                count += 1
    return count
            


def part2(input: str):
    grid = [list(i) for i in input.split("\n")]
    
    count = 0
    done = False
    while not done:
        done = True
        for y in range(len(grid)):
            for x in range(len(grid[y])):
                if grid[y][x] == ".":
                    continue
                adj = 0
                for dy in [-1, 0, 1]:
                    for dx in [-1, 0, 1]:
                        if dy == 0 and dx == 0:
                            continue
                        ny = y + dy
                        nx = x + dx
                        if 0 <= ny < len(grid) and 0 <= nx < len(grid[y]):
                            if grid[ny][nx] == "@":
                                adj += 1
                if adj < 4:
                    grid[y][x] = "."
                    count += 1
                    done = False     
    return count


run(4, part1, part2)