import string
def re(s):
    l = 0
    while len(s) != l:
        l = len(s)
        for j in string.ascii_lowercase:
            s = s.replace(j + j.upper(),"").replace(j.upper() + j,"")
    return s

o = re(open("text.txt").read())
for i in string.ascii_lowercase:
    s = re(o.replace(i,"").replace(i.upper(),""))
    if i == "a" or len(s) < m:
        m = len(s)
    
print("Answer:",m)