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
    print(op,digit)
    while "+" in op:
        ind = op.index("+")
        digit[ind] = digit[ind] + digit[ind + 1]
        del op[ind]
        del digit[ind + 1]
    total = digit[0]
    for i,iv in enumerate(op):
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