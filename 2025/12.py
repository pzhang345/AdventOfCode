from utils import run

def part1(input: str):
    def greedy_tile_shapes(w, h, shapes):
        board = [['.' for _ in range(w)] for _ in range(h)]
        for i in range(h):
            for j in range(w):
                if board[i][j] == '#':
                    continue
                
                for shape in shapes:
                    if i + len(shape) >= h or j + len(shape[0]) >= w or shapes[shape] == 0:
                        continue
                    
                    can_place = True
                    for ishape in range(len(shape)):
                        for jshape in range(len(shape[0])):
                            if shape[ishape][jshape] == '#' and board[i + ishape][j + jshape] == '#':
                                    can_place = False
                                    break
                                
                        if not can_place:
                            break
                    
                    if can_place:
                        for ishape in range(len(shape)):
                            for jshape in range(len(shape[0])):
                                board[i + ishape][j + jshape] = shape[ishape][jshape]
                                
                    shapes[shape] -= 1
           
        sol = all(shapes[shape] == 0 for shape in shapes) 
        if sol == False:
            raise Exception("Failed greedy")
            
        return sol
                
    tokens  = input.strip().split("\n\n")
    presents = [tuple([tuple(j) for j in i.split("\n")[1:]]) for i in tokens[:-1]]
    boards = tokens[-1].split("\n")
    
    
    presents_sizes = ["".join(["".join(j) for j in i]).count("#") for i in presents]
    
    ret = 0
    for i in boards:
        dim, left  = i.split(": ")
        h, w = [int(i) for i in dim.split("x")]
        left = [int(i) for i in left.split(" ")]
        
        total_tiles = sum([presents_sizes[i] * iv for i,iv in enumerate(left)])
        allowed_miss = (w * h - total_tiles)
        if allowed_miss < 0:
            continue
        
        
        if greedy_tile_shapes(w, h, {presents[i]: left[i] for i in range(len(left))}):
            ret += 1
            continue
    
    return ret


def part2(input: str):
    pass

run(12, part1, part2, part=1)