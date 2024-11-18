from aocd import get_data
f = get_data(day=16,year=2017)
instr = f.split(",")

def part1():
    c = [chr(ord("a") + i) for i in range(16)]
    for i in instr:
        if i[0] == "s":
            x = int(i[1:])
            c = c[16 - x:] + c[0:16-x]
        elif i[0] == "x":
            x,y = [int(j) for j in i[1:].split("/")]
            c[x],c[y] = c[y], c[x]
        elif i[0] == "p":
            x,y = [c.index(j) for j in i[1:].split("/")]
            c[x],c[y] = c[y], c[x]
    return ''.join(c)

def part2():
    c = [chr(ord("a") + i) for i in range(16)]
    done = {}
    rev = {}
    total = 1000000000
    for it in range(total):
        s = ''.join(c)
        if s in done:
            l = it
            break
        done[s] = it
        rev[it] = s
        for i in instr:
            if i[0] == "s":
                x = int(i[1:])
                c = c[16 - x:] + c[0:16-x]
            elif i[0] == "x":
                x,y = [int(j) for j in i[1:].split("/")]
                c[x],c[y] = c[y], c[x]
            elif i[0] == "p":
                x,y = [c.index(j) for j in i[1:].split("/")]
                c[x],c[y] = c[y], c[x]
    
    total = (total - done[s]) % (l - done[s])
    return rev[total + done[s]]

print("Part 1:", part1())
print("Part 2:", part2())