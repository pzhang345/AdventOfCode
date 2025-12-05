from utils import run

def part1(input: str):
    l,ingredients = input.split("\n\n")
    temp_r = []
    for i in l.split("\n"):
        lb, ub = i.split("-")
        lb, ub = int(lb), int(ub)
        temp_r.append((lb, ub))
    
    temp_r.sort()
    
    ranges = []
    current_bound = None
    for (lb,ub) in temp_r:
        if current_bound == None:
            current_bound = [lb, ub]
        
        elif lb <= current_bound[1] + 1:
            current_bound[1] = max(current_bound[1], ub)
        else:
            ranges.append(tuple(current_bound))
            current_bound = [lb, ub]
    
    if current_bound != None:
        ranges.append(tuple(current_bound))
    
    count = 0
    for i in ingredients.split("\n"):
        val = int(i)
        for (lb, ub) in ranges:
            if lb <= val <= ub:
                count += 1
                break

    return count

        
def part2(input: str):
    l,_ = input.split("\n\n")
    temp_r = []
    for i in l.split("\n"):
        lb, ub = i.split("-")
        lb, ub = int(lb), int(ub)
        temp_r.append((lb, ub))
    
    temp_r.sort()
    
    ranges = []
    current_bound = None
    for (lb,ub) in temp_r:
        if current_bound == None:
            current_bound = [lb, ub]
        
        elif lb <= current_bound[1] + 1:
            current_bound[1] = max(current_bound[1], ub)
        else:
            ranges.append(tuple(current_bound))
            current_bound = [lb, ub]
    
    if current_bound != None:
        ranges.append(tuple(current_bound))
    
    count = 0
    for i in ranges:
        count += i[1] - i[0] + 1

    return count



run(5, part1, part2)