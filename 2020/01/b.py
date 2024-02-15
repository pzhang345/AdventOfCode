nums = []
answer = 0
for i in open("text.txt"):
    nums += [int(i.strip())]

for i in range(len(nums)):
    for j in range(i,len(nums)):
        for k in range(j,len(nums)):
            if(nums[i] + nums[j] + nums[k] == 2020):
                answer = nums[i] * nums[j] * nums[k]

print("Answer:",answer)