bool = [False] * 2020
answer = 0
for i in open("text.txt"):
    n = int(i.strip())
    if(bool[n]):
        answer = (n * (2020 - n))
        break
    bool[2020 - n] = True

print("Answer:",answer)