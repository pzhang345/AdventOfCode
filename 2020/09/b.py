list = []
nums = []
count = 0
for line in open("text.txt"):
    num = int(line.strip())
    nums += [num]
    if count < 25:
        list += [num]
    else:
        print(list)
        s = set()
        has = False
        for i in list:
            if i not in s:
                s.add(num-i)
            else:
                has = True
                break
        if has:
            del list[0]
            list += [num]
        else:
            break
    count += 1

for i,iv in enumerate(nums):
    min = max = sum = iv
    for j in range(i + 1,len(nums)):
        sum += nums[j]
        if(nums[j] < min):
            min = nums[j]
        if(nums[j] > max):
            max = nums[j]
        if(sum >= nums[-1]):
            break
    if sum == nums[-1]:
        break

print("Answer:",max + min)