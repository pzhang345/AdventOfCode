import math
convert = dict([(i.split(" => ")[1].split(" ")[1], (int(i.split(" => ")[1].split(" ")[0]),dict([(j.split(" ")[1],int(j.split(" ")[0])) for j in i.split(" => ")[0].split(", ")]))) for i in open("text.txt").read().split("\n")])
print(convert)
chems = {"FUEL":1}
excess = {}
ores = 0
fuel = 0
while ores < 1000000000000:
    if len(chems) == 0:
        fuel += 1
        if(ores%100 == 0):
            print(ores)
        chems["FUEL"] = 1
    chem,amount = list(chems.items())[0]
    del chems[chem]
    #print(chem)
    if chem in excess:
        if amount > excess[chem]:
            amount -= excess[chem]
            del excess[chem]
        elif amount == excess[chem]:
            del excess[chem]
            continue
        else:
            excess[chem] -= amount
            continue
    div,comp = convert[chem]
    mult = math.ceil(amount/div)
    ex = -amount % div
    if chem in excess:
        excess[chem] += ex
    elif ex != 0:
        excess[chem] = ex
    for i in comp:
        if(i == "ORE"):
            ores += mult * comp[i]
            #print(ores)
        else:
            if i in chems:
                chems[i] += mult * comp[i]
            else:
                chems[i] = mult * comp[i]
    #print(chems,excess,ores)
    #print(comp)
print("Answer:",fuel)