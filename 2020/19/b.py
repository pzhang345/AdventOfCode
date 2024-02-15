conn,inputs = open("text.txt").read().split("\n\n")
conn = dict(map(lambda str:(int(str.split(":")[0]),str.replace('"',"").split(": ")[1]),conn.split("\n")))
conn[8] = "42 | 42 8"
conn[11] = "42 31 | 42 11 31"
print(conn)
for k,v in conn.items():
    temp = v.split(" | ")
    conn[k] = []
    for i in temp:
        if i != "a" and i != "b":
            conn[k] += [[int(j) for j in i.split(" ")]]
        else:
            conn[k] += i
print(conn)
def isSeq(seq,str):
    if seq == [] or str == "":
        print(seq,str)
        return seq == [] and str == ""
    #print(seq)
    curr = conn[seq[0]]
    #print(curr)
    if curr == ["a"] or curr == ["b"]:
        if curr[0] == str[0]:
            return isSeq(seq[1:],str[1:])
        else:
            return False
    else:
        for i in curr:
            if isSeq(i + seq[1:],str):
                return True
        return False
    
count = 0
for i in inputs.split("\n"):
    print(i)
    if isSeq([0],i):
        count += 1
print("Answer:",count)