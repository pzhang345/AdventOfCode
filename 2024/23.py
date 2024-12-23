from aocd import get_data
f = get_data(day=23,year=2024)

def part1():
    conn = {}
    for i in f.split("\n"):
        c1,c2 = i.split("-")
        if not c1 in conn:
            conn[c1] = set()
        conn[c1].add(c2)

        if not c2 in conn:
            conn[c2] = set()
        conn[c2].add(c1)

    c = 0
    done = set()
    for i in conn:
        if i[0] != 't':
            continue

        for v1 in conn[i]:
            for v2 in conn[i]:
                if v2 in conn[v1]:
                    done.add(tuple(sorted([i,v1,v2])))
    return len(done)               


def part2():
    conn = {}
    party = {2:set()}
    for i in f.split("\n"):
        c1,c2 = i.split("-")
        if not c1 in conn:
            conn[c1] = set()
        conn[c1].add(c2)

        if not c2 in conn:
            conn[c2] = set()
        conn[c2].add(c1)

        party[2].add((c1,c2))

    idx = 2
    while len(party[idx]) != 0:
        party[idx + 1] = set()
        for p in party[idx]:
            for i in conn[p[0]]:
                inp = True
                for e in p:
                    if not e in conn[i]:
                        inp = False
                        break
                if inp:
                    party[idx + 1].add(tuple(sorted(p + tuple([i]))))
        idx += 1
    
    return ",".join(party[idx-1].pop())

print("Part 1:", part1())
print("Part 2:", part2())