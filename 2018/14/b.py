num = [int(i) for i in open("text.txt").read()]
e = [0,1]
r = [3,7]
ind = []
done = False
while not done:
    de = [r[e[0]],r[e[1]]]
    new = r[e[0]] + r[e[1]]
    arr = [int(i) for i in str(new)]
    r += arr
    #print(arr)
    for i in arr:
        for j in range(len(ind)):
            if num[ind[j]] == i:
                ind[j] += 1
                if ind[j] > 4:
                    print(ind)
                if ind[j] == len(num):
                    done = True
                    break
            else:
                del ind[j]
        if done:
            break 
        if i == num[0]:
            ind += [1]
    for i in range(len(e)):
        e[i] = (e[i] + 1 + de[i]) % len(r)
ans = len(r) - len(num)
dif = 0
while num[-1] != r[-dif-1]:
    dif += 1
print("Answer:",ans - dif)