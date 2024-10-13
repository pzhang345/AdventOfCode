[depth], [tx,ty] = [[int(j) for j in i.split(": ")[1].split(",")] for i in open("text.txt").read().split("\n")]
mod = 20183
smod = 3
cmod = mod * smod
print(depth,tx,ty)
pos = {}
a = 0
for y in range(ty + 1):
    s = ""
    for x in range(tx + 1):
        if y == 0:
            pos[(x,y)] = (depth + 16807 * x) % cmod
        elif x == 0:
            pos[(x,y)] = (depth + 48271 * y) % cmod
        elif x == tx and y == ty:
            pos[(x,y)] = (depth) %  cmod
        else:
            pos[(x,y)] = (pos[(x - 1,y)] * pos[(x,y - 1)] + depth) % cmod
        
        c = (pos[(x,y)] % mod) % 3
        a += c
        if c == 0:
            s += "."
        elif c == 1:
            s += "="
        elif c == 2:
            s += "|"
    print(s)
print("Answer:",a)