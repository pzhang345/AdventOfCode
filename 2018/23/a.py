nano = [tuple([int(j) for j in i.split(",")]) for i in open("text.txt").read().replace("pos=<","").replace(">, r=",",").split("\n")]
print(nano)

nano = sorted(nano,key=lambda i: i[3],reverse=True)

def dist(n1,n2):
    return abs(n1[0] - n2[0]) + abs(n1[1] - n2[1]) + abs(n1[2] - n2[2])

c = 0
for i in nano:
    if dist(nano[0],i) <= nano[0][3]:
        c += 1
print("Answer:",c)
