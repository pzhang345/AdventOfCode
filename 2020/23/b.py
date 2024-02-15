cups = [int (i) for i in list(open("text.txt").read())]
for i in range(1000001):
    if(i > 9):
        cups += [i]
llcups = {}
for i,iv in enumerate(cups):
    llcups[iv] = cups[(i + 1)%len(cups)]
#print(llcups)
curr = cups[0]
for i in range(10000000):
    if i % 100000 == 0:
        print(i)
    first = llcups[curr]
    second = llcups[first]
    third = llcups[second]
    llcups[curr] = llcups[third]
    #print(first,second,third)
    des = (curr - 2) % len(llcups) + 1
    while first == des or second == des or third == des:
        des = (des - 2) % len(llcups) + 1
    llcups[third] = llcups[des]
    llcups[des] = first
    curr = llcups[curr]


print("Answer:", llcups[1] * llcups[llcups[1]])