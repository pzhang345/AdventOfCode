from collections import deque
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
def runProgram(code,pos,input,inputc = None,rm = 0,out = []):
    nums = dict(code)
    i = pos
    rmode = rm
    output = out
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
            #print(i)
            vals = getnums(nums,i,2,rmode)
            # print(len(input))
            if(len(input) != 0):
                nums[vals[0][0]] = input[0]
                input = input[1:]
            elif inputc == None:
                return (nums,i,rmode,output)
            else:
                # print("a")
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
        return [(nums,i,rmode),output,input]


network = [(dict(prog),0,0) for i in range(50)]
inputs = [[i] for i in range(50)]
outputs = [[] for i in range(50)]
NAT = []
done = False
isIdle = [False] * 50
hasGone = [deque([0] * 11) for i in range(50)]
hasSent = set()
idlepos = deque([73,75,79,82,85,73,75,79,82,85,73])
while not done:
    for i in range(50):
        if isIdle[i]:
            continue
        # print(inputs)
        network[i],outputs[i],inputs[i] = runProgram(network[i][0],network[i][1],inputs[i],-1,network[i][2],outputs[i])
        pos = network[i][1]

        hasGone[i].append(pos)
        hasGone[i].popleft()
        
        if hasGone[i] == idlepos:
            isIdle[i] = True
        
        # if outputs[i] == [] and inputs[i] == []:
        #     if posrm in hasGone[i]:
        #         isIdle[i] = True
        #     else:
        #         hasGone[i].add(posrm)
        # else:
        #     isIdle[i] = False
        #     hasGone[i] = set()
        #print(network[i][1:]) 
        while len(outputs[i]) >= 3:
            print("OUTPUT:",outputs[i],i)
            isIdle[i] = False
            hasGone[i] = deque([0] * 11)
            if outputs[i][0] == 255:
                NAT = outputs[i][1:3]
                outputs[i] = outputs[i][3:]
            else:
                loc = outputs[i][0]
                inputs[loc] += outputs[i][1:3]
                isIdle[loc] = False
                hasGone[loc] = deque([0] * 11)
                outputs[i] = outputs[i][3:]
            #print(inputs)
            #print("INPUT:",inputs)
        if done:
            break
    # print(hasGone[0])
    #if NAT != []:
        #print(isIdle)
    if all(isIdle):
        inputs[0] += NAT
        if NAT[1] in hasSent:
            break
        else:
            hasSent.add(NAT[1])
            hasGone[0] = deque([0] * 11)
            isIdle[0] = False
#print(hasGone)
print(hasSent)
print("Answer:",NAT[1])