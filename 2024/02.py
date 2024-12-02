from aocd import get_data
f = get_data(day=2,year=2024)

def part1():
    c = 0
    for i in f.split("\n"):
        nums = [int(j) for j in i.split(" ")]
        
        safe = True
        inc = nums[1] - nums[0] > 0
        for n in range(len(nums) - 1):
            d = nums[n + 1] - nums[n]
            if not inc:
                d *= -1
    
            if d <= 0 or d > 3:
                safe = False
                break
        if safe:
            c += 1
    return c
        

def part2():
    def isSafe(nums):
        inc = nums[1] - nums[0] > 0
        for n in range(len(nums) - 1):
            d = nums[n + 1] - nums[n]
            if not inc:
                d *= -1
    
            if d <= 0 or d > 3:
                return False
        return True
    
    
    c = 0
    for i in f.split("\n"):
        nums = [int(j) for j in i.split(" ")]
        if(isSafe(nums)):
            c += 1
            continue
        for i in range(len(nums)):
            newList = list(nums)
            del newList[i]
            if(isSafe(newList)):
                c += 1
                break
    return c

print("Part 1:", part1())
print("Part 2:", part2())