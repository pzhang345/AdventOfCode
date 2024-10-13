grid = open("text.txt").read().split("\n")

carts = []
dirs = [(0,-1),(1,0),(0,1),(-1,0)]
d = {"^":0, ">": 1, "v":2, "<":3}
turn = [-1,0,1]
for i,iv in enumerate(grid):
    for j,jv in enumerate(iv):
        if jv in d:
            carts += [[j,i,d[jv],0]]

for i in range(len(grid)):
    grid[i] = grid[i].replace("^","|").replace("v","|").replace("<","-").replace(">","-")
print(carts)
ans = 0
while ans == 0:
    dpos = {}
    for i,iv in enumerate(carts):
        dpos[(iv[1],iv[0])] = i
    kord = sorted(dpos)

    pos = []
    for k in kord:
        pos += [(k[1],k[0])]
    
    for i,k in enumerate(kord):
        cart = carts[dpos[k]]
        # print(cart)
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
            ans = (str(x),str(y))
            break
        pos[i] = (x,y)
    # for y,yv in enumerate(grid):
    #     s = ""
    #     for x,xv in enumerate(yv):
    #         if (x,y) in pos:
    #             s += "#"
    #         else:
    #             s += xv
    #     print(s)
    # print()
print("Answer:",",".join(ans))
