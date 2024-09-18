nums = [int(i) for i in open("text.txt").read().split(",")]
nums[1:3] = [12,2]
for i in range(0,len(nums),4):
    if(nums[i] == 1):
        nums[nums[i + 3]] = nums[nums[i + 2]] + nums[nums[i + 1]]
    elif(nums[i] == 2):
        nums[nums[i + 3]] = nums[nums[i + 2]] * nums[nums[i + 1]]
    elif(nums[i] == 99):
        break
print("Answer:",nums[0])