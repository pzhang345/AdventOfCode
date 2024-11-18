from aocd import get_data
f = get_data(day=10,year=2017)

def part1():
    d = [int(i) for i in f.split(",")]
    l = [i for i in range(256)]
    c = 0
    ss = 0
    for i in d:
        tl = []
        for j in range(i):
            tl += [l[(c + j)%256]]
        tl.reverse()
        for j in range(i):
            l[(c + j) % 256] = tl[j]
        
        c += i + ss
        ss += 1
    return l[0] * l[1]

def part2():
    d = [ord(i) for i in f] + [17, 31, 73, 47, 23]
    
    l = [i for i in range(256)]
    c = 0
    ss = 0

    for x in range(64):
        for i in d:
            tl = []
            for j in range(i):
                tl += [l[(c + j)%256]]
            tl.reverse()
            for j in range(i):
                l[(c + j) % 256] = tl[j]
            
            c += i + ss
            ss += 1
    s = ""
    for i in range(16):
        temp = 0
        for j in range(16):
            temp = l[i * 16 + j] ^ temp
        s += str(hex(temp)[2:])
    return s
    

print("Part 1:", part1())
print("Part 2:", part2())