from functools import cache
orbits = {}
@cache
def getOrbit(str):
    if not str in orbits:
        return 0
    else:
        return getOrbit(orbits[str]) + 1

for line in open("text.txt"):
    o1,o2 = line.strip().split(")")
    orbits[o2] = o1

sum = 0
for i in orbits:
    sum += getOrbit(i)
print("Answer:",sum)