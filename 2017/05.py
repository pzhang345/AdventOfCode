from aocd import get_data
f = get_data(day=5,year=2017)

def part1():
    nums = [int(i) for i in f.split("\n")]
    i = 0
    c = 0
    while 0 <= i < len(nums):
        nums[i] += 1
        i += nums[i] - 1
        c += 1
    return c

def part2():
    nums = [int(i) for i in f.split("\n")]
    i = 0
    c = 0
    while 0 <= i < len(nums):
        if nums[i] >= 3:
            nums[i] -= 1 
            i += nums[i] + 1
        else:
            nums[i] += 1
            i += nums[i] - 1
        c += 1
    return c

print("Part 1:",part1())
print("Part 2:",part2())