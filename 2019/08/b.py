pixel = [[2] * 25 for i in range(6)]
line = open("text.txt").read().strip()
for i in range(int(len(line)/(25 * 6))):
    for j in range((25 * 6)):
        if int(line[i * 25 * 6 + j]) != 2 and pixel[int(j/25)][j%25] == 2:
            pixel[int(j/25)][j%25] = int(line[i * 25 * 6 + j])
print("Answer:")
for i in pixel:
    dis = ''
    for j in i:
        if j == 0:
            dis += ' '
        elif j == 1:
            dis += '#'
    print(dis)