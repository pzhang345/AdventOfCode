from aocd import get_data
f = get_data(day=12,year=2017)
d = {int(j[0]):[int(k) for k in j[1].split(",")] for i in f.split("\n") if (j := i.split(" <-> "))}

from collections import deque
def part1():
    de = deque([0])
    has = set([0])
    while de:
        curr = de.pop()
        for i in d[curr]:
            if not i in has:
                has.add(i)
                de.append(i)

    return len(has)


def part2():
    notdone = list(d.keys())
    count = 0
    while len(notdone) != 0:
        de = deque([notdone[0]])
        has = set([notdone[0]])
        while de:
            curr = de.pop()
            notdone.remove(curr)
            for i in d[curr]:
                if not i in has:
                    has.add(i)
                    de.append(i)
        count += 1
    return count

print("Part 1:", part1())
print("Part 2:", part2())