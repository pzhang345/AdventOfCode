
two = 0
three = 0
for i in open("text.txt"):
    chars = {}
    for j in i:
        if not j in chars:
            chars[j] = 0
        chars[j] += 1
    for k in chars:
        if chars[k] == 2:
            two += 1
            break
    for k in chars:
        if chars[k] == 3:
            three += 1
            break
print("Answer:",two*three)