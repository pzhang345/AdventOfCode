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
s = open("text.txt").read().split("\n")
state = s[0].split(": ")[1]
rules = {i.split(" => ")[0]: i.split(" => ")[1] for i in s[2:]}
print(rules)
ind = 0
for i in range(20):
    print(state)
    state,dind = pad(state)
    print(state)
    print(dind)
    ind -= dind - 2
    news = ""
    for j in range(0,len(state) - 4):
        if state[j:j+5] in rules:
            news += rules[state[j:j+5]]
        else:
            news += "."
    state = news
sum = 0
print(ind)
print(state)
for i in range(len(state)):
    if(state[i] == "#"):
        sum += i + ind
print("Answer:", sum)
