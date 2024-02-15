from functools import cache
conn,inputs = open("test2.txt").read().split("\n\n")
conn = dict(map(lambda str:(int(str.split(": ")[0]),str.replace('"',"").split(": ")[1]),conn.split("\n")))
conn[8] = "42 | 42 8"
conn[11] = "42 31 | 42 11 31"
print(conn)
@cache
def isZero(nums):
    if(nums == (0,)):
        return True
    #print(nums)
    for i,iv in enumerate(nums):
        #print(iv)
        if (iv,) in reduce:
            for j in reduce[(iv,)]:
                if(isZero(tuple(nums[0:i] + (j,) + nums[i+1:]))):
                    return True
        if i < len(nums) - 1 and (iv,nums[i+1]) in reduce:
            for j in reduce[(iv,nums[i+1])]:
                if(isZero(tuple(nums[0:i] + (j,) + nums[i+2:]))):
                    return True
        if i < len(nums) - 2 and (iv,nums[i+1],nums[i+2]) in reduce:
            for j in reduce[(iv,nums[i+1],nums[i+2])]:
                if(isZero(tuple(nums[0:i] + (j,) + nums[i+3:]))):
                    return True
    return False

count = 0
reduce = {}
for k,v in conn.items():
    value = [tuple([v])]
    if v != "a" and v != "b":
        value = [tuple(int(j) for j in i.split(" ")) for i in v.split(" | ")]
    for i in value:
        if i in reduce:
            reduce[i] += [k]
        else:
            reduce[i] = [k]
count = 0
for i in inputs.split("\n"):
    print(i)
    if(isZero(tuple(list(i)))):
        count += 1
print(count)
