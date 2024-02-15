count = 0
at = ["byr", "iyr","eyr","hgt","hcl","ecl","pid"]
for i in open("text.txt").read().split("\n\n"):
    has = [False] * len(at)
    for j in i.split("\n"):
        for num,element in enumerate(at):
            if (element + ":") in j:
                has[num] = True
    if(all(has)):
        count += 1

print("Answer:",count)
