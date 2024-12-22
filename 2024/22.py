from aocd import get_data
from collections import deque
f = get_data(day=22,year=2024)

def part1():
    def getNext(num):
       num = ((num*64) ^ num) % 16777216
       num = ((num//32) ^ num) % 16777216
       num = ((num*2048) ^ num) % 16777216
       return num
   
    s = 0
    for i in f.split("\n"):
        num = int(i)
        for i in range(2000):
            num = getNext(num)
        s += num
    return s
   
def part2():
    def getNext(num):
       num = ((num*64) ^ num) % 16777216
       num = ((num//32) ^ num) % 16777216
       num = ((num*2048) ^ num) % 16777216
       return num
   
    total = {}
    for i in f.split("\n"):
        num = int(i)
        q = deque()
        done = set()
        for i in range(2000):
            nnum = getNext(num)
            cost = num%10
            ncost = nnum%10

            q.append(ncost-cost)
            num = nnum
            if len(q) < 4:
                continue

            k = tuple(q)
            q.popleft()
        
            if k in done:
                continue
            done.add(k)

            if not k in total:
                total[k] = 0
            total[k] += ncost
    
    m = 0

    for k,v in total.items():
        if v > m:
            m = v
    return m

print("Part 1:", part1())
print("Part 2:", part2())