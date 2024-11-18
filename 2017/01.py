from aocd import get_data
f = get_data(day=1,year=2017)

print("Part 1:", sum([int(iv) for i,iv in enumerate(f) if f[i-1] == iv]))
print("Part 2:",sum([int(iv) for i,iv in enumerate(f) if f[(i + len(f)//2) %len(f)] == iv]))