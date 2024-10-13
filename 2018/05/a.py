import string
s = open("text.txt").read()
l = 0
while len(s) != l:
    l = len(s)
    for i in string.ascii_lowercase:
        s = s.replace(i + i.upper(),"").replace(i.upper() + i,"")
print("Answer:",len(s))