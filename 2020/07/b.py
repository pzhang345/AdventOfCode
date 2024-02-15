bags = {}
class Bag:
    def __init__(self,name,contains):
        self.name = name
        if "" in contains:
            contains = {}
        self.contains = contains
def StringtoDict(str):
    if str == "":
        return ("","")
    else:
        return (str[2:],int(str[0]))
for line in open("text.txt"):
    line = line.strip()
    name = line.split(" bags")[0]
    c = dict(map(StringtoDict,line.split("contain ")[1].replace("no other bags.","").replace("bags","bag").replace(" bag.","").split(" bag, ")))
    bags[name] = Bag(name,c)
    print(bags[name].contains)

nums = {"shiny gold": 1}
total = 0
while len(nums) != 0:
    print(nums)
    tempnums = {}
    for k,v in nums.items():
        for b,n in bags[k].contains.items():
            total += n * v
            if b in tempnums:
                tempnums[b] += n * v
            else:
                tempnums[b] = n * v
    nums = tempnums

print("Answer",total)