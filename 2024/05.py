from aocd import get_data
f = get_data(day=5,year=2024)


rules,pages = f.split("\n\n")
r = {}
rr = {}
for i in rules.split("\n"):
    f,b = i.split("|")
    if not f in r:
        r[f] = []
    r[f] += [b]

    if not b in rr:
        rr[b] = []
    rr[b] += [f]
p = [i.split(",") for i in pages.split("\n")]

def part1():
    t = 0
    for i in p:
        isValid = True
        before = []
        for j in i:
            if not j in r:
                continue
            for k in r[j]:
                if k in before:
                    isValid = False
                    break
            if not isValid:
                break
            before += [j]

        if isValid:
            t += int(i[len(i)//2])
    return t


def part2():
    t = 0
    for i in p:
        l = list(i)
        idx = 0
        while idx < len(l):
            n = l[idx]
            if not n in rr:
                idx += 1
                continue
            nidx = idx
            good = False
            del l[idx]
            while not good:
                good = True
                for tn in rr[n]:
                    if tn in l[nidx:]:
                        good = False
                        break

                if not good:
                    nidx += 1

            l[nidx:nidx] = [n]
            if nidx == idx:
                idx += 1
    
        if l != i:
            t += int(l[len(i)//2])
    return t

print("Part 1:", part1())
print("Part 2:", part2())