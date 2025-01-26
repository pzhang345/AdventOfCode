use std::collections::{HashMap, HashSet, VecDeque};

use aoc2016::aoc;

pub fn part1(input:&str) -> String {
    let mut tokens:Vec<Vec<String>> = input.lines().map(|line| line.replace("T","").split_whitespace().map(|str| str.to_string()).collect::<Vec<String>>()).collect();
    tokens.drain(0..2);
    let mut count = 0;
    for i in 0..tokens.len() {
        let token_from = &tokens[i];
        let token_from_size = token_from[2].parse::<i32>().unwrap();
        if token_from_size == 0 {
            continue;
        }
        for j in 0..tokens.len() {
            if i == j {
                continue;
            }
            let token_to = &tokens[j];
            let token_to_avail = token_to[3].parse::<i32>().unwrap();

            if token_to_avail >= token_from_size {
                count += 1;
            }

        }
    } 
    return count.to_string();
}


pub fn part2(input:&str) -> String {
    let mut tokens:Vec<Vec<String>> = input.lines().map(|line| line.replace("T","").replace("-"," ").split_whitespace().map(|str| str.to_string()).collect::<Vec<String>>()).collect();
    tokens.drain(0..2);
    let mut size_map = HashMap::new();
    let mut start_value_map = HashMap::new();
    let mut zero_x = 0;
    let mut zero_y = 0;
    let mut max_x = 0;
    let mut max_y = 0;
    for token in tokens {
        let x = token[1][1..].parse::<i32>().unwrap();
        let y = token[2][1..].parse::<i32>().unwrap();
        let size = token[3].parse::<i32>().unwrap();
        let value = token[4].parse::<i32>().unwrap();
        size_map.insert((x,y), size);
        start_value_map.insert((x,y),value);
        if value == 0 {
            zero_x = x;
            zero_y = y;
        }
        if x > max_x {
            max_x = x;
        }
        if y > max_y {
            max_y = y;
        }
    }
    let dirs = [(-1,0),(0,-1),(1,0),(0,1)];
    let mut queue = VecDeque::new();
    let mut done = HashSet::from([((zero_x,zero_y),(max_x,0))]);
    queue.push_back(((zero_x,zero_y),(max_x,0),start_value_map,0));
    loop {
        let (zero_pos,end_pos,value_map,score) = queue.pop_front().unwrap();
        for (dx,dy) in dirs {
            let new_pos = (zero_pos.0 + dx, zero_pos.1 + dy);
            if !(0 <= new_pos.0 && new_pos.0 <= max_x && 0 <= new_pos.1 && new_pos.1 <= max_y) {
                continue;
            }

            if value_map[&new_pos] > size_map[&zero_pos] {
                continue;
            }

            if done.contains(&(new_pos,end_pos)) {
                continue;
            } 

            let mut new_map = value_map.clone();
            new_map.insert(zero_pos,value_map[&new_pos]);
            new_map.insert(new_pos, 0);
            if new_pos == end_pos {
                if new_pos == (0,0) {
                    return score.to_string();
                }
                done.insert((new_pos,zero_pos));
                queue.push_back((new_pos,zero_pos,new_map,score + 1));
            }
            else {
                done.insert((new_pos,end_pos));
                queue.push_back((new_pos,end_pos,new_map,score + 1));
            }
        }
    }
}

fn main(){
    aoc::run(22,&part1,&part2);
}