import os

def getNext():
    files = os.listdir()
    for i in range(1,26):
        if not str(i).zfill(2) + ".py" in files:
            return i
    raise Exception("All files made")

day = getNext()
year = os.getcwd().split(os.sep)[-1]
if not year.isdigit():
    raise Exception("Invalid year: " + year)

f = open(str(day).zfill(2) + ".py","w")

f.write(f"""from utils import run

def part1(input: str):
    return 0


def part2(input: str):
    return 0


run({day}, part1, part2, part=1)"""
)
f.close()