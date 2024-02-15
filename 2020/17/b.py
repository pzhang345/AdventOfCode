pos = []
for i,line in enumerate(open("text.txt")):
    for j,jv in enumerate(line.strip()):
        if jv == '#':
            pos += [(j,i,0,0)]
for it in range(6):
    dict = {}
    for i in pos:
        for x in range(-1,2):
            for y in range(-1,2):
                for z in range(-1,2):
                    for w in range(-1,2):
                        if x == y == z == w == 0:
                            continue
                        if (i[0] + x,i[1] + y,i[2] + z,i[3] + w) in dict:
                            dict[(i[0] + x,i[1] + y,i[2] + z,i[3] + w)] += 1
                        else:
                            dict[(i[0] + x,i[1] + y,i[2] + z,i[3] + w)] = 1
    newpos = []
    for k,v in dict.items():
        if v == 3 or (k in pos and v == 2):
            newpos += [k]
    pos = newpos
print("Answer:",len(pos))