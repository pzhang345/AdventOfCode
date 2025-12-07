from utils import run
from collections import deque

def part1(input: str):
    board = [list(i) for i in input.splitlines()]
    pos = deque([(board[0].index("S"),0)])
    count = 0
    visited = set()
    
    def add(p):
        if p not in visited:
            visited.add(p)
            pos.append(p)
    
    while len(pos) > 0:
        x, y = pos.popleft()
        if y >= len(board):
            continue
        elif board[y][x] == "^":
            add((x - 1, y))
            add((x + 1, y))
            count += 1
        else:
            add((x,y + 1))
        
    return count


def part2(input: str):
    board = [list(i) for i in input.splitlines()]
    pos = deque([(board[0].index("S"),0)])
    visited = {}
    
    def add(p, parent):
        if p not in visited:
            visited[p] = visited.get(parent, 1)
            pos.append(p)
        else:
            visited[p] += visited.get(parent, 1)
    
    while len(pos) > 0:
        x, y = pos.popleft()
        if y >= len(board) - 1:
            continue
        elif board[y + 1][x] == "^":
            add((x - 1, y + 1), (x, y))
            add((x + 1, y + 1), (x, y))
        else:
            add((x, y + 1), (x, y))
    
    count = 0
    for k,v in visited.items():
        if k[1] == len(board) - 1:
            count += v
    return count


run(7, part1, part2)