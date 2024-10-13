from z3 import *

nano = [tuple([int(j) for j in i.split(",")]) for i in open("text.txt").read().replace("pos=<","").replace(">, r=",",").split("\n")]
print(nano)

def mabs(x):
    return If(x >= 0,x,-x)

op = Optimize()
r = [Int("n" + str(i)) for i in range(len(nano))]
x = Int("x")
y = Int("y")
z = Int("z")
d = Int("d")
c = Int("c")
print(r)
for i,iv in enumerate(nano):
    op.add(r[i] == If(mabs(x - iv[0]) + mabs(y - iv[1]) + mabs(z - iv[2]) <= iv[3],1,0))
op.add(d == mabs(x) + mabs(y) + mabs(z))
op.add(c == sum(r))
op.maximize(c)
ans = op.minimize(d)
op.check()
print("Answer:",op.lower(ans))