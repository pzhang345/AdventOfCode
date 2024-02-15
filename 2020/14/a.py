stor = {}
for line in open("text.txt"):
    type,str = line.strip().split(" = ")
    if type == "mask":
        mask = str
        print(mask)
    else:
        addr = int(type.split("[")[1].split("]")[0])
        num = "{:>36}".format(bin(int(str))[2:]).replace(" ","0")
        temp = ''
        for i,c in enumerate(mask):
            if c == 'X':
                temp += num[i]
            else:
                temp += c
        print(int(temp,2))
        stor[addr] = int(temp,2)
sum = 0
for k,v in stor.items():
    sum += v
print("Answer:",sum)