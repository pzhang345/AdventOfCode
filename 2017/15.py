from aocd import get_data
f = get_data(day=15,year=2017)
sa,sb = [int(i.split(" ")[-1]) for i in f.split("\n")]

def part1():
    a,b = sa,sb
    mod1 = 2147483647
    mod2 = 2 ** 16
    c = 0
    for i in range(40000000):
        a = (a * 16807) % mod1
        b = (b * 48271) % mod1
        if a%mod2 == b%mod2:
            c += 1
    return c

def part2():
    a,b = sa,sb
    mod1 = 2147483647
    mod2 = 2 ** 16
    c = 0
    for i in range(5000000):
        a = (a * 16807) % mod1
        b = (b * 48271) % mod1

        while a%4 != 0:
            a = (a * 16807) % mod1

        while b%8 != 0:
            b = (b * 48271) % mod1

        if a%mod2 == b%mod2:
            c += 1
    return c

print("Part 1:", part1())
print("Part 2:", part2())