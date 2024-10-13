grid = {}
nums = {}
for i in open("text.txt"):
    num,info = i.strip().split(" @ ")
    num = int(num[1:])
    pos, dim = info.split(":")
    left,top = pos.strip().split(",")
    w,h = dim.strip().split("x")
    nums[num] = True
    for wi in range(int(w)):
        for hi in range(int(h)):
            if not (int(left) + wi,int(top) + hi) in grid:
                grid[(int(left) + wi,int(top) + hi)] = []
            grid[(int(left) + wi,int(top) + hi)] += [num]
count = 0
for k,v in grid.items():
    if len(v) > 1:
        for i in v:
            nums[i] = False

for num in nums:
    if nums[num]:
        ans = num
        break
print(ans)
