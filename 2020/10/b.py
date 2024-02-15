from functools import cache
ints = [0] + [int(i) for i in open("text.txt").read().split("\n")]
ints.sort()
ints += [ints[-1] + 3]
@cache
def getCount(num):
    if num == ints[-1]:
        return 1
    elif num in ints:
        return getCount(num + 1) + getCount(num + 2) + getCount(num + 3)
    return 0

print("Answer:",getCount(0))