from aocd import get_data
f = get_data(day=17,year=2024)

def part1():
    def combo(r,i):
        if i <= 3:
            return i
        else:
            return r[i-4]
        
    def adv(r,i):
        r[0] = r[0]// 2 ** combo(r,i)


    def bxl(r,i):
        r[1] = r[1] ^ i

    def bst(r,i):
        r[1] = combo(r,i) % 8
    
    def bxc(r,i):
        r[1] = r[1] ^ r[2] 

    def bdv(r,i):
        r[1] = r[0]// 2 ** combo(r,i)
    
    def cdv(r,i):
        r[2] = r[0]// 2 ** combo(r,i)
    
    cmds = {
        0: lambda r,i: adv(r,i),
        1: lambda r,i: bxl(r,i),
        2: lambda r,i: bst(r,i),
        4: lambda r,i: bxc(r,i),
        6: lambda r,i: bdv(r,i),
        7: lambda r,i: cdv(r,i),
    }
    lines = f.split("\n")
    r = [int(lines[i].split(": ")[1]) for i in range(3)]
    p = [int(i) for i in lines[4].split(":")[1].split(",")]
    out = []
    idx = 0
    while idx < len(p):
        cmd,arg = p[idx],p[idx+1]
        if cmd == 3:
            if r[0] != 0:
                idx = arg
                continue
        elif cmd == 5:
            out += [combo(r,arg)%8]
        else:
            cmds[cmd](r,arg)
        idx += 2
    return ",".join([str(i) for i in out])

def part2():
    def combo(r,i):
        if i <= 3:
            return i
        else:
            return r[i-4]
        
    def adv(r,i):
        r[0] = r[0]// 2 ** combo(r,i)


    def bxl(r,i):
        r[1] = r[1] ^ i

    def bst(r,i):
        r[1] = combo(r,i) % 8
    
    def bxc(r,i):
        r[1] = r[1] ^ r[2] 

    def bdv(r,i):
        r[1] = r[0]// 2 ** combo(r,i)
    
    def cdv(r,i):
        r[2] = r[0]// 2 ** combo(r,i)
    
    cmds = {
        0: lambda r,i: adv(r,i),
        1: lambda r,i: bxl(r,i),
        2: lambda r,i: bst(r,i),
        4: lambda r,i: bxc(r,i),
        6: lambda r,i: bdv(r,i),
        7: lambda r,i: cdv(r,i),
    }
    lines = f.split("\n")
    r = [int(lines[i].split(": ")[1]) for i in range(3)]
    p = [int(i) for i in lines[4].split(":")[1].split(",")]
    possible = [i for i in range(8**4)]
    
    out = []
    areg = 0
    works = []
    for i in range(len(p) - 3):
        newpossible = []
        for curr in possible:
            areg = curr
            r = [areg,0,0]
            idx = 0
            out = []
            while idx < len(p):
                cmd,arg = p[idx],p[idx+1]
                if cmd == 3:
                    if r[0] != 0:
                        idx = arg
                        continue
                elif cmd == 5:
                    out += [combo(r,arg)%8]
                    if out == p or out != p[0:len(out)]:
                        break
                else:
                    cmds[cmd](r,arg)
                idx += 2
            
            if out == p:
                works += [curr]

            if len(out) > i + 1:
                for j in range(8):
                    newpossible += [j * 8 ** (i + 4) + curr]
        possible = newpossible
    return min(works)

print("Part 1:", part1())
print("Part 2:", part2())