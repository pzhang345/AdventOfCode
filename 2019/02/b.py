org = [int(i) for i in open("text.txt").read().split(",")]
b = False
for n in range(100):
    for v in range(100):
        nums = list(org)
        nums[1:3] = [n,v]
        for i in range(0,len(nums),4):
            if(nums[i] == 1):
                nums[nums[i + 3]] = nums[nums[i + 2]] + nums[nums[i + 1]]
            elif(nums[i] == 2):
                nums[nums[i + 3]] = nums[nums[i + 2]] * nums[nums[i + 1]]
            elif(nums[i] == 99):
                if(nums[0] == 19690720):
                    b = True
                    answer = n * 100 + v
                break
            else:
                break
        if b:
            break
    if b:
        break
print("Answer:",answer)