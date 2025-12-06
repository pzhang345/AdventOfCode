from utils import run

def part1(input: str):
    problems = [i.strip().split() for i in input.split("\n")]
    nums = [[int(j) for j in i] for i in problems[:-1]]
    ops = problems[-1]

    ret = 0
    for idx in range(len(problems[0])):
        op = ops[idx]
        total = 0 if op == "+" else 1
        for n in nums:
            if op == "+":
                total += n[idx]
            elif op == "*":
                total *= n[idx]
        ret += total
    return ret

def part2(input: str):
    problems = input.split("\n")
    nums = problems[:-1]
    ops = problems[-1]
    op_idx = 0
    ret = 0
    while op_idx < len(ops):
        op = ops[op_idx]
        base = op_idx
        count = 1
        while (base + count) < len(ops) and ops[base + count] == " ":
            count += 1
            
        if (base + count) >= len(ops):
            count = len(nums[0]) - base
        
        n = []
        for i in range(count):
            num = 0
            only_space = True
            for j in nums:
                if j[base + i] == " ":
                    continue
                only_space = False
                num = num * 10 + int(j[base + i])
            
            if not only_space:
                n.append(num)
                        
        res = 0
        if op == "+":
            res = sum(n)
        elif op == "*":
            res = 1
            for val in n:
                res *= val
        
        ret += res
        op_idx = base + count
        
    return ret

run(6, part1, part2)