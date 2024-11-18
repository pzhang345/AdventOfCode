from aocd import get_data
f = get_data(day=23,year=2017)
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

def sub(arg,reg):
    reg[arg[1]] -= getNum(arg[2],reg)

def mul(arg,reg):
    reg[arg[1]] *= getNum(arg[2],reg)

cmd = {
    "set": lambda a,b: set(a,b),
    "sub": lambda a,b: sub(a,b),
    "mul": lambda a,b: mul(a,b),
}

def part1():
    i = 0
    r = {}
    c = 0
    while i < len(prog):
        args = prog[i].split(" ")
        if args[0] == "jnz":
            if getNum(args[1],r) != 0:
                i += getNum(args[2],r)
                continue
        else:
            if not args[1] in r:
                r[args[1]] = 0
            cmd[args[0]](args,r)
            if args[0] == "mul":
                c += 1
        i += 1
    return c

def part2():
    def isPrime(num):
        for i in range(2,num//2):
            if num%i == 0:
                return False
        return True
    i = 0
    r = {'a':1}
    while i < 8:
        args = prog[i].split(" ")
        if args[0] == "jnz":
            if getNum(args[1],r) != 0:
                i += getNum(args[2],r)
                continue
        else:
            if not args[1] in r:
                r[args[1]] = 0
            cmd[args[0]](args,r)
        i += 1
    incr = -int(prog[-2].split(" ")[2])

    c = 0
    for i in range(r['b'],r['c'] + 1,incr):
        if not isPrime(i):
            c += 1
    return c
    

print("Part 1:", part1())
print("Part 2:", part2())

# sb = 109900
# c = 126900
# for b in range(sb,c + 1,17)
    # f = 1
    # for d in range(2,b)
        # for e in range(2,b)
            # if d * e == b:
                # f = 0
    # if f == 0:
    #   h += 1