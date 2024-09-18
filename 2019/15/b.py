from collections import deque
prog = {i:int(iv) for i,iv in enumerate(open("text.txt").read().split(","))}
class tile:
    def __init__(self,code,pos,rm,num):
        self.code = code
        self.pos = pos
        self.rm = rm
        self.num = num
def getnums(nums,i,len,rmode):
    vals = []
    for j in range(len):
        if (i + j) in nums:
            vals += [nums[i + j]]
        else:
            vals += [0]
    #print(vals,i,rmode)
    arr = []
    par = int(vals[0]/100)
    #print(par)
    for j in range(1,len):
        if par % 10 == 0:
            if vals[j] in nums:
                arr += [(vals[j],nums[vals[j]])]
            else:
                arr += [(vals[j],0)]
        elif par % 10 == 1:
            arr += [(-1,vals[j])]
        elif par % 10 == 2:
            if (vals[j] + rmode) in nums:
                arr += [(vals[j] + rmode,nums[vals[j] + rmode])]
            else:
                arr += [(vals[j] + rmode,0)]
        par = int(par/10)
    #print(arr)
    return arr
def runProgram(code,pos,input,inputc,rm):
    nums = dict(code)
    i = pos
    rmode = rm
    output = []
    #print(code)
    while i < len(nums):
        #print("ROW:",i,nums[i])
        if nums[i]%100 == 1:
            vals = getnums(nums,i,4,rmode)
            nums[vals[2][0]] = vals[1][1] + vals[0][1]
            i += 4
        
        elif nums[i]%100 == 2:
            vals = getnums(nums,i,4,rmode)
            nums[vals[2][0]] = vals[1][1] * vals[0][1]
            i += 4
        
        elif nums[i]%100 == 3:
            vals = getnums(nums,i,2,rmode)
            if(len(input) != 0):
                nums[vals[0][0]] = input[0]
                input = input[1:]
            elif inputc == -1:
                return (nums,i,rmode,output)
            else:
                nums[vals[0][0]] = inputc
            i += 2
        
        elif nums[i]%100 == 4:
            vals = getnums(nums,i,2,rmode)
            output += [vals[0][1]]
            #print(output)
            i += 2
        
        elif nums[i]%100 == 5:
            vals = getnums(nums,i,3,rmode)
            if vals[0][1] == 0:
                i += 3
            else:
                i = vals[1][1]
        
        elif nums[i]%100 == 6:
            vals = getnums(nums,i,3,rmode)
            if vals[0][1] == 0:
                i = vals[1][1]
            else:
                i += 3
        
        elif nums[i]%100 == 7:
            vals = getnums(nums,i,4,rmode)
            if vals[0][1] < vals[1][1]:
                nums[vals[2][0]] = 1
            else:
                nums[vals[2][0]] = 0
            i += 4
        
        elif nums[i]%100 == 8:
            vals = getnums(nums,i,4,rmode)
            #print(vals[0][1],vals[1][1],vals)
            if vals[0][1] == vals[1][1]:
                nums[vals[2][0]] = 1
            else:
                nums[vals[2][0]] = 0
            i += 4
        
        elif nums[i]%100 == 9:
            vals = getnums(nums,i,2,rmode)
            rmode += vals[0][1]
            #print(rmode)
            i += 2

        elif nums[i] == 99:
            print("END")
            return (nums,-1,rmode,output)
        
        else:
            print(nums[i])
            print("ERROR")
            return
tiles = {}
tiles[(0,0)] = tile(prog,0,0,0)
q = deque()
q.append((0,0))
dirs = [(0,-1),(0,1),(-1,0),(1,0)]

done = False
count = 0
while q:
    pos = q.popleft()
    t = tiles[pos]
    #print(pos)
    for i ,(x,y) in enumerate(dirs):
        newpos = (pos[0] + x, pos[1] + y)
        if newpos in tiles:
            continue
        i += 1
        o = runProgram(t.code,t.pos,[i],-1,t.rm)
        out = o[3][0]
        #print(pos,out)
        if out == 0:
            tiles[newpos] = "#"
        elif out == 1:
            tiles[newpos] = tile(o[0],o[1],o[2],t.num + 1)
            if t.num + 1 != count:
                count += 1
                print(count,t.num)
            q.append(newpos)
            #print(len(q))
        elif out == 2:
            tiles = {}
            tiles[newpos] = tile(o[0],o[1],o[2],0)
            print(tiles[newpos].num)
            q = deque()
            q.append(newpos)
            count = 0
            break
print("Answer:",count)
# minx = 0
# maxx = 0
# miny = 0
# maxy = 0
# for i in tiles:
#     if i[0] < minx:
#         minx = i[0]
#     if i[0] > maxx:
#         maxx = i[0]
#     if i[1] < miny:
#         miny = i[1]
#     if i[1] > maxy:
#         maxy = i[1]
# for y in range(miny,maxy + 1):
#     s = ""
#     for x in range(minx,maxx + 1):
#         if not (x,y) in tiles or tiles[(x,y)] == "#":
#             s += "#"
#         else:
#             s += " "
#     print(s)