from aocd import get_data
f = get_data(day=17,year=2017)
inc = int(f)

def part1():
    l = [0]
    ind = 1
    for i in range(1,2018):
        ind = (inc + ind + 1)%len(l)
        l[ind:ind] = [i]
    return l[ind + 1]

def part2():
    ind = 0
    for i in range(1,50000001):
        ind = (inc + ind)%i
        if ind == 0:
            ans = i
        ind += 1
    return ans

print("Part 1:", part1())
print("Part 2:", part2())