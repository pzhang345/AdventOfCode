conn,inputs = open("test.txt").read().split("\n\n")
conn = dict(map(lambda str:(int(str.split(":")[0]),str.replace('"',"").split(": ")[1]),conn.split("\n")))
print(conn)
def getCom(num):
    if(conn[num] == "a" or conn[num] == "b"):
        return [conn[num]]
    nums = list(map(lambda str:str.split(" "),conn[num].split(" | ")))
    com = []
    #print(nums)
    for i in nums:
        temp = [""]
        for j in i:
            print(j)
            temp = add(temp,getCom(int(j)))
        com += temp
    return com
def add(arr1,arr2):
    temp = set()
    for i in arr1:
        for j in arr2:
            temp.add(i + j)
    #print(temp)
    return list(temp)

com = getCom(0)
count = 0
for i in inputs.split("\n"):
    print(i)
    if i in com:
        count += 1
print("Answer:",count)