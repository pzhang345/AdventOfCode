graph = open("text.txt").read().splitlines()
def getCount(right,down):
    count = 0
    for i in range(int(len(graph)/down)):
        print(i)
        if(graph[i * down][(i * right)%len(graph[i])] == '#'):
            count += 1
    return count

answer = getCount(1,1) * getCount(3,1) * getCount(5,1) * getCount(7,1) * getCount(1,2)
print("Answer:", answer)