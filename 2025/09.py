from utils import run

def part1(input: str):
    pos = [tuple([int(j) for j in i.split(",")]) for i in input.split("\n")]
    m = 0
    for i in range(len(pos)):
        for j in range(i + 1, len(pos)):
            x1, y1 = pos[i]
            x2, y2 = pos[j]
            a = (abs(x1 - x2) + 1) * (abs(y1 - y2) + 1)
            if a > m:
                m = a
    return m

def part2(input: str):
    pos = [tuple([int(j) for j in i.split(",")]) for i in input.split("\n")]
    pos += [pos[0]]
    wall_bounds = {}
    
    prev = pos[0]
    
    def add(x,y, side):
        if not y in wall_bounds:
            wall_bounds[y] = [set(), set()]
        
        wall = 0 if side == 1 else 1
        wall_bounds[y][wall].add(x)

    for i in pos:
        if prev[0] == i[0]:
            step = 1 if i[1] > prev[1] else -1
            for j in range(prev[1], i[1] + step, step):
                add(prev[0], j, step)
        
        prev = i
    
    first_key = wall_bounds[min(wall_bounds.keys())]
    left_wall_idx = 0 if min(first_key[0]) < min(first_key[1]) else 1
    bounds = {}
    
    for y in wall_bounds:
        left_walls = sorted(list(wall_bounds[y][left_wall_idx]))
        right_walls = sorted(list(wall_bounds[y][1 - left_wall_idx]))
        left_idx = 0
        right_idx = 0
        
        bound_start = left_walls[0]
        bounds[y] = []
        
        while right_idx < len(right_walls):
            if left_idx < len(left_walls):
                bounds[y] += [(bound_start, right_walls[-1])]
                break
            
            left = left_walls[left_idx]
            right = right_walls[right_idx]
            
            if left < right:
                bound_start = left
                left_idx += 1
            
            elif left > right:
                bound_end = right
                while bound_end <= left:
                    right_idx += 1
                    bound_end = right_walls[right_idx]
                
                bounds[y] += [(bound_start, bound_end)]
                right_idx += 1
        
    def check_bounds(p1,p2):
        left = min(p1[0], p2[0])
        right = max(p1[0], p2[0])
        top = min(p1[1], p2[1])
        bottom = max(p1[1], p2[1])
        for i in range(top, bottom + 1):
            if i not in bounds:
                return False
            
            in_bounds = False
            for b in bounds[i]:
                if b[0] <= left and right <= b[1]:
                    in_bounds = True
            
            if not in_bounds:
                return False
            
        return True
    
    m = 0
    for i in range(len(pos)):
        for j in range(i + 1, len(pos)):
            x1, y1 = pos[i]
            x2, y2 = pos[j]
            a = (abs(x1 - x2) + 1) * (abs(y1 - y2) + 1)
            if a > m and check_bounds(pos[i], pos[j]):
                m = a
    return m

run(9, part1, part2)