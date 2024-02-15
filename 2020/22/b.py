from collections import deque
txt = open("text.txt").read().split("\n\n")
p1 = deque(int(i) for i in txt[0].split("\n")[1:])
p2 = deque(int(i) for i in txt[1].split("\n")[1:])
print(p1)
print(p2)
def game(p1,p2,depth):
    played = []
    while len(p1) != 0 and len(p2) != 0:
        if (p1) in played:
            return (True,p1)
        played += [deque(p1)]
        if(depth == 0):
            print(p1)
            print(p2)
            print()
        c1 = p1.popleft()
        c2 = p2.popleft()
        if c1 <= len(p1) and c2 <= len(p2):
            win = game(deque(list(p1)[:c1]),deque(list(p2)[:c2]),depth + 1)[0]
            if win:
                p1.extend((c1,c2))
            else:
                p2.extend((c2,c1))
        elif c1 > c2:
            p1.extend((c1,c2))
        else:
            p2.extend((c2,c1))
    if len(p1) == 0:
        return (False,p2)
    else:
        return (True,p1)
sum = 0
arr = game(p1,p2,0)[1]
print(arr)
print()
for i in range(len(arr)):
    sum += (i + 1) * arr[-(i + 1)]
print("Answer:",sum)