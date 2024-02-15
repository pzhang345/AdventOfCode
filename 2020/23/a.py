cups = [int (i) for i in list(open("text.txt").read())]
print(cups)
curr = cups[0]
size = len(cups)
for i in range(100):
    print(i,cups,curr)
    temp = []
    for j in range(3):
        temp += [cups[(cups.index(curr) + 1)%len(cups)]]
        cups.remove(temp[j])
    print(temp)
    des = (curr - 2) % (size) + 1

    while not des in cups:
        print(des)
        des = (des - 2) % (size) + 1
        print(des)
    cups[(cups.index(des) + 1):(cups.index(des) + 1)] = temp
    curr = cups[(cups.index(curr) + 1) % len(cups)]

answer = ""
for i in range(size - 1):
    answer += str(cups[(cups.index(1) + 1 + i)%size])
print("Answer:",answer)