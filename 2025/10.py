from utils import run
from collections import deque
from z3 import Optimize, Int

def part1(input: str):
    def solve(target, buttons):
        states_seen = set()
        if target == 0:
            return 0
        
        q = deque([(0,0)])
        states_seen.add(0)
        while len(q) > 0:
            steps, state = q.popleft()
            for i in buttons:
                new = state ^ i
                
                if new in states_seen:
                    continue
                if new == target:
                    return steps + 1
            
                states_seen.add(new)
                q.append((steps + 1, new))
    
    s = 0
    for i in input.split("\n"):
        tokens = i.split(" ")
        light = 0
        for j,jv in enumerate(list(tokens[0][1:-1])):
            if jv == "#":
                light = light | 1 << j
                
        button = [0 for _ in range(len(tokens) - 2)]
        for j,jv in enumerate(tokens[1:-1]):
            button[j] = 0
            for k in jv[1:-1].split(","):
                 button[j] =  button[j] | 1 << int(k)
            
        s += solve(light,button)
        
    return s

def part2(input: str):
    s = 0
    for i in input.split("\n"):
        tokens = i.split(" ")
        joltage = [int(j) for j in tokens[-1][1:-1].split(",")]
        buttons = [[False] * (len(tokens) - 2) for _ in range(len(joltage))]
        
        for j,jv in enumerate(tokens[1:-1]):
            for k in jv[1:-1].split(","):
                 buttons[int(k)][j] = True
            
        solver = Optimize()
        symbols = [Int(f"b{i}") for i in range(len(tokens) - 2)]
        
        for b,j in zip(buttons,joltage):
            equation = 0
            for var,var_in in enumerate(b):
                if var_in:
                    equation += symbols[var]
            solver.add(equation == j)
            
        for symbol in symbols:
            solver.add(symbol >= 0)
        
        solver.minimize(sum(symbols))
        solver.check()
        s += solver.model().evaluate(sum(symbols)).as_long()
    
    return s


run(10, part1, part2)