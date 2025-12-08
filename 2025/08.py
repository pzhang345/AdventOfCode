import enum
from utils import run
import math

def part1(input: str):
    points = [tuple([int(j) for j in i.split(",")]) for i in input.splitlines()]

    distances = {}
    for i in range(len(points)):
        for j in range(i + 1, len(points)):
            p1 = points[i]
            p2 = points[j]

            d = math.sqrt((p1[0] - p2[0])**2 + (p1[1] - p2[1])**2 + (p1[2] - p2[2])**2)
            if d not in distances:
                distances[d] = []
                 
            distances[d] += [(p1, p2)] 
    dist_keys = sorted(distances.keys())
    idx = [0, 0]
    d = dist_keys[0]
    
    
    graph = {}
    def add(p1,p2):
        if p1 not in graph:
            graph[p1] = set()
        if p2 not in graph:
            graph[p2] = set()
        
        graph[p1].add(p2)
        graph[p2].add(p1)
    
    for _ in range(1000):
        if idx[1] >= len(distances[d]):
            idx[0] += 1
            idx[1] = 0
            d = dist_keys[idx[0]]
        
        p1, p2 = distances[d][idx[1]]
        add(p1,p2)
        idx[1] += 1
        
    
    visited = set()
    counts = []
    for start in graph.keys():
        if start in visited:
            continue
        count = 1
        stack = [start]
        visited.add(start)
        while len(stack) > 0:
            node = stack.pop()
            for neighbor in graph[node]:
                if neighbor not in visited:
                    stack.append(neighbor)
                    visited.add(neighbor)
                    count += 1
            
        counts.append(count)
    
    ret = 1
    for c in sorted(counts)[-3:]:
        ret *= c
    return ret
                
    

def part2(input: str):
    points = [tuple([int(j) for j in i.split(",")]) for i in input.splitlines()]

    distances = {}
    for i in range(len(points)):
        for j in range(i + 1, len(points)):
            p1 = points[i]
            p2 = points[j]

            d = math.sqrt((p1[0] - p2[0])**2 + (p1[1] - p2[1])**2 + (p1[2] - p2[2])**2)
            if d not in distances:
                distances[d] = []
                 
            distances[d] += [(p1, p2)] 
    dist_keys = sorted(distances.keys())
    idx = [0, 0]
    d = dist_keys[0]
    
    
    graph = {}
    def add(p1,p2):
        if p1 not in graph:
            graph[p1] = set([p1])
        
        s1 = graph[p1]
        s2 = graph.get(p2,set([p2]))
        s1.update(s2)
        for i in s2:
            graph[i] = s1
    
    while True:
        if idx[1] >= len(distances[d]):
            idx[0] += 1
            idx[1] = 0
            d = dist_keys[idx[0]]
        
        p1, p2 = distances[d][idx[1]]
        add(p1,p2)
        idx[1] += 1
        
        if len(graph[p1]) >= len(points):
            return p1[0] * p2[0]
        

run(8, part1, part2)