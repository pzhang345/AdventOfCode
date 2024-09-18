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
def runProgram(code,pos,input,inputc = -1,rm = 0):
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
            #print("END")
            return (nums,-1,rmode,output)
        
        else:
            print(nums[i])
            print("ERROR")
            return
done = False
for i in range(5,-1,-1):
    for j in range(5,-1,-1):
        print(j,i)
        if runProgram(prog,0,[j,i])[3][0] == 1:  
            bottomc = {i:j}
            bottomcoord = [j,i]
            if not done:
                topcoord = [j,i]
            done = True
    if done:
        break
            
size = 100
print(runProgram(prog,0,bottomcoord)[3][0])
while True:
    print(bottomcoord,topcoord)
    if bottomcoord[1] < topcoord[1] + size:
        bottomcoord[1] += 1
        while runProgram(prog,0,bottomcoord)[3][0] == 0:
            bottomcoord[0] += 1
        bottomc[bottomcoord[1]] = bottomcoord[0]
    else:
        if topcoord[0] - bottomc[topcoord[1] + size - 1] >= size - 1:
            answer = topcoord[1] + bottomc[topcoord[1] + size - 1] * 10000
            break
        topcoord[0] += 1
        while runProgram(prog,0,topcoord)[3][0] == 0:
            topcoord[1] += 1
print("Answer:",answer)
