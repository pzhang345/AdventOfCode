from aocd import get_data
f = get_data(day=7,year=2024)


def part1():
    def findC(nums,idx):
        if(idx == 0):
            return [nums[0]]
        v = []
        for i in findC(nums,idx - 1):
            v += [nums[idx] + i,nums[idx] * i]
        return v
    
    s = 0
    for i in f.split("\n"):
        ans,nums = i.split(":")
        ans = int(ans)
        nums = [int(i) for i in nums.strip().split()]
        pos = findC(nums,len(nums)-1)
        if ans in pos:
            s += ans
    return s

def part2():
    def findC(nums,idx):
        if(idx == 0):
            return [nums[0]]
        v = []
        for i in findC(nums,idx - 1):
            v += [nums[idx] + i,nums[idx] * i, int(str(i) + str(nums[idx]))]
        return v
    
    s = 0
    for i in f.split("\n"):
        ans,nums = i.split(":")
        ans = int(ans)
        nums = [int(i) for i in nums.strip().split()]
        pos = findC(nums,len(nums)-1)
        if ans in pos:
            s += ans
    return s


print("Part 1:", part1())
print("Part 2:", part2())