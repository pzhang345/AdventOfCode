from collections import deque
from utils import run

def part1(input: str):
    m = {}
    for i in input.split('\n'):
        s = i.split(": ")
        m[s[0]] = s[1].split(" ")
    
    vm = {"out": 1}
    while "you" not in vm:
        for k, v in m.items():
            if k not in vm and all(i in vm for i in v):
                vm[k] = sum(vm[i] for i in v)
        
    return vm["you"]

def part2(input: str):
    m = {}
    for i in input.split('\n'):
        s = i.split(": ")
        m[s[0]] = s[1].split(" ")
    
    vm = {"out": [0,0,0,1]}
    while "svr" not in vm:
        for k, v in m.items():
            if k not in vm and all(i in vm for i in v):
                vm[k] = [sum(vm[i][j] for i in v) for j in range(4)]
                if k == "dac":
                    vm["dac"] = [vm["dac"][2], vm["dac"][3], 0, 0]
                if k == "fft":
                    vm["fft"] = [vm["fft"][1], 0, vm["fft"][3], 0]

    return vm["svr"][0]


run(11, part1, part2)