posx = posy = 0
wx = 10
wy = 1
dirs = [[1,1],[-1,1],[-1,-1],[1,-1]]
for i in open("text.txt"):
    dir = i[0]
    dist = int(i[1:].strip())
    if dir == 'F':
        posx += wx * dist
        posy += wy * dist
    elif dir == 'N':
        wy += dist
    elif dir == 'S':
        wy -= dist
    elif dir == 'E':
        wx += dist
    elif dir == 'W':
        wx -= dist
    elif dir == 'R':
        if(dist % 180 == 90):
            temp = wx
            wx = wy
            wy = temp
        dy,dx = dirs[int((dist%360)/90)]
        wx *= dx
        wy *= dy
    elif dir == 'L':
        if(dist % 180 == 90):
            temp = wx
            wx = wy
            wy = temp
        dy,dx = dirs[int((-dist%360)/90)]
        wx *= dx
        wy *= dy
    #print(posx,posy,wy,wx)
        
        
print("Answer:", abs(posx) + abs(posy))