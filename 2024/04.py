from aocd import get_data
f = get_data(day=4,year=2024)

def part1():
    ch = f.split("\n")
    
    dirs = []

    for i in range(-1,2):
        for j in range(-1,2):
            if i == 0 and j == 0:
                continue
            dirs += [(i,j)]

    w = "XMAS"
    c = 0
    
    for i in range(len(ch)):
        for j in range(len(ch[i])):
            for k in range(len(dirs)):
                isXMAS = True
                for l in range(len(w)):
                    ni = i + dirs[k][0] * l
                    nj = j + dirs[k][1] * l
                    if not (0 <= ni < len(ch) and 0 <= nj < len(ch[0])) or w[l] != ch[ni][nj]:
                        isXMAS = False
                        break
                if isXMAS:
                    c += 1
    return c


def part2():
    ch = f.split("\n")

    dirs = [((-1,-1),(1,1)),((1,-1),(-1,1))]
    c = 0
    for i in range(1,len(ch) - 1):
        for j in range(1,len(ch[i]) - 1):
            if(ch[i][j] != "A"):
                continue
            isXMAS = True
            for k in dirs:
                le = set()
                for l in k:
                    newi = i + l[0]
                    newj = j + l[1]
                    le.add(ch[newi][newj])

                if le != set(["M","S"]):
                    isXMAS = False
                    break

            if isXMAS:
                c += 1
    return c
                
                

print("Part 1:", part1())
print("Part 2:", part2())