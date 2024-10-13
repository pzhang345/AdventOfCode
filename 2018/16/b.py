data,code = open("text.txt").read().split("\n\n\n\n")
data = [i.split("\n") for i in data.split("\n\n")]
code = code.split("\n")
def addr(arr,instr):
    arr = list(arr)
    arr[instr[2]] = arr[instr[0]] + arr[instr[1]]
    return arr

def addi(arr,instr):
    arr = list(arr)
    arr[instr[2]] = arr[instr[0]] + instr[1]
    return arr

def mulr(arr,instr):
    arr = list(arr)
    arr[instr[2]] = arr[instr[0]] * arr[instr[1]]
    return arr

def muli(arr,instr):
    arr = list(arr)
    arr[instr[2]] = arr[instr[0]] * instr[1]
    return arr

def banr(arr,instr):
    arr = list(arr)
    arr[instr[2]] = arr[instr[0]] & arr[instr[1]]
    return arr

def bani(arr,instr):
    arr = list(arr)
    arr[instr[2]] = arr[instr[0]] & instr[1]
    return arr

def borr(arr,instr):
    arr = list(arr)
    arr[instr[2]] = arr[instr[0]] | arr[instr[1]]
    return arr

def bori(arr,instr):
    arr = list(arr)
    arr[instr[2]] = arr[instr[0]] | instr[1]
    return arr

def setr(arr,instr):
    arr = list(arr)
    arr[instr[2]] = arr[instr[0]]
    return arr

def seti(arr,instr):
    arr = list(arr)
    arr[instr[2]] = instr[0]
    return arr

def gtir(arr,instr):
    arr = list(arr)
    arr[instr[2]] = int(instr[0] > arr[instr[1]])
    return arr

def gtri(arr,instr):
    arr = list(arr)
    arr[instr[2]] = int(arr[instr[0]] > instr[1])
    return arr

def gtrr(arr,instr):
    arr = list(arr)
    arr[instr[2]] = int(arr[instr[0]] > arr[instr[1]])
    return arr

def eqir(arr,instr):
    arr = list(arr)
    arr[instr[2]] = int(instr[0] == arr[instr[1]])
    return arr

def eqri(arr,instr):
    arr = list(arr)
    arr[instr[2]] = int(arr[instr[0]] == instr[1])
    return arr

def eqrr(arr,instr):
    arr = list(arr)
    arr[instr[2]] = int(arr[instr[0]] == arr[instr[1]])
    return arr

commands = {
    "addr": lambda arr,instr: addr(arr,instr),
    "addi": lambda arr,instr: addi(arr,instr),
    "mulr": lambda arr,instr: mulr(arr,instr),
    "muli": lambda arr,instr: muli(arr,instr),
    "banr": lambda arr,instr: banr(arr,instr),
    "bani": lambda arr,instr: bani(arr,instr),
    "borr": lambda arr,instr: borr(arr,instr),
    "bori": lambda arr,instr: bori(arr,instr),
    "setr": lambda arr,instr: setr(arr,instr),
    "seti": lambda arr,instr: seti(arr,instr),
    "gtir": lambda arr,instr: gtir(arr,instr),
    "gtri": lambda arr,instr: gtri(arr,instr),
    "gtrr": lambda arr,instr: gtrr(arr,instr),
    "eqir": lambda arr,instr: eqir(arr,instr),
    "eqri": lambda arr,instr: eqri(arr,instr),
    "eqrr": lambda arr,instr: eqrr(arr,instr),
}

print(data)
numToCommand = {j:[i for i in commands] for j in range(16)}
print(numToCommand)
for i in data:
    before = [int(j) for j in i[0].split("[")[1].replace("]","").split(",")]
    instr = [int(j) for j in i[1].split(" ")]
    after = [int(j) for j in i[2].split("[")[1].replace("]","").split(",")]
    for k,v in commands.items():
        if not k in numToCommand[instr[0]]:
            continue 
        newarr = v(before,instr[1:])
        if(newarr != after):
            numToCommand[instr[0]].remove(k)

done = False
while not done:
    done = True
    for k,v in numToCommand.items():
        if len(v) == 1:
            for i in numToCommand:
                if i != k and v[0] in numToCommand[i]:
                    numToCommand[i].remove(v[0])
        else:
            done = False

for k,v in numToCommand.items():
    numToCommand[k] = commands[v[0]]
print(numToCommand)

reg = [0] * 4
for i in code:
    print(i)
    line = [int(j) for j in i.split(" ")]
    reg = numToCommand[line[0]](reg,line[1:])
print("Answer:",reg[0])