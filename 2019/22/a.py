instr = open("text.txt").read().split("\n")

def dealIntoNewStack(cards):
    cards = list(cards)
    return list(reversed(cards))

def cut(cards,val):
    cards = list(cards)
    return cards[val:]+ cards[0:val]

def dealWithIncrement(cards,incr):
    count = 0
    tempcards = [-1] * len(cards)
    for i in cards:
        tempcards[count] = i
        count = (count + incr) % len(cards)
    return tempcards

cards = [i for i in range(10007)]
print(cards)
for i in instr:
    if i == "deal into new stack":
        # print("DEAL:",i)
        cards = dealIntoNewStack(cards)
    elif i.split(" ")[0] == "cut":
        # print("CUT:",i,int(i.split(" ")[-1]))
        cards = cut(cards,int(i.split(" ")[1]))
    else:
        # print("INCREMENT:",i,int(i.split(" ")[-1]))
        cards = dealWithIncrement(cards,int(i.split(" ")[-1]))
print("Answer:",cards.index(2019))
# print(cards)