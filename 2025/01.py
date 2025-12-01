from utils import run

def part1(input: str):
    input = input.strip().split("\n")
    n = 50
    count = 0
    for line in input:
        sign = 1
        if line[0] == "L":
            sign =  -1
            
        c = line[1:]
        num = int(c) * sign
        
        n = (n + num) % 100
        if n == 0:
            count += 1
    return count

def part2(input: str):
    input = input.strip().split("\n")
    n = 50
    count = 0
    for line in input:
        sign = 1
        if line[0] == "L":
            sign =  -1
            
        c = line[1:]        
        for i in range(int(c)):
            n = (n + sign) % 100
            if n == 0:
                count += 1
        
    return count


run(1, part1, part2)