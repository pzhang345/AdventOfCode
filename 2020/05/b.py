seats = [False] * (8 * 128)
for line in open("text.txt"):
    row = int(line.strip()[:7].replace("F","0").replace("B","1"),2)
    column = int(line.strip()[-3:].replace("L","0").replace("R","1"),2)
    seats[row * 8 + column] = True

for i,iv in enumerate(seats):
    #print(num,i)
    if(not iv and seats[i - 1] == True == seats[i + 1]):
        answer = i
        break
print("Answer:",i)