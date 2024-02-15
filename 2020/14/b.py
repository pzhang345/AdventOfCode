stor = {}
def addToStor(value,baddr,currnum,stor):
    if len(baddr) == 0:
        stor[currnum] = value
    else:
        currnum *= 2
        if baddr[0] == '0':
            addToStor(value,baddr[1:],currnum,stor)
        elif baddr[0] == '1':
            addToStor(value,baddr[1:],currnum + 1,stor)
        else:
            addToStor(value,baddr[1:],currnum,stor)
            addToStor(value,baddr[1:],currnum + 1,stor)

for line in open("text.txt"):
    type,str = line.strip().split(" = ")
    if type == "mask":
        mask = str
        #print(mask)
    else:
        addr = "{:>36}".format(bin(int(type.split("[")[1].split("]")[0]))[2:]).replace(' ','0')
        #print(addr)
        num = int(str)
        temp = ''
        for i,c in enumerate(mask):
            if c == 'X':
                temp += 'X'
            elif c == '1':
                temp += '1'
            else:
                temp += addr[i]
        addToStor(num,temp,0,stor)
sum = 0
for k,v in stor.items():
    sum += v
print("Answer:",sum)