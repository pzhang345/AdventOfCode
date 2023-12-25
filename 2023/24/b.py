#Got a hint for this one
#Saw many people were using z3 and sympy
#
import sympy

f = open("text.txt", "r")
hail = []
for i in f:
    hail += [i.replace('@',',').replace(' ', '').replace('\n','').split(',')]
for i in range(len(hail)):
    for j in range(len(hail[i])):
        hail[i][j] = int(hail[i][j])
print(hail)
eq = [0]
x = sympy.Symbol('x')
y = sympy.Symbol('y')
z = sympy.Symbol('z')
vx = sympy.Symbol('vx')
vy = sympy.Symbol('vy')
vz = sympy.Symbol('vz')
for i in range(3):
    psx = hail[i][0]
    psy = hail[i][1]
    psz = hail[i][2]
    pvx = hail[i][3]
    pvy = hail[i][4]
    pvz = hail[i][5]
    eq += [(x - psx) / (pvx - vx) - (y - psy) / (pvy - vy)]
    eq += [(x - psx) / (pvx - vx) - (z - psz) / (pvz - vz)]
    eq += [(y - psy) / (pvy - vy) - (z - psz) / (pvz - vz)]

    
answer = sympy.solve(eq)
print("Answer:",answer[0][x] + answer[0][y] + answer[0][z])