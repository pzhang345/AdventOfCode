from collections import deque
grid = [list(i) for i in open("text.txt").read().split("\n")]
moveOrder = [(0,-1),(-1,0),(1,0),(0,1)]
class ElfDied(Exception):
        pass
class Unit:
    goblinPos = {}
    elfPos = {}
    def __init__(self,type,x,y) -> None:
        self.type = type
        self.health = 200
        
        if(type == "E"):
            self.damage = edmg
            Unit.elfPos[(x,y)] = self
            self.selfPos = Unit.elfPos
            self.enemyPos = Unit.goblinPos
        else:
            self.damage = 3
            Unit.goblinPos[(x,y)] = self
            self.selfPos = Unit.goblinPos
            self.enemyPos = Unit.elfPos
        self.x = x
        self.y = y

    def check(self) -> bool:
        return len(self.enemyPos) == 0
    
    def move(self) -> None:
        prevPos = {(self.x,self.y): None}
        q = deque()
        q.append(((self.x,self.y)))
        Epos = None
        while Epos == None and q:
            currpos = q.popleft()
            for dpos in moveOrder:
                npos = (currpos[0] + dpos[0], currpos[1] + dpos[1])
                if not npos in prevPos and not npos in self.selfPos and grid[npos[1]][npos[0]] != "#":
                    prevPos[npos] = currpos
                    if npos in self.enemyPos:
                        if currpos == (self.x,self.y):
                            return
                        Epos = npos
                        break
                    q.append(npos)
        while Epos != None:
            Epos = prevPos[Epos]
            if prevPos[Epos] == (self.x,self.y):
                del self.selfPos[(self.x,self.y)]
                self.selfPos[Epos] = self
                self.x,self.y = Epos
                break

    def attack(self):
        possible = [self.enemyPos[(dpos[0] + self.x,dpos[1] + self.y)] for dpos in moveOrder if (dpos[0] + self.x,dpos[1] + self.y) in self.enemyPos]
        if len(possible) > 0:
            low = possible[0]
            for i in possible:
                if(low.health > i.health):
                    low = i
            low.takeDamage(self.damage)
    
    def takeDamage(self,damage):
        self.health -= damage
        if self.health <= 0:
            if self.type == "E":
                raise ElfDied("unlucky")
            self.delete()

    def changeDamage(self,damage):
        self.damage = damage
    
    def delete(self):
        del self.selfPos[(self.x,self.y)]


    def __repr__(self) -> str:
        return "### " + self.type + ": (" + str(self.x) + "," + str(self.y) + ") " + "H=" + str(self.health) + " ###"
    
def printGrid():
    for i,iv in enumerate(grid):
        s = ""
        for j,jv in enumerate(iv): 
            if ((j,i) in Unit.elfPos):
                s += "E"
            elif((j,i) in Unit.goblinPos):
                s += "G"
            else:
                s += jv
        print(s)

def reset():
    Unit.elfPos = {}
    Unit.goblinPos = {}

def run():
    reset()
    count = 0
    units = [Unit(c,x,y) for c,x,y in UnitsInfo]
    while len(Unit.goblinPos) != 0 and len(Unit.elfPos) != 0:
        units.sort(key=lambda unit:(unit.y,unit.x))
        for unit in units:
            if unit.health > 0:
                if(unit.check()):
                    count -= 1
                    break
                unit.move()
                unit.attack()
            
        units = [unit for unit in units if unit.health > 0]
        count += 1
    printGrid()
    print(units)
    print()
    return (units,count)

UnitsInfo = []
for i in range(len(grid)):
    for j in range(len(grid[i])):
        if grid[i][j] != "#" and grid[i][j] != ".":
            UnitsInfo += [(grid[i][j],j,i)]
            grid[i][j] = "."

printGrid()
print(UnitsInfo)
range = [0,200]
while range[0] != range[1]:
    print(range)
    edmg = sum(range)//2
    try:
        out = run()
    except ElfDied:
        range[0] = edmg + 1
    else:
        range[1] = edmg
units,count = out
print(count)
print("Answer:",sum([unit.health for unit in units if unit.health > 0]) * count)