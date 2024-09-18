from itertools import permutations;
prog = [int(i) for i in open("text.txt").read().split(",")]

def getnums(nums,vals):
    #print(vals)
    arr = []
    par = vals[0]
    par = int(par/100)
    #print(par)
    for j in range(1,len(vals)):
        if par % 10 == 0:
            arr += [nums[vals[j]]]
        elif par % 10 == 1:
            arr += [vals[j]]
        par = int(par/10)
    #print(arr)
    return arr
def runProgram(pos,arr,input,inputc):
    i = pos
    nums = arr
    save = input[0]
    while i < len(nums):
        #print("ROW:",i,len(nums),nums[i])
        if nums[i] == 0:
            break

        if nums[i]%100 == 1:
            vals = getnums(nums,nums[i:i+3])
            if int(nums[i] /10000):
                print("ERROR")
                return
            nums[nums[i + 3]] = vals[1] + vals[0]
            i += 4
        
        elif nums[i]%100 == 2:
            vals = getnums(nums,nums[i:i+3])
            if int(nums[i] /10000):
                print("ERROR")
                return
            nums[nums[i + 3]] = vals[1] * vals[0]
            i += 4
        
        elif nums[i] == 3:
            if len(input) == 0:
                nums[nums[i + 1]] = inputc
            else:
                nums[nums[i + 1]] = input[0]
                input = input[1:]
            i += 2
        elif nums[i]%100 == 4:
            vals = getnums(nums,nums[i:i+2])
            i += 2
            return (i,vals[0])
        
        elif nums[i]%100 == 5:
            vals = getnums(nums,nums[i:i+3])
            if vals[0] == 0:
                i += 3
            else:
                i = vals[1]
        
        elif nums[i]%100 == 6:
            vals = getnums(nums,nums[i:i+3])
            if vals[0] == 0:
                i = vals[1]
            else:
                i += 3
        
        elif nums[i]%100 == 7:
            vals = getnums(nums,nums[i:i+3])
            if vals[0] < vals[1]:
                nums[nums[i + 3]] = 1
            else:
                nums[nums[i + 3]] = 0
            i += 4
        
        elif nums[i]%100 == 8:
            vals = getnums(nums,nums[i:i+3])
            if vals[0] == vals[1]:
                nums[nums[i + 3]] = 1
            else:
                nums[nums[i + 3]] = 0
            i += 4
        
        elif nums[i] == 99:
            print("END")
            return (-1,save)
max = 0
for th in permutations(range(5,10)):
    input = 0
    pos = [0,0,0,0,0]
    thr = []
    for i in range(5):
        thr += [list(prog)]
    print(th)
    first = True
    while(True):
        for i,iv in enumerate(th):
            #print([iv] + [input])
            if (first):
                pos[i],input = runProgram(pos[i],thr[i],[iv] + [input],0)
            else:
                pos[i],input = runProgram(pos[i],thr[i],[input],0)
            #print(input)
            if pos[i] == -1:
                break
        first = False
        if pos[0] == -1:
                break
    if(input > max):
        max = input
print("Answer:",max)