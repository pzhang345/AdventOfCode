code = [i.split(" ") for i in open("text.txt").read().split("\n")]
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
r = [0] * 6 
for i in code:
    i[1:] = [int(j) for j in i[1:]]
print(code)
ip = code[0][1]
code = code[1:]

while r[ip] < len(code):
    l = r[ip]
    cmd = code[l][0]
    instr = code[l][1:]
    r = commands[cmd](r,instr)
    r[ip] += 1
print(r)
