from aocd import get_data
f = get_data(day=24,year=2024)

def part1():
	def getRes(cmd,v1,v2):
		if cmd == "AND":
			return v1 and v2
		if cmd == "OR":
			return v1 or v2
		if cmd == "XOR":
			return v1 != v2
	reg,prog = f.split("\n\n")
	reg = {i.split(": ")[0]: True if int(i.split(": ")[1]) == 1 else False for i in reg.split("\n")}
	prog = [i.split(" ") for i in prog.split("\n")]
	i = 0
	while len(prog) != 0:
		i %= len(prog)
		curr = prog[i]
		if curr[0] in reg and curr[2] in reg:
			reg[curr[4]] = getRes(curr[1],reg[curr[0]],reg[curr[2]])
			del prog[i]
		else:
			i += 1
	
	s = ""
	for i in sorted(list(filter(lambda s:s[0] == "z", reg)),reverse=True):
		s += str(int(reg[i]))
	return int(s,2)

def part2():
	def getRes(cmd,v1,v2):
		if cmd == "AND":
			return v1 and v2
		if cmd == "OR":
			return v1 or v2
		if cmd == "XOR":
			return v1 != v2
	
	reg,prog = f.split("\n\n")
	reg = {i.split(": ")[0]: False for i in reg.split("\n")}
	prog = [i.split(" ") for i in prog.split("\n")]
	used = {i:set() for i in reg}
	fr = {i[4]:i[0:3] for i in prog}

	prog = list(prog)
	i = 0

	while len(prog) != 0:
		i %= len(prog)
		curr = prog[i]
		if curr[0] in reg and curr[2] in reg:
			reg[curr[4]] = getRes(curr[1],reg[curr[0]],reg[curr[2]])
			used[curr[4]] = set([curr[0],curr[2]] + list(used[curr[0]]) + list(used[curr[2]]))
			del prog[i]
		else:
			i += 1
	
	error = []
	znum = 46
	for i in range(1,znum):
		text = str(i).zfill(2)
		count = {"XOR":0,"OR":0,"AND":0}
		s = set(used["z" + text].difference(used["z" + str(i-1).zfill(2)]))
		s.add("z" + text)
		for e in s:
			if e[0] == "y" or e[0] == "x":
				continue
			count[fr[e][1]] += 1
		if count != {"XOR":2,"OR":1,"AND":2}:
			error += [i]
	
	out = open("log.txt","w")
	for e in error:
		text = str(e).zfill(2)
		out.write(f"BIT {e}\n")
		out.write(f"z{text}\t{fr["z" + text]}\n")
		for i in used["z" + text].difference(used["z" + str(e-1).zfill(2)]):
			if i[0] != "y" and i[0] != "x":
				out.write(i + "\t" + str(fr[i]) + "\n")
		out.write("\n")
	out.close()
	return "Did this one manually by tuning the adder, check \"log.txt\" for the potentially incorrect bits."

print("Part 1:", part1())
print("Part 2:", part2())
