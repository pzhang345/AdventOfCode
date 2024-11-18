from aocd import get_data
f = get_data(day=7,year=2017)

def part1():
    nums = [i.split() for i in f.replace(",","").replace(" ->","").split("\n")]
    parent = {}
    for i in nums:
        for j in i[2:]:
            parent[j] = i[0]
            if not i[0] in parent:
                parent[i[0]] = None
    for i in parent:
        if parent[i] == None:
            return i

def part2():
    tWeight = {}
    def setWeight(node):
        w = weight[node]
        for i in children[node]:
            setWeight(i)
            w += tWeight[i]
        tWeight[node] = w
    
    nums = [i.split() for i in f.replace(",","").replace(" ->","").split("\n")]
    top = part1()
    children = {}
    weight = {}
    for i in nums:
        children[i[0]] = []
        weight[i[0]] = int(i[1][1:-1])
        for j in i[2:]:
            children[i[0]] += [j]
    setWeight(top)
    curr = top
    changed = True
    dw = []
    while changed:
        total = {}
        changed = False
        dw += [0]
        for i in children[curr]:
            if not tWeight[i] in total:
                total[tWeight[i]] = []
            total[tWeight[i]] += [i]
        for i in total:
            if len(total[i]) == 1:
                dw[-1] -= i
                curr = total[i][0]
                changed = True
            else:
                dw[-1] += i
    return weight[curr] + dw[-2]

print("Part 1:", part1())
print("Part 2:", part2())