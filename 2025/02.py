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
                
        
    ranges = input.split(",")
    sum = 0
    for r in ranges:
        a, b = r.split("-")
        a_bound = get_bound(a, True)
        b_bound = get_bound(b, False)
        for i in range(a_bound, b_bound + 1):
            sum += int(str(i) * 2) 
        
    return sum


def part2(input: str):
    def get_bound(s, isLower, rl):
        l = len(s)
        if l%rl != 0 and not isLower:
            return int("9" * (l//rl))
        elif l%rl != 0 and isLower:
            return int("1" + "0" * (l//rl))
       
        sections = [int(s[i:i+l//rl]) for i in range(0, l, l//rl)]
                
        for i in range(len(sections)):
            if sections[0] != sections[i]:
                if isLower:
                    if sections[0] < sections[i]:
                        return sections[0] + 1
                    elif sections[0] > sections[i]:
                        return sections[0]
                    
                elif not isLower:
                    if sections[0] < sections[i]:
                        return sections[0]
                    elif sections[0] > sections[i]:
                        return sections[0] - 1
        
        return sections[0]
        
    ranges = input.split(",")
    sum = 0
    for r in ranges:
        a, b = r.split("-")
        added = set()
        for i in range(2,len(b) + 1):
            a_bound = get_bound(a, True, i)
            b_bound = get_bound(b, False, i)
            
            for j in range(a_bound, b_bound + 1):
                num = int(str(j) * i)
                if num not in added:
                    added.add(num)
                    sum += num              
    return sum


run(2, part1, part2)