def CRT(rem,mod):
    total = 1
    for i in mod:
        total *= i
    result = 0
    for i in range(len(rem)):
        result += rem[i] * int(total/mod[i]) * pow(int(total/mod[i]),-1,mod[i])
    return result%total
allsyms = open("text.txt").read().split("\n")[1].split(",")
buses = []
rem = []
for i,iv in enumerate(allsyms):
    if iv != 'x':
        buses += [int(iv)]
        rem += [-i]
print(rem,buses)
print("Answer:",CRT(rem,buses))