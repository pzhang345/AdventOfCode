from aocd import get_data
f = get_data(day=14,year=2017)

def knotHash(s):
    d = [ord(i) for i in s] + [17, 31, 73, 47, 23]
    
    l = [i for i in range(256)]
    c = 0
    ss = 0

    for x in range(64):
        for i in d:
            for j in range(i//2):
                p1 = (c + j) % 256
                p2 = (c + i - j - 1) % 256
                l[p1],l[p2] = l[p2],l[p1]
            c += i + ss
            ss += 1
    s = ""
    for i in range(16):
        temp = 0
        for j in range(16):
            temp = l[i * 16 + j] ^ temp
        s += str(hex(temp)[2:].zfill(2))
    return s

from collections import deque
def part1():
    sum = 0
    for i in range(128):
        hash = knotHash(f + "-" + str(i))
        sum += bin(int(hash,16))[2:].count('1')
    return sum

def part2():
    grid = []
    for i in range(128):
        hash = knotHash(f + "-" + str(i))
        grid += [bin(int(hash,16))[2:].zfill(128)]
    done = set()
    count = 0
    dir = [(0,-1),(0,1),(-1,0),(1,0)]
    for i,iv in enumerate(grid):
        for j,jv  in enumerate(iv):
            if jv == '0' or (i,j) in done:
                continue
            count += 1
            d = deque([(i,j)])
            done.add((i,j))
            while d:
                curr = d.pop()
                for k in dir:
                    newi,newj = curr[0] + k[0], curr[1] + k[1]
                    if not 0 <= newi < 128 or not 0 <= newj < 128:
                        continue
                    if grid[newi][newj] == '1' and not (newi,newj) in done:
                        d.append((newi,newj))
                        done.add((newi,newj))
    return count

print("Part 1:", part1())
print("Part 2:", part2())