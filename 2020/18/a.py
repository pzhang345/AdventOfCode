def solve(eq):
    count = 0
    str = ""
    digit = []
    op = []
    for i in list(eq.replace(" ","")):
        if i == "(":
            if count != 0:
                str += "("
            count += 1
        elif i == ")":
            count -= 1
            if count != 0:
                str += ")"
            else:
                print(str)
                digit += [solve(str)]
                str = ""
        elif count != 0:
            str += i
        else:
            if i.isdigit():
                digit += [int(i)]
            else:
                op += [i]
    total = digit[0]
    print(op,digit)
    for i,iv in enumerate(op):
        if iv == "+":
            total += digit[i + 1]
        else:
            total *= digit[i + 1]
    return total
        
eqs = open("text.txt").read().split("\n")
print(eqs)
sum = 0
for i in eqs:
    sol = solve(i)
    print(sol)
    sum += sol
print("Answer:",sum)
