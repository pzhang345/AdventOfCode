from aocd import get_data
f = get_data(day=25,year=2024)

def part1():
	keys = []
	locks = []
	for i in f.split("\n\n"):
		arr = keys if i[0] == "." else locks
		b = i.split("\n")
		t = []
		for x in range(len(b[0])):
			c = 0
			for y in range(len(b)):
				if b[y][x] == "#":
					c += 1
			t += [c - 1]
		arr += [tuple(t)]
	c = 0
	for k in keys:
		for l in locks:
			fit = True
			for i in range(len(k)):
				if k[i] + l[i] > 5:
					fit = False
					break
			if fit:
				c += 1
	return c

print("Part 1:", part1())