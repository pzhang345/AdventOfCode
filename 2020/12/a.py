bdir = 90
posx = posy = 0
dirs = ['N','E','S','W']
for i in open("text.txt"):
    dir = i[0]
    dist = int(i[1:].strip())
    if dir == 'F':
        dir = dirs[int((bdir%360)/90)]
    
    if dir == 'N':
        posy += dist
    elif dir == 'S':
        posy -= dist
    elif dir == 'E':
        posx += dist
    elif dir == 'W':
        posx -= dist
    elif dir == 'R':
        bdir += dist
    elif dir == 'L':
        bdir -= dist
        
print("Answer:", abs(posx) + abs(posy))