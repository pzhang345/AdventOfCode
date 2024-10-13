root = [int(i) for i in open("text.txt").read().strip().split(" ")]
print(root)

def sumMeta(ind):
    c,mn = root[ind:ind + 2]
    ind += 2
    sums = []
    for i in range(c):
        sum,dind = sumMeta(ind)
        sums += [sum]
        ind = dind
    print(sums)
    s = 0
    for i in root[ind:ind + mn]:
        if c == 0:
            s += i
        else:
            if(0 <= i-1 < len(sums)):
                s += sums[i-1]

    ind += mn
    return (s,ind) 

print("Answer:",sumMeta(0)[0])
