nums = [int(i) for i in open("text.txt").read()] * 10000
loc = int("".join([str(i) for i in nums[0:7]]))

if len(nums) / 2 > loc:
    print("ERROR")
    0/0

nums = nums[loc:]
print(len(nums))
for i in range(100):
    newnums = []
    print(i)
    total = 0
    for j in range(len(nums)):
        total = (total + nums[-j-1]) % 10
        newnums += [total]
        #print(total)
    for j,jv in enumerate(newnums):
        nums[-j-1] = jv
print("Answer:","".join([str(i) for i in nums[0:8]]))
