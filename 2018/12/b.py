def pad(s):
    back = False
    front = False
    c = 0
    for i in range(5):
        if s[i] != "." or front:
            front = True
            s = "." + s
            c += 1
        if s[-i-1] != "." or back:
            back = True
            s += "."
    return (s,c)
def nextState(state,ind):
    stated[state] = (i,ind)
    state,dind = pad(state)
    ind -= dind - 2
    news = ""
    for j in range(0,len(state) - 4):
        if state[j:j+5] in rules:
            news += rules[state[j:j+5]]
        else:
            news += "."
    return (news,ind)
s = open("test.txt").read().split("\n")
loop = 50000000000
state = s[0].split(": ")[1]
rules = {i.split(" => ")[0]: i.split(" => ")[1] for i in s[2:]}
stated = {}
print(rules)
ind = 0
for i in range(loop):
    if state in stated:
        break
    state,ind = nextState(state,ind)
    print(state)

ind1 = ind
i1 = i

di = i1 - stated[state][0]
dind = ind1 - stated[state][1]

print(ind)

ind = dind * int((loop - i1) / di) + ind1

print(ind)

print(di,dind)

for i in range((loop -i1) % di):
    state,ind = nextState(state,ind)

s = 0
for i in range(len(state)):
    if(state[i] == "#"):
        s += i + ind

print("Answer:", s)
