list = []
count = 0
for line in open("text.txt"):
    num = int(line.strip())
    if count < 25:
        list += [num]
    else:
        print(list)
        s = set()
        has = False
        for i in list:
            if i not in s:
                s.add(num-i)
            else:
                has = True
                break
        if has:
            del list[0]
            list += [num]
        else:
            break
    count += 1
print("Answer:",num)