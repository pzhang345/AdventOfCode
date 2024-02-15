from collections import deque
bags = {}
class Bag:
    def __init__(self,name,contains):
        self.name = name
        if contains[0] == "":
            contains = []
        self.contains = contains
        self.parents = []
q = deque()
for line in open("text.txt"):
    line = line.strip()
    name = line.split(" bags")[0]
    c = list(map(lambda x: x[2:],line.split("contain ")[1].replace("no other bags.","").replace("bags","bag").replace(" bag.","").split(" bag, ")))
    if "shiny gold" in c:
        q.append(name)
    bags[name] = Bag(name,c)

for k,v in bags.items():
    for i in v.contains:
        bags[i].parents += [k]

for k,v in bags.items():
    print(k,v.parents)

hasDone = set(q)
print(q)
while q:
    name = q.popleft()
    for i in bags[name].parents:
        if i not in hasDone:
            q.append(i)
            hasDone.add(i)
    print(name)

print(hasDone)
print("Answer:",len(hasDone))