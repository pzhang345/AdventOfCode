grid = open("text.txt").read().split("\n")

carts = {}
dirs = [(0,-1),(1,0),(0,1),(-1,0)]
d = {"^":0, ">": 1, "v":2, "<":3}
turn = [-1,0,1]
count = 0
for i,iv in enumerate(grid):
    for j,jv in enumerate(iv):
        if jv in d:
            carts[count] = [j,i,d[jv],0]
            count += 1

for i in range(len(grid)):
    grid[i] = grid[i].replace("^","|").replace("v","|").replace("<","-").replace(">","-")
print(carts)
ans = 0
while len(carts) > 1:
    dpos = {}
    for k,v in carts.items():
        dpos[(v[1],v[0])] = k
    print(dpos)
    kord = sorted(dpos)

    # print(kord)
    pos = {}
    for k in kord:
        pos[(k[1],k[0])] = dpos[k]
    
    de = []
    for i,k in enumerate(kord):
        if not dpos[k] in carts:
            continue
        cart = carts[dpos[k]]
        # print(cart)
        del pos[(cart[0],cart[1])]
        cart[0] += dirs[cart[2]][0]
        cart[1] += dirs[cart[2]][1]

        x,y = cart[0:2]
        # print(cart)
        
        # print(cart)
        if grid[y][x] == "\\":
            if cart[2] % 2 == 0:
                incr = -1
            else:
                incr = 1
            
            cart[2] = (cart[2] + incr) % 4

        elif grid[y][x] == "/":
            if cart[2] % 2 == 0:
                incr = 1
            else:
                incr = -1

            cart[2] = (cart[2] + incr) % 4

        elif grid[y][x] == "+":
            cart[2] = (cart[2] + turn[cart[3]]) % 4
            cart[3] = (cart[3] + 1) % 3
        # print(cart)
        if (x,y) in pos:
            del carts[pos[(x,y)]]
            del carts[dpos[k]]
            del pos[(x,y)]
        
        pos[(x,y)] = dpos[k]
    # for y,yv in enumerate(grid):
    #     s = ""
    #     for x,xv in enumerate(yv):
    #         if (x,y) in pos:
    #             s += "#"
    #         else:
    #             s += xv
    #     print(s)
    # print()
print(carts)
ans = [str(i) for i in carts[list(carts)[0]][0:2]]
print("Answer:",",".join(ans))
