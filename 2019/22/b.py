instr = open("text.txt").read().split("\n")

startloc = 2020
numcard = 119315717514047
iter = 101741582076661

# startloc = 6431
# numcard = 10007
# iter = 1

# startloc = 7
# numcard = 10
# iter = 1

currloc = startloc
add = 0
mult = 1
def dealIntoNewDeck(add,mult):
    return (add + mult * -1,mult * -1)
    # return numcard - currloc - 1

def cut(val,add,mult):
    return (add + mult * val,mult)
    #return (currloc + val) % numcard

# a = (b * x) mod c
def dealWithIncrement(incr,add,mult):
    return (add,mult * pow(incr,-1,numcard))
    # return (currloc * pow(incr,-1,numcard)) % numcard
values = []

for j in instr:
    if j == "deal into new stack":
        # currloc = dealIntoNewDeck()
        add,mult = dealIntoNewDeck(add,mult)
    elif j.split(" ")[0] == "cut":  
        # currloc = cut(int(j.split(" ")[1]))
        add,mult = cut(int(j.split(" ")[1]),add,mult)
    else:
        # currloc = dealWithIncrement(int(j.split(" ")[-1]))
        add,mult = dealWithIncrement(int(j.split(" ")[-1]),add,mult)
    add %= numcard
    mult %= numcard

print(add,mult)
m = pow(mult,iter,numcard)
b = add * (1 - m) * pow(1 - mult,-1,numcard)
b %= numcard
print(m,b)

print("Answer:",(m * startloc + b)%numcard)


# print(cards)