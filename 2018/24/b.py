l = [i.split("\n") for i in open("text.txt").read().replace("Immune System:\n","").split("\n\nInfection:\n")]

class Army:
    immuneArr = []
    infectArr = []
    def __init__(self,s,t):
        info = s.replace("(",";").replace(")","").replace(" with an attack that does ",";").replace(" damage at initiative ",";").replace(" units each with ",";").replace(" hit points","").split(";")
        self.type = t
        self.units = int(info[0])
        self.hp = int(info[1])
        self.damage = int(info[-2].split(" ")[0])
        self.dtype = info[-2].split(" ")[1]
        self.initiative = int(info[-1])
        self.immune = []
        self.weak = []

        for i in info[2:-2]:
            if "immune to " in i:
                self.immune = i.strip().replace("immune to ","").split(", ")
            if "weak to " in i:
                self.weak = i.strip().replace("weak to ","").split(", ")
        
        if self.type == "Immune":
            Army.immuneArr += [self]
            self.self = Army.immuneArr
            self.enemy = Army.infectArr
            self.damage += boost
        else:
            Army.infectArr += [self]
            self.self = Army.infectArr
            self.enemy = Army.immuneArr
    
    def __repr__(self):
        return self.type + "(Units: " + str(self.units) + ", HP: " + str(self.hp) + ", Damage: " + str(self.damage) + " " + self.dtype + ", I: " + str(self.initiative) + ", Weak: " + str(self.weak) + ", Immune: " + str(self.immune) + ")"
    
    def calcDamage(self,target):
        edamage = self.damage * self.units
        if self.dtype in target.weak:
            edamage *= 2
        if self.dtype in target.immune:
            edamage = 0
        return edamage
    
    def getTarget(self,used):
        if self.units <= 0:
            return None
        targetable = [e for e in self.enemy if e.units > 0 and e not in used and self.dtype not in e.immune]
        if len(targetable) == 0:
            return None
        target = targetable[0]
        comp = (self.dtype in target.weak,target.units * target.damage, target.initiative)
        for t in targetable[1:]:
            if comp < (self.dtype in t.weak, t.units * t.damage, t.initiative):
                target = t
                comp = (self.dtype in t.weak, t.units * t.damage, t.initiative)
        # print("\n".join([str(i) for i in targetable]))
        return target

    def attack(self,target):
        damage = self.calcDamage(target)
        if damage > 0:
            kills = damage // target.hp
            target.units -= kills
            return kills > 0

def printa(a):
    print("\n".join([str(i) for i in a]))

def reset():
    Army.immuneArr = []
    Army.infectArr = []

def create():
    armys = []
    t = "Immune"
    for i in range(len(l)):
        if i == 1:
            t = "Infection"
        for j in l[i]:
            armys += [Army(j,t)]
    return armys

def run():
    reset()
    armys = create()
    while True:
        targets = {}
        used = []
        armys.sort(key=lambda a: (a.units * a.damage,a.initiative),reverse=True)
        for group in armys:
            t = group.getTarget(used)
            targets[group] = t
            if t != None:
                used += [t]
        change = False
        armys.sort(key=lambda a: a.initiative,reverse=True)
        for group in armys:
            if(targets[group] != None):
                if(group.attack(targets[group])):
                    change = True
        # print(change)

        if len([i for i in Army.immuneArr if i.units > 0]) == 0 or not change:
            return -1
        if len([i for i in Army.infectArr if i.units > 0]) == 0:
            return sum([i.units for i in Army.immuneArr if i.units > 0])

boost = 0
while run() == -1:
    if boost == 0:
        boost += 1
    boost *= 2
boostRange = [boost//2,boost]
while boostRange[0] != boostRange[1]:
    print(boostRange)
    boost = sum(boostRange) // 2
    result = run()
    if result == -1:
        boostRange[0] = boost + 1
    else:
        boostRange[1] = boost
print(boostRange)
boost = boostRange[0]
print("Answer:",run())