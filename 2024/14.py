from aocd import get_data
import keyboard
f = get_data(day=14,year=2024)
log = open("log.txt","w")

def part1():
    w,h = 101,103
    q = {(1,1):0,(1,-1):0,(-1,1):0,(-1,-1):0}
    for i in f.split("\n"):
        p,v = [[int(k) for k in j.split(",")] for j in i[2:].split(" v=")]
        x,y = (p[0] + 100 * v[0]) % w, (p[1] + 100 * v[1])% h
        if x == w//2 or y == h//2:
            continue
        qx = -1 if w//2 > x else 1
        qy = -1 if h//2 > y else 1
        q[(qx,qy)] += 1
    
    p = 1
    for k,v in q.items():
        p *= v
    
    return p

def part2():
    w,h = 101,103
    def stringp(p):
        arr = [["." for i in range(w)] for j in range(h)]
        for i in p:
            x,y = i[0][0],i[0][1]
            arr[y][x] = "#"
        s = ""
        for i in arr:
            s += "".join(i) + "\n"
        return s
    def getLength(arr):
        arr.sort()
        m = 0
        c = 0
        curr = -2
        for i in arr:
            if curr + 1 == i:
                c += 1
                if c > m:
                    m = c
            else:
                c = 0
            curr = i
        return m
    p = []
    for i in f.split("\n"):
        p += [[[int(k) for k in j.split(",")] for j in i[2:].split(" v=")]]
    c = 0
    while True:
        has = {}
        for i in p:
            i[0][0],i[0][1] = (i[0][0] + i[1][0]) % w,(i[0][1] + i[1][1]) % h
            if not i[0][0] in has:
                has[i[0][0]] = []
            has[i[0][0]] += [i[0][1]]
        
        m = 0
        for k,v in has.items():
            r = getLength(v)
            if(r > m):
                m = r
        # print(c)
        c += 1
        if m > 20:
            log.write(stringp(p))
            log.close()
            return c
        

    

print("Part 1:", part1())
print("Part 2:", part2())