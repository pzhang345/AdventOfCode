from aocd import get_data
f = get_data(day=2,year=2017)

print("Part 1:",sum([max([int(j) for j in i.split()]) - min([int(j) for j in i.split()]) for i in f.split("\n")]))
print("Part 2:",sum([[x[0] for j1 in i.split() if (x := [int(j2)//int(j1) for j2 in i.split() if int(j2) % int(j1) == 0 and j2 != j1]) and x != []][0] for i in f.split("\n")]))