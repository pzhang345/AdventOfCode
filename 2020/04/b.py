count = 0
at = ["byr", "iyr","eyr","hgt","hcl","ecl","pid"]
def isValid(str):
    has = [False] * len(at)
    for i in str:
        key, value = i.split(":")
        if(key == "byr"):
            if(1920 <= int(value) <= 2002):
                has[0] = True
            else:
                return False
            
        elif(key == "iyr"):
            if(2010 <= int(value) <= 2020):
                has[1] = True
            else:
                return False
            
        elif(key == "eyr"):
                if(2020 <= int(value) <= 2030):
                    has[2] = True
                else:
                    return False
                
        elif(key == "hgt"):
            if("cm" in value):
                if(150 <= int(value[:-2]) <= 193):
                    has[3] = True
            elif("in" in value):
                if(59 <= int(value[:-2]) <= 76):
                    has[3] = True
            else:
                return False

        elif(key == "hcl"):
            hex = ["0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f"]
            if(value[0] == "#"):
                for c in value[1:]:
                    isIn = False
                    for ch in hex:
                        if(c == ch):
                            isIn = True
                            break
                    if(not isIn):
                       return False
                has[4] = True
            else:
                return False

        elif(key == "ecl"):
            l = ["amb","blu","brn","gry","grn","hzl","oth"]
            isIn = False
            for c in l:
                if(c == value):
                    isIn = True
                    break
            if(isIn):
                has[5] = True
            else:
                return False
        
        elif(key == "pid"):
            if(len(value) == 9):
                has[6] = True
            else:
                return False
    return all(has)


for i in open("text.txt").read().split("\n\n"):
    if(isValid(i.replace("\n"," ").split(" "))):
        count += 1

print("Answer:",count)
