from aocd import get_data
f = get_data(day=8,year=2024)

def part1():
    d = {}
    for i,iv in enumerate(f.split("\n")):
        for j,jv in enumerate(iv):
            if jv == ".":
                continue

            if not jv in d:
                d[jv] = []
            d[jv] += [(j,i)]

    si = (len(f.split("\n")[0]),len(f.split("\n")))
    
    s = set()

    for k,v in d.items():
        for i in v:
            for j in v:
                if i == j:
                    continue

                dx = i[0] - j[0]
                dy = i[1] - j[1]

                x = i[0] + dx
                y = i[1] + dy

                if(0 <= x < si[0] and 0 <= y < si[1]):
                    s.add((x,y))

    return len(s)

def part2():
    d = {}
    for i,iv in enumerate(f.split("\n")):
        for j,jv in enumerate(iv):
            if jv == ".":
                continue

            if not jv in d:
                d[jv] = []
            d[jv] += [(j,i)]

    si = (len(f.split("\n")[0]),len(f.split("\n")))
    
    s = set()

    for k,v in d.items():
        for i in v:
            for j in v:
                if i == j:
                    continue

                dx = i[0] - j[0]
                dy = i[1] - j[1]

                x = i[0]
                y = i[1]

                while(0 <= x < si[0] and 0 <= y < si[1]):
                    s.add((x,y))
                    x += dx
                    y += dy

    return len(s)


print("Part 1:", part1())
print("Part 2:", part2())