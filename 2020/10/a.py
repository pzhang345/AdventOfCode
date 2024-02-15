ints = [int(i) for i in open("text.txt").read().split("\n")]
ints.sort()
print(ints)
diff = [1] * 3
for i in range(len(ints) - 1):
    diff[ints[i + 1] - ints[i] - 1] += 1
print("Answer:",diff[0] * diff[2])