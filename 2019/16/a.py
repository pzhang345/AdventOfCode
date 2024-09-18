nums = [int(i) for i in open("text.txt").read()]
print(nums)
for i in range(100):
    newnums = []
    print(i)
    for j in range(len(nums)):
        p = 1
        count = j + 1
        total = 0
        k = j
        while k < len(nums):
            if count == 0:
                p = -p
                k += j + 1
                if k >= len(nums):
                    break
                count = j + 1
            #print(nums[k],pattern[p])
            if p == 1:
                total += nums[k]
            else:
                total -= nums[k]
            count -= 1
            k += 1
        if total < 0:
            total *= -1
        newnums += [total%10]
    #print(newnums)
    nums = newnums
print("Answer:","".join([str(i) for i in nums[0:8]]))
