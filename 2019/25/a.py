# manually went through the maze and picked up all the items and brute forced the security checkpoint
# save commmand to create save state, load command to load save state, hack to brute force password
from itertools import chain, combinations
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
def runProgram(code,pos,input,inputc = None,rm = 0):
    nums = dict(code)
    i = pos
    rmode = rm
    output = []
    #print(code)
    if not type(input) is list:
        input = [input]
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
            elif inputc == None:
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
memory = [prog,0,0]
instr = ""
instrASCII = []
while True:
    out = runProgram(memory[0],memory[1],instrASCII,None,memory[2])
    memory = out[0:3]
    s = ""
    for i in out[3]:
        if 0 <= i < 128:
            s += chr(i)
    print(s)
    if memory[1] == -1:
        break

    instr = input()
    instrASCII = []
    if instr == "save":
        open("save.txt","w").write("\n".join([str(i) for i in memory]))
        break

    elif instr == "load":
        memory = open("save.txt").read().split("\n")
        memory[0] = eval(memory[0])
        memory[1] = int(memory[1])
        memory[2] = int(memory[2])
        # print(memory)

    elif instr == "hack":
        instr = "inv"
        instrASCII = [ord(i) for i in (instr.strip() +"\n")]
        out = runProgram(memory[0],memory[1],instrASCII,None,memory[2])
        memory = out[0:3]
        s = ""
        for i in out[3]:
            if 0 <= i < 128:
                s += chr(i)
        print(s)
        items = [i[2:] for i in s.strip().split("\n")][1:-2]
        print(items)

        allsets = chain.from_iterable(combinations(items, r) for r in range(len(items)+1))
        #print(list(allsets))
        instr = ""
        for j in items:
            instr += "drop " + j + "\n"
        instrASCII = [ord(i) for i in (instr.strip() +"\n")]
        out = runProgram(memory[0],memory[1],instrASCII,None,memory[2])
        memory = out[0:3]

        for i in allsets:
            print(i)
            instr = ""
            for j in i:
                instr += "take " + j + "\n"
            instr += "north"
            instrASCII = [ord(k) for k in (instr.strip() +"\n")]
            out = runProgram(memory[0],memory[1],instrASCII,None,memory[2])
            memory = out[0:3]
            if memory[1] == -1:
                break
            instr = ""
            for j in i:
                instr += "drop " + j + "\n"
            instrASCII = [ord(k) for k in (instr.strip() +"\n")]
            out = runProgram(memory[0],memory[1],instrASCII,None,memory[2])
            memory = out[0:3]
        s = ""
        for i in out[3]:
            if 0 <= i < 128:
                s += chr(i)
        print(s)
        break


    else:
        instrASCII = [ord(i) for i in (instr.strip() +"\n")]