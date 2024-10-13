root = [int(i) for i in open("text.txt").read().strip().split(" ")]
print(root)

def sumMeta(ind):
    c,mn = root[ind:ind + 2]
    ind += 2
    s = 0
    for i in range(c):
        ds,dind = sumMeta(ind)
        s += ds
        ind = dind
    for i in root[ind:ind + mn]:
        s += i
    ind += mn
    return (s,ind) 

print("Answer:",sumMeta(0)[0])
