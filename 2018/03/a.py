grid = [[0] * 1000 for i in range(1000)]
for i in open("text.txt"):
    nums = i.strip().split(" @ ")[1]
    pos, dim = nums.split(":")
    left,top = pos.strip().split(",")
    w,h = dim.strip().split("x")
    for wi in range(int(w)):
        for hi in range(int(h)):
            grid[int(left) + wi][int(top) + hi] += 1
count = 0
for i in grid:
    for j in i:
        if j > 1:
            count += 1
print(count)
