prog = {i:int(iv) for i,iv in enumerate(open("text.txt").read().split(","))}
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
                nums[vals[0][0]] = inputc
            i += 2
        
        elif nums[i]%100 == 4:
            vals = getnums(nums,i,2,rmode)
            output += [vals[0][1]]
            #print(output)
            i += 2
            if len(output) == 2:
                return (i,rmode,output)
        
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
output = (0,0,0)
color = {}
x = y = dir = 0
while output[0] != -1:
    c = 0
    if (x,y) in color:
        c = color[(x,y)]
    output = runProgram(output[0],prog,[],c,output[1])

    if(output[0] == -1):
        break
    color[(x,y)] = output[2][0]
    
    if output[2][1] == 0:
        dir = (dir - 1)% 4
    elif output[2][1] == 1:
        dir = (dir + 1)% 4
    
    if dir == 0:
        y -= 1
    elif dir == 1:
        x += 1
    elif dir == 2:
        y += 1
    elif dir == 3:
        x -= 1
    # print(output)
    # print(color)
    # print(x,y,dir)
print("Answer:",len(color))