orbits = {}
ycount = {}
def getOrbit(str):
    if not str in orbits:
        return 
    else:
        ycount[orbits[str]] = ycount[str] + 1

for line in open("text.txt"):
    o1,o2 = line.strip().split(")")
    orbits[o2] = o1

save = orbits["YOU"]
ycount[save] = 0
while save in orbits:
    ycount[orbits[save]] = ycount[save] + 1
    save = orbits[save]

save = orbits["SAN"]
count = 0
while not save in ycount:
    save = orbits[save]
    count += 1

print("Answer:",count + ycount[save])