from collections import deque
txt = open("text.txt").read().split("\n\n")
p1 = deque(int(i) for i in txt[0].split("\n")[1:])
p2 = deque(int(i) for i in txt[1].split("\n")[1:])
print(p1)
print(p2)
while len(p1) != 0 and len(p2) != 0:
    c1 = p1.popleft()
    c2 = p2.popleft()
    if c1 > c2:
        p1.extend((c1,c2))
        win = p1
    else:
        p2.extend((c2,c1))
        win = p2
sum = 0
print(win)
for i in range(len(win)):
    sum += win[-(i + 1)] * (i + 1)
    print(win[-(i + 1)],(i+1))
print("Answer:",sum)