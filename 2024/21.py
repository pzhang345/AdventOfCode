from aocd import get_data
f = get_data(day=21,year=2024)

def part1():
    keys = {(0,0):'7',(1,0):'8',(2,0):'9',(0,1):'4',(1,1):'5',(2,1):'6',(0,2):'1',(1,2):'2',(2,2):'3',(1,3):'0',(2,3):'A'}
    rkeys = {}

    dkeys = {(1,0):'^',(2,0):'A',(0,1):'<',(1,1):'v',(2,1):'>'}
    rdkeys = {}

    dirs = {'^':(0,-1),'>':(1,0),'v':(0,1),'<':(-1,0)}

    for k,v in keys.items():
        rkeys[v] = k

    for k,v in dkeys.items():
        rdkeys[v] = k
    
    def check(keys,x,y,s):
        for i in s:
            x,y = x + dirs[i][0], y + dirs[i][1]
            if not (x,y) in keys:
                return False
        return True
    def getKeyPath(keys,rkeys,s,e,cache):
        if (s,e) in cache:
            return cache[(s,e)]
        
        sx,sy = rkeys[s]
        ex,ey = rkeys[e]
        xsym = ('<' if sx > ex else '>') * abs(ex - sx)
        ysym = ('^' if sy > ey else 'v') * abs(ey - sy)

        cache[(s,e)] = set()

        if check(keys,sx,sy,xsym + ysym):
            cache[(s,e)].add(xsym + ysym + 'A')
        
        if check(keys,sx,sy,ysym + xsym):
            cache[(s,e)].add(ysym + xsym + 'A')
        return cache[(s,e)]

    def getPaths(keys,rkeys,code,start,cache):
        paths = [""]
        for i,c in enumerate(start + code):
            if i == len(code):
                break

            paths = append(paths,getKeyPath(keys,rkeys,c,code[i],cache))
        return paths

    def append(a1,a2):
        a = []
        for i in a1:
            for j in a2:
                a += [i + j]
        return a
    

    codes = f.split("\n")
    dpcache = {}
    t = 0
    for c in codes:
        paths = getPaths(keys,rkeys,c,'A',{})
        
        paths2 = []
        for path in paths:
            paths2 += getPaths(dkeys,rdkeys,path,'A',dpcache)

        paths3 = []
        for path in paths2:
            paths3 += getPaths(dkeys,rdkeys,path,'A',dpcache)

        t += len(min(paths3,key=lambda path: len(path))) * int(c[:-1])
    return t

def part2():
    keys = {(0,0):'7',(1,0):'8',(2,0):'9',(0,1):'4',(1,1):'5',(2,1):'6',(0,2):'1',(1,2):'2',(2,2):'3',(1,3):'0',(2,3):'A'}
    rkeys = {}

    dkeys = {(1,0):'^',(2,0):'A',(0,1):'<',(1,1):'v',(2,1):'>'}
    rdkeys = {}

    dirs = {'^':(0,-1),'>':(1,0),'v':(0,1),'<':(-1,0)}

    for k,v in keys.items():
        rkeys[v] = k

    for k,v in dkeys.items():
        rdkeys[v] = k
    
    def check(keys,x,y,s):
        for i in s:
            x,y = x + dirs[i][0], y + dirs[i][1]
            if not (x,y) in keys:
                return False
        return True
    def getKeyPath(keys,rkeys,s,e,cache):
        if (s,e) in cache:
            return cache[(s,e)]
    
        sx,sy = rkeys[s]
        ex,ey = rkeys[e]
        xsym = ('<' if sx > ex else '>') * abs(ex - sx)
        ysym = ('^' if sy > ey else 'v') * abs(ey - sy)

        cache[(s,e)] = set()

        if check(keys,sx,sy,xsym + ysym):
            cache[(s,e)].add(xsym + ysym + 'A')
        
        if check(keys,sx,sy,ysym + xsym):
            cache[(s,e)].add(ysym + xsym + 'A')
        return cache[(s,e)]
    
    def getPaths(keys,rkeys,code,start,cache):
        paths = [""]
        for i,c in enumerate(start + code):
            if i == len(code):
                break

            paths = append(paths,getKeyPath(keys,rkeys,c,code[i],cache))
        return paths
    
    def append(a1,a2):
        a = []
        for i in a1:
            for j in a2:
                a += [i + j]
        return a
    
    codes = f.split("\n")
    dpcache = {}
    depth = 25
    
    for i in rdkeys:
        for j in rdkeys:
            getKeyPath(dkeys,rdkeys,i,j,dpcache)
    
    cost = {0:{(i,j):1 for i in rdkeys for j in rdkeys}}
    for i in range(1,depth + 1):
        cost[i] = {}
        for k,v in dpcache.items():
            cost[i][k] = -1
            for s in v:
                s = 'A' + s
                c = 0
                for idx,ch in enumerate(s):
                    if idx == len(s) - 1:
                        break
                    c += cost[i-1][(ch,s[idx+1])]
                if cost[i][k] == -1 or c < cost[i][k]:
                    cost[i][k] = c

    ans = 0
    for c in codes:
        m = -1
        paths = getPaths(keys,rkeys,c,'A',{})
        for path in paths:
            path = 'A' + path
            total = 0
            for idx,ch in enumerate(path):
                if idx == len(path) - 1:
                    break
                total += cost[depth][(ch,path[idx+1])]
            if m == -1 or total < m:
                m = total
        ans += int(c[:-1]) * m
    return ans
print("Part 1:", part1())
print("Part 2:", part2())