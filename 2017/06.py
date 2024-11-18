from aocd import get_data
f = get_data(day=6,year=2017)

re = [int(i) for i in f.split()]

def part1():
    r = list(re)
    s = set()
    c = 0
    while not tuple(r) in s:
        s.add(tuple(r))
        h = max(r)
        st = r.index(h) + 1
        r[st - 1] = 0
        r = [iv + h//len(r) + 1 if h%len(r) > (i-st)%len(r) else iv + h//len(r) for i,iv in enumerate(r)]
        c += 1
    return c

def part2():
    r = list(re)
    s = {}
    c = 0
    while not tuple(r) in s:
        s[tuple(r)] = c
        h = max(r)
        st = r.index(h) + 1
        r[st - 1] = 0
        r = [iv + h//len(r) + 1 if h%len(r) > (i-st)%len(r) else iv + h//len(r) for i,iv in enumerate(r)]
        c += 1
    return c - s[tuple(r)]

print("Part 1:", part1())
print("Part 2:", part2())