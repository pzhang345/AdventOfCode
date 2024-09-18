prog = {i:int(iv) for i,iv in enumerate(open("text.txt").read().split(","))}
class tile:
    def __init__(self,code,pos,rm,num):
        self.code = code
        self.pos = pos
        self.rm = rm
        self.num = num
def getnums(nums,i,len,rmode):
    vals = []
    for j in range(len):
        if (i + j) in nums:
            vals += [nums[i + j]]
        else:
            vals += [0]
    #print(vals,i,rmode)
    arr = []
    par = int(vals[0]/100)
    #print(par)
    for j in range(1,len):
        if par % 10 == 0:
            if vals[j] in nums:
                arr += [(vals[j],nums[vals[j]])]
            else:
                arr += [(vals[j],0)]
        elif par % 10 == 1:
            arr += [(-1,vals[j])]
        elif par % 10 == 2:
            if (vals[j] + rmode) in nums:
                arr += [(vals[j] + rmode,nums[vals[j] + rmode])]
            else:
                arr += [(vals[j] + rmode,0)]
        par = int(par/10)
    #print(arr)
    return arr
def runProgram(code,pos,input,inputc,rm):
    nums = dict(code)
    i = pos
    rmode = rm
    output = []
    if not type(input) is list:
        input = [input]
    #print(code)
    while i < len(nums):
        #print("ROW:",i,nums[i])
        if nums[i]%100 == 1:
            vals = getnums(nums,i,4,rmode)
            nums[vals[2][0]] = vals[1][1] + vals[0][1]
            i += 4
        
        elif nums[i]%100 == 2:
            vals = getnums(nums,i,4,rmode)
            nums[vals[2][0]] = vals[1][1] * vals[0][1]
            i += 4
        
        elif nums[i]%100 == 3:
            vals = getnums(nums,i,2,rmode)
            if(len(input) != 0):
                nums[vals[0][0]] = input[0]
                input = input[1:]
            elif inputc == -1:
                return (nums,i,rmode,output)
            else:
                nums[vals[0][0]] = inputc
            i += 2
        
        elif nums[i]%100 == 4:
            vals = getnums(nums,i,2,rmode)
            output += [vals[0][1]]
            #print(output)
            i += 2
        
        elif nums[i]%100 == 5:
            vals = getnums(nums,i,3,rmode)
            if vals[0][1] == 0:
                i += 3
            else:
                i = vals[1][1]
        
        elif nums[i]%100 == 6:
            vals = getnums(nums,i,3,rmode)
            if vals[0][1] == 0:
                i = vals[1][1]
            else:
                i += 3
        
        elif nums[i]%100 == 7:
            vals = getnums(nums,i,4,rmode)
            if vals[0][1] < vals[1][1]:
                nums[vals[2][0]] = 1
            else:
                nums[vals[2][0]] = 0
            i += 4
        
        elif nums[i]%100 == 8:
            vals = getnums(nums,i,4,rmode)
            #print(vals[0][1],vals[1][1],vals)
            if vals[0][1] == vals[1][1]:
                nums[vals[2][0]] = 1
            else:
                nums[vals[2][0]] = 0
            i += 4
        
        elif nums[i]%100 == 9:
            vals = getnums(nums,i,2,rmode)
            rmode += vals[0][1]
            #print(rmode)
            i += 2

        elif nums[i] == 99:
            print("END")
            return (nums,-1,rmode,output)
        
        else:
            print(nums[i])
            print("ERROR")
            return
def nextCord(x,y,dir):
    if dir == 0:
            y -= 1
    elif dir == 1:
        x += 1
    elif dir == 2:
        y += 1
    elif dir == 3:
        x -= 1
    return (x,y)
def findrep(moves,letter,max=10):
    i = 0
    while moves[i] != "L" and moves[i] != "R":
        i += 1
    initseq = moves[i:i+4]
    #print(initseq)
    apos = []
    i = 0
    while i < len(moves) - len(initseq):
        while moves[i] != "L" and moves[i] != "R":
            i += 1
        isin = True
        for j in range(len(initseq)):
            if moves[i + j] != initseq[j]:
                isin = False
                break
        if isin:
            apos += [i]
        i += 2
    while True:
        newarr = moves[apos[0]:apos[0] + len(initseq) + 2]
        if len(newarr)>max:
            break
        #print(newarr)
        next = True
        for i in apos:
            for j in range(2):
                try:
                    if moves[i + len(initseq) + j] != newarr[-2+j]:
                        #print(moves[i + len(initseq) + j])
                        next = False
                        break
                except:
                    next = False
                    break
            if not next:
                break
        if next:
            initseq = newarr
        else:
            break
    for i in range(len(apos)):
        moves[apos[-i-1]:apos[-i-1] + len(initseq)] = letter
    return (initseq,moves)
out = runProgram(prog,0,[],-1,0)[3]
print(out)
ind = 0
s = ""
for i in out:
    s += chr(i)
#s = open("test.txt").read()
print(s)
grid = [list(i) for i in s.strip().split("\n")]
for i,iv in enumerate(grid):
    for j,jv in enumerate(iv):
        if jv != "." and jv != "#":
            y = i
            x = j
            if jv == "^":
                dir = 0
            elif jv == ">":
                dir = 1
            elif jv == "v":
                dir = 2
            elif jv == "<":
                dir = 3

grid[y][x] = "#"
con = True
moves = []
while con:
    count = 0
    while grid[y][x] == "#":
        testx,testy = nextCord(x,y,dir)
        if 0 <= testx < len(grid[0]) and 0 <= testy < len(grid) and grid[testy][testx] == "#":
            x = testx
            y = testy
            count += 1
        else:
            break
    if count != 0:
        moves += [str(count)]
    leftx,lefty = nextCord(x,y,(dir - 1)%4)
    rightx,righty = nextCord(x,y,(dir + 1)%4)
    con1 = False
    con2 = False
    if 0 <= leftx < len(grid[0]) and 0 <= lefty < len(grid) and grid[lefty][leftx] == "#":
        dir = (dir - 1)%4
        con1 = True
        moves += ["L"]
    elif 0 <= rightx < len(grid[0]) and 0 <= righty < len(grid) and grid[righty][rightx] == "#":
        dir = (dir + 1)%4
        con2 = True
        moves += ["R"]
    print(x,y,leftx,lefty,rightx,righty)
    con = con1 or con2

    for i,iv in enumerate(grid):
        s = ""
        for j,jv in enumerate(iv):
            if i == y and j == x:
                s += "O"
            else:
                s += jv
        print(s)
print(moves)
a = []
b = []
c = []
amax = 10
while True:
    copymoves = list(moves)
    a,copymoves = findrep(copymoves,"A",amax)
    b,copymoves = findrep(copymoves,"B")
    c,copymoves = findrep(copymoves,"C")
    if "L" in copymoves or "R" in copymoves:
        amax -= 2
    else:
        moves = copymoves
        break
print(a)
print(b)
print(c)
print(moves)
instr = ",".join(moves) + "\n" + ",".join(a) + "\n" + ",".join(b) + "\n" + ",".join(c) + "\nn\n"
print(instr)
input = [ord(i) for i in instr]
print(input)
prog[0] = 2
print("Answer:",runProgram(prog,0,input,-1,0)[3][-1])

    