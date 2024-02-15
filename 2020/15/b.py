arr = open("text.txt").read().split(",")
dict = {}
needadd = 0
for i in range(30000000):
    if(len(arr) != 0):
        dict[int(arr[0])] = i
        needadd = int(arr[0])
        del arr[0]
    else:
        if needadd in dict:
            temp = needadd
            needadd = i - 1 - dict[needadd]
        else:
            temp = needadd
            needadd = 0
        dict[temp] = i - 1
    #print(needadd,dict)
    if(i % 1000000 == 0):
        print(i)
print("Answer:",needadd)