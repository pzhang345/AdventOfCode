from aocd import get_data
from functools import cache
f = get_data(day=19,year=2024)
print(f)

def part1():
    combo,l = f.split("\n\n")
    combo = combo.split(", ")
    @cache
    def check(str):
        if str == "":
            return True

        for i in combo:
            if len(i) <= len(str) and i == str[0:len(i)] and check(str[len(i):]):
                return True
        return False
    
    l = l.split("\n")
    c = 0
    for i in l:
        if check(i):
            c += 1
    return c

def part2():
    combo,l = f.split("\n\n")
    combo = combo.split(", ")
    @cache
    def check(str):
        if str == "":
            return 1
        
        c = 0
        for i in combo:
            if len(i) <= len(str) and i == str[0:len(i)]:
                c += check(str[len(i):])
        return c
    
    l = l.split("\n")
    c = 0
    for i in l:
        c += check(i)
    return c

print("Part 1:", part1())
print("Part 2:", part2())