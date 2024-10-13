from datetime import datetime
input = open("text.txt").read().split("\n")
log = {}
for line in input:
    time,action = line[1:].split("] ")
    log[datetime.strptime(time,"%Y-%m-%d %H:%M")] = action
times = sorted(log)
guards = {}
for i in times:
    if log[i] == "falls asleep":
        start = i.minute
    elif log[i] == "wakes up":
        for j in range(start,i.minute):
            guards[currGuard][j] += 1
    else:
        currGuard = int(log[i].split(" ")[1][1:])
        if not currGuard in guards:
            guards[currGuard] = [0] * 60 
m = 0
for k,v in guards.items():
    if(sum(v) > m):
        m = sum(v)
        g = k

print("Answer:",g * guards[g].index(max(guards[g])))
