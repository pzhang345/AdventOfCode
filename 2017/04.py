from aocd import get_data
f = get_data(day=4,year=2017)

print("Part 1:",len([0 for i in f.split("\n") if len([0 for j1,jv1 in enumerate(i.split()) if len([0 for j2,jv2 in enumerate(i.split()) if j1 != j2 and jv1 == jv2]) > 0]) == 0]))
print("Part 2:",len([0 for i in f.split("\n") if len([0 for j1,jv1 in enumerate(i.split()) if len([0 for j2,jv2 in enumerate(i.split()) if j1 != j2 and sorted(jv1) == sorted(jv2)]) > 0]) == 0]))