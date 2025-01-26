use std::collections::{HashMap, VecDeque};

use aoc2016::aoc;

fn make_vec(map:&mut HashMap<u8,Vec<u8>>,key:u8){
    if !map.contains_key(&key) {
        map.insert(key, vec![]);
    }
}
fn add(map:&mut HashMap<u8,Vec<u8>>, key:u8, val: u8){
    make_vec(map, key);
    map.get_mut(&key).unwrap().push(val);
}

pub fn part1(input:&str) -> String {
    let mut give_map = HashMap::new();
    let mut bot_inv = HashMap::new();
    let mut queue = VecDeque::new();

    for line in input.lines() {
        let tokens:Vec<&str> = line.split(" ").collect();
        if tokens[0] == "value" {
            let val: u8 = tokens[1].parse().unwrap();
            let bot: u8 = tokens[5].parse().unwrap();
            add(&mut bot_inv,bot,val);
            if bot_inv.get(&bot).unwrap().len() == 2 {
                queue.push_back(bot);
            }
        }
        else {
            let bot: u8 = tokens[1].parse().unwrap();
            let low_type = tokens[5];
            let low: u8 = tokens[6].parse().unwrap();
            let high_type = tokens[10];
            let high: u8 = tokens[11].parse().unwrap();
            give_map.insert(bot,[(low_type,low),(high_type,high)]);
            
        }
    }
    
    let mut inv_map = HashMap::from([("bot",bot_inv),("output",HashMap::new())]);
    while !queue.is_empty() {
        let current_bot = queue.pop_front().unwrap();
        let current_inv = inv_map.get("bot").unwrap().get(&current_bot).unwrap();
        let rules = give_map.get(&current_bot).unwrap();
        let (low,high) = if current_inv[0] < current_inv[1] {(current_inv[0],current_inv[1])} else {(current_inv[1],current_inv[0])};

        if low == 17 && high == 61 {
            return current_bot.to_string();
        }

        let low_map = inv_map.get_mut(rules[0].0).unwrap();
        add(low_map,rules[0].1,low);
        if low_map.get(&rules[0].1).unwrap().len() == 2 {
            queue.push_back(rules[0].1);
        }

        let high_map = inv_map.get_mut(rules[1].0).unwrap();
        add(high_map,rules[1].1,high);
        if high_map.get(&rules[1].1).unwrap().len() == 2 {
            queue.push_back(rules[1].1);
        }
    }
    unreachable!()
}


pub fn part2(input:&str) -> String {
    let mut give_map = HashMap::new();
    let mut bot_inv = HashMap::new();
    let mut queue = VecDeque::new();

    for line in input.lines() {
        let tokens:Vec<&str> = line.split(" ").collect();
        if tokens[0] == "value" {
            let val: u8 = tokens[1].parse().unwrap();
            let bot: u8 = tokens[5].parse().unwrap();
            add(&mut bot_inv,bot,val);
            if bot_inv.get(&bot).unwrap().len() == 2 {
                queue.push_back(bot);
            }
        }
        else {
            let bot: u8 = tokens[1].parse().unwrap();
            let low_type = tokens[5];
            let low: u8 = tokens[6].parse().unwrap();
            let high_type = tokens[10];
            let high: u8 = tokens[11].parse().unwrap();
            give_map.insert(bot,[(low_type,low),(high_type,high)]);
            
        }
    }
    
    let mut inv_map = HashMap::from([("bot",bot_inv),("output",HashMap::new())]);
    while !queue.is_empty() {
        let current_bot = queue.pop_front().unwrap();
        let current_inv = inv_map.get("bot").unwrap().get(&current_bot).unwrap();
        let rules = give_map.get(&current_bot).unwrap();
        let (low,high) = if current_inv[0] < current_inv[1] {(current_inv[0],current_inv[1])} else {(current_inv[1],current_inv[0])};
        
        let low_map = inv_map.get_mut(rules[0].0).unwrap();
        add(low_map,rules[0].1,low);
        if low_map.get(&rules[0].1).unwrap().len() == 2 {
            queue.push_back(rules[0].1);
        }

        let high_map = inv_map.get_mut(rules[1].0).unwrap();
        add(high_map,rules[1].1,high);
        if high_map.get(&rules[1].1).unwrap().len() == 2 {
            queue.push_back(rules[1].1);
        }
    }
    
    let output = inv_map.get("output").unwrap();
    return (output.get(&0).unwrap()[0] as u16 * output.get(&1).unwrap()[0] as u16 * output.get(&2).unwrap()[0] as u16).to_string();
}

fn main(){
    aoc::run(10,&part1,&part2);
}