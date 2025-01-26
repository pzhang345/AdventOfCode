use std::collections::{HashMap, HashSet, VecDeque};

use aoc2016::aoc;

fn parse_input(input:&str) -> Vec<[u8;2]> {
    let mut idx_map = HashMap::new();
    let mut map = Vec::new();
    let mut idx:usize = 0;
    for (floor,line) in input.lines().enumerate() {
        let splice_idx = line.find("contains ").unwrap() + 9;
        let new_line = line.replace(".","").replace(" and",",").replace("-compatible","").replace(",,",",")[splice_idx..].to_owned();
        if new_line == "nothing relevant" {
            continue;
        }

        for elem in new_line.split(", ") {
            let tokens:Vec<String> = elem.split(" ").map(String::from).collect();
            if !idx_map.contains_key(&tokens[1]) {
                idx_map.insert(tokens[1].clone(), idx);
                map.push([0,0]);
                idx += 1;
            }

            let vec_idx = idx_map.get(&tokens[1]).unwrap();
            let side = if tokens[2] == "generator" {1} else {0};
            map[*vec_idx][side] = (floor + 1) as u8;
        }
    }
    return map;
}

fn check(map: &Vec<[u8;2]>) -> bool {
    let mut in_danger = HashSet::new();
    let mut generator_levels = HashSet::new();
    for items in map {
        if items[0] != items[1]{
            in_danger.insert(items[0]);
        }
        generator_levels.insert(items[1]);
    }
    for i in in_danger {
        if generator_levels.contains(&i) {
            return false;
        }
    }
    return true;
}

fn sort(map: &Vec<[u8;2]>) -> Vec<[u8;2]> {
    let mut new_map = map.to_vec();
    new_map.sort();
    return new_map;
}

fn step((map,floor,moves):(Vec<[u8;2]>,u8,u32),cache:&mut HashSet<(Vec<[u8;2]>,u8)>) -> VecDeque<(Vec<[u8;2]>,u8,u32)>{
    let mut add = VecDeque::new();
    for df in [-1,1] {
        let new_floor = (df + floor as i32) as u8;
        if ! (0 < new_floor && new_floor <= 4){
            continue;
        }
        for i in 0..(map.len() * 2) {
            for j in i..(map.len() * 2) {
                if floor == map[i/2][i%2] && floor == map[j/2][j%2] {
                    let mut new_map = map.clone();
                    new_map[i/2][i%2] = new_floor;
                    new_map[j/2][j%2] = new_floor;
                    if check(&new_map) {
                        let sorted = sort(&new_map);
                        if cache.contains(&(sorted.clone(),new_floor)) {
                            continue;
                        }
                        add.push_back((sorted.clone(),new_floor,moves + 1));
                        cache.insert((sorted,new_floor));
                    }
                }
            }
        }
    }
    
    
    return add;

}

fn bfs(map: Vec<[u8;2]>) -> u32{
    let mut cache = HashSet::from([(map.clone(),1)]);
    let mut queue = VecDeque::from([(map,1,0)]);
    while !queue.is_empty(){
        let current_state = queue.pop_front().unwrap();
        if current_state.0[0] == [4,4] {
            return current_state.2;
        }
        queue.append(&mut step(current_state,&mut cache));
    }
    unreachable!();
}

pub fn part1(input:&str) -> String {
    return bfs(sort(&parse_input(input))).to_string();
}


pub fn part2(input:&str) -> String {
    let mut map = parse_input(input);
    map.push([1,1]);
    map.push([1,1]);
    return bfs(sort(&map)).to_string();
}

fn main(){
    aoc::run(11,&part1,&part2);
}