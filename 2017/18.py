from aocd import get_data
f = get_data(day=18,year=2017)
prog = f.split("\n")

def getNum(num,reg):
    try:
        return int(num)
    except:
        if not num in reg:
            reg[num] = 0
        return reg[num]
    
def set(arg,reg):
    reg[arg[1]] = getNum(arg[2],reg)

def add(arg,reg):
    reg[arg[1]] += getNum(arg[2],reg)

def mul(arg,reg):
    reg[arg[1]] *= getNum(arg[2],reg)

def mod(arg,reg):
    reg[arg[1]] %= getNum(arg[2],reg)

cmd = {
    "set": lambda a,b: set(a,b),
    "add": lambda a,b: add(a,b),
    "mul": lambda a,b: mul(a,b),
    "mod": lambda a,b: mod(a,b),
}

def part1():
    i = 0
    r = {}
    while True:
        args = prog[i].split(" ")
        if args[0] == "snd":
            prev = getNum(args[1],r)
        elif args[0] == "rcv":
            if getNum(args[1],r) != 0:
                break
        elif args[0] == "jgz":
            if getNum(args[1],r) > 0:
                i += getNum(args[2],r)
                continue
        else:
            if not args[1] in r:
                r[args[1]] = 0
            cmd[args[0]](args,r)
        i += 1
    return prev

def part2():
    def step(r,i,p,c):
        if p == 0:
            b = buf0
            ob = buf1
        else:
            b = buf1
            ob = buf0
        args = prog[i].split(" ")
        if args[0] == "snd":
            if p == 1:
                c += 1
            ob += [getNum(args[1],r)]
        elif args[0] == "rcv":
            if len(b) == 0:
                return (i,True,c)
            
            r[args[1]] = b[0]
            del b[0]
        elif args[0] == "jgz":
            if getNum(args[1],r) > 0:
                return (i + getNum(args[2],r),False,c)
        else:
            if not args[1] in r:
                r[args[1]] = 0
            cmd[args[0]](args,r)
        return (i + 1,False,c)
    

    r0,i0,buf0 = {"p":0},0,[]
    r1,i1,buf1 = {"p":1},0,[]
    c = 0
    while True:
        i0,b0,c = step(r0,i0,0,c)
        i1,b1,c = step(r1,i1,1,c)
        if b0 and b1:
            break
    return c
    
print("Part 1:", part1())
print("Part 2:", part2())