graph = open("text.txt").read().splitlines()
count = 0
for i in range(len(graph)):
    print(i)
    if(graph[i][(3 * i)%len(graph[i])] == '#'):
        count += 1

print("Answer:", count)