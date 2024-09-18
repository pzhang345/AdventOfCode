nums = [int(i) for i in open("text.txt").read().split(",")]
i = 0
output = 0
def getnums(vals):
    print(vals)
    arr = []
    par = vals[0]
    par = int(par/100)
    print(par)
    for j in range(1,len(vals)):
        if par % 10 == 0:
            arr += [nums[vals[j]]]
        elif par % 10 == 1:
            arr += [vals[j]]
        par = int(par/10)
    print(arr)
    return arr
while i < len(nums):
    if(nums[i] == 1100):
        break
    print("ROW:",i,len(nums),nums[i])
    if(nums[i]%100 == 1):
        vals = getnums(nums[i:i+3])
        if(int(nums[i] /10000)):
            print("ERROR")
            break
        nums[nums[i + 3]] = vals[1] + vals[0]
        i += 4
    elif(nums[i]%100 == 2):
        vals = getnums(nums[i:i+3])
        if(int(nums[i] /10000)):
            print("ERROR")
            break
        nums[nums[i + 3]] = vals[1] * vals[0]
        i += 4
    elif(nums[i] == 3):
        nums[nums[i + 1]] = 1
        i += 2
    elif(nums[i]%100 == 4):
        vals = getnums(nums[i:i+2])
        if(output == 0):
            print(vals[0])
            output = vals[0]
        else:
            print("Error")
            break
        i += 2
    elif(nums[i] == 99):
        print("END")
        break
print("Answer:",output)