door, key = [int(i) for i in open("test.txt").read().split("\n")]
print(door,key)
dval = 1
dcount = 0
kval = 1
while dval != door:
    dval = (7 * dval) % 20201227
    dcount += 1
for i in range(dcount):
    kval = (key * kval) % 20201227
print(dcount)
print("Answer:", kval)
