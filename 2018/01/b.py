sum = 0
freq = set()
ans = None
while ans == None:
    for i in open("text.txt"):
        if sum in freq:
            ans = sum
            break
        freq.add(sum)
        sum += int(i)
print("Answer:",ans)