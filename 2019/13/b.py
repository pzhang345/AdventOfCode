prog = {i:int(iv) for i,iv in enumerate(open("text.txt").read().split(","))}
prog[0] = 2
print(prog)
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
def runProgram(pos,arr,input,inputc,rm):
    i = pos
    nums = arr
    rmode = rm
    output = []
    #print(arr)
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
            else:
                return (pos,rmode,output)
                #nums[vals[0][0]] = inputc
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
            return (-1,rmode,output)
        
        else:
            print(nums[i])
            print("ERROR")
            return
def printarr(dict):
    s = ""
    for i in range(23):
        for j in range(43):
            if dict[(j,i)] == 0:
                s += " "
            elif dict[(j,i)] == 1:
                s += "#"
            elif dict[(j,i)] == 2:
                s += "+"
            elif dict[(j,i)] == 3:
                s += "-"
            elif dict[(j,i)] == 4:
                s += "O"
        s += "\n"
    print(s)
has2 = True
p = r = 0
o = []
while p != -1:
    p,r,output = runProgram(p,prog,o,0,r)
    pos = {}
    for i in range(int(len(output)/3)):
        pos[(output[3*i], output[3*i + 1])] = output[3*i + 2]
        if output[3*i + 2] == 3:
            px = output[3*i]
        elif output[3*i + 2] == 4:
            bx = output[3*i]
    if px > bx:
        o = [-1]
    elif px == bx:
        o = [0]
    elif px < bx:
        o = [1] 
    printarr(pos)
print("Answer:",pos[(-1,0)])