time,buses = open("text.txt").read().split("\n")
time = int(time)
buses = list(map(lambda a: int(a),buses.replace("x,","").split(",")))
print(buses)
min = buses[0] - time%buses[0]
busnum = buses[0]
for bus in buses:
    if min > bus - time % bus:
        min = bus - time % bus
        index = bus
print("Answer:",min*index)