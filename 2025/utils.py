import argparse
import os
import sys
import subprocess
import time
from argparse import ArgumentParser

YEAR = 2025
PATH = os.path.dirname(os.path.abspath(__file__))
BASE_DIR = os.path.realpath(os.path.join(PATH, ".."))

def get_input(day):
    if not os.path.exists(os.path.join(BASE_DIR, "Inputs", str(YEAR), f"{str(day).zfill(2)}.txt")):
        result = subprocess.run(["py", os.path.join(BASE_DIR, "getInput.py"), str(YEAR), str(day)])
        if result.returncode != 0:
            sys.exit(1)
        
    with open(os.path.join(BASE_DIR, "Inputs", str(YEAR), f"{str(day).zfill(2)}.txt")) as f:
        return f.read()

def get_test():
    with open(os.path.join(PATH, "test.txt")) as f:
        return f.read()
    
def run_part(name, part, input):
    start_time = time.perf_counter_ns()
    ans = part(input)
    end_time = time.perf_counter_ns()
    print(f"{name}: {ans}")
    
    elasped = end_time - start_time
    
    if elasped >= 1_000_000_000:
        elasped /= 1_000_000_000
        print(f"Elasped: {elasped:.7f}s")
    elif elasped >= 1_000_000:
        elasped /= 1_000_000
        print(f"Elasped: {elasped:.4f}ms")
    elif elasped >= 1_000:
        elasped /= 1_000
        print(f"Elasped: {elasped:.1f}µs")
    else:
        print(f"Elasped: {elasped}ns")
    
    return ans

def run(day, part1, part2, part=None, test=False, cli_args=True):
    if cli_args:
        parser = ArgumentParser(add_help=False)
        parser.add_argument("-p", "--part", type=int, choices=[1,2], default=part)
        parser.add_argument("-t", "--test", type=bool, nargs="?", default=test, const=True)
        args = parser.parse_args(sys.argv[1:])
        test = args.test
        part = args.part
        
    if test:
        input = get_test()
    else:
        input = get_input(day)
    
    if part == None or part == 1:
        run_part("Part 1", part1, input)
        
    if part == None or part == 2:
        run_part("Part 2", part2, input)
    
    
    