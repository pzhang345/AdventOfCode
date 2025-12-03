from utils import run

def part1(input: str):
    lines = input.split("\n")
    sum = 0 
    for line in lines:
        max = [0, 0]
        for i, char in enumerate(line):
            v = int(char)
            
            if v > max[0] and i < len(line) - 1:
                max[0] = v
                max[1] = 0
            elif v > max[1]:
                max[1] = v
            
        sum += max[0] * 10 + max[1]
    return sum

def part2(input: str):        
    lines = input.split("\n")
    sum = 0 
    for line in lines:
        a = []
        b = [int(i) for i in line]
        for l in range(-11,1):
            if l == 0:
                lines = b
            else:
                lines = b[:l]
            m = max(lines)
            a.append(m)
            i = lines.index(m)
            
            if l != 0:
                b = b[i+1:]
        
        for i,n in enumerate(a):
            sum += n * (10 ** (len(a)-i-1))
        
    return sum


run(3, part1, part2)