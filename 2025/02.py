from utils import run

def part1(input: str):
    def get_bound(s, isLower):
        l = len(s)
        upperHalf = int(s[:l//2]) if l > 1 else 0
        lowerHalf = int(s[l//2:])
        
        if l%2 != 0 and not isLower:
            return int("9" * (l//2))
        elif l%2 != 0 and isLower:
            return int("1" + "0" * (l//2))
        else:
            if isLower and upperHalf < lowerHalf:
                return upperHalf + 1
            elif not isLower and upperHalf > lowerHalf:
                return upperHalf - 1
            else:
                return upperHalf
                
        
    input = input.split(",")
    sum = 0
    for ranges in input:
        a, b = ranges.split("-")
        a_bound = get_bound(a, True)
        b_bound = get_bound(b, False)
        for i in range(a_bound, b_bound + 1):
            sum += int(str(i) * 2) 
        
    return sum


def part2(input: str):
    def isRepeat(s):
        for c in range(1, len(s)):
            
            if len(s) % c != 0:
                continue
            
            repeat = True
            for i in range(0, len(s), c):
                if s[i:i+c] != s[0:c]:
                    repeat = False
                    break
            if repeat:
                return True
        return False
            
    input = input.split(",")
    sum = 0
    for ranges in input:
        a, b = ranges.split("-")
        a = int(a)
        b = int(b)
        for i in range(a, b + 1):
            if isRepeat(str(i)):
                sum += i
    return sum
run(2, part1, part2)