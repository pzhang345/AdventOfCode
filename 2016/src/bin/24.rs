use std::collections::{HashMap, HashSet, VecDeque};

use aoc2016::aoc;

fn bfs(map:&HashMap<(i32,i32),char>,start_pos:(i32,i32)) -> HashMap<char,i32> {
    let mut queue = VecDeque::from([(start_pos,0)]);
    let mut done = HashSet::from([start_pos]);
    let mut bots = HashMap::new();
    let dirs = [(-1,0),(0,-1),(1,0),(0,1)];
    while !queue.is_empty() {
        let (pos,distance) = queue.pop_front().unwrap();
        for (dx,dy) in dirs {
            let new_pos = (pos.0 + dx, pos.1 + dy);
            if !map.contains_key(&new_pos) || done.contains(&new_pos) {
                continue;
            }
            if map[&new_pos] != '#'{
                done.insert(new_pos);
                queue.push_back((new_pos,distance + 1));
                if map[&new_pos] != '.' {
                    bots.insert(map[&new_pos], distance + 1);
                }
            }
        }
    }
    return bots;
}

fn find_shortest(bots:HashMap<char,HashMap<char,i32>>,start_pos:char,p1:bool) -> i32{
    let mut queue = VecDeque::from([(HashSet::from([start_pos]),start_pos,0)]);
    let mut min: i32 = i32::MAX;
    while !queue.is_empty() {
        let (set,curr_pos,steps) = queue.pop_front().unwrap();
        if set.len() == bots.len() {
            if p1 && steps < min {
                min = steps;
            }
            else if !p1 && steps + bots[&curr_pos][&start_pos] < min{
                min = steps + bots[&curr_pos][&start_pos];
            }
            continue;
        }
        for (k,v) in &bots[&curr_pos] {
            if set.contains(&k) {
                continue;
            }
            let mut new_set = set.clone();
            new_set.insert(*k);
            queue.push_back((new_set,*k,steps + v));

        }
    }
    return min;
}

pub fn part1(input:&str) -> String {
    let mut map = HashMap::new();
    let mut bots = HashMap::new();
    for (y,rows) in input.lines().enumerate() {
        for (x,c) in rows.chars().enumerate() {
            map.insert((x as i32,y as i32),c);
            if c != '#' && c != '.' {
                bots.insert(c, (x as i32,y as i32));
            }
        }
    }

    let mut bot_distance = HashMap::new();
    for (k,v) in bots {
        bot_distance.insert(k,bfs(&map,v));
    }
    return find_shortest(bot_distance, '0',true).to_string();
}


pub fn part2(input:&str) -> String {
    let mut map = HashMap::new();
    let mut bots = HashMap::new();
    for (y,rows) in input.lines().enumerate() {
        for (x,c) in rows.chars().enumerate() {
            map.insert((x as i32,y as i32),c);
            if c != '#' && c != '.' {
                bots.insert(c, (x as i32,y as i32));
            }
        }
    }

    let mut bot_distance = HashMap::new();
    for (k,v) in bots {
        bot_distance.insert(k,bfs(&map,v));
    }
    return find_shortest(bot_distance, '0',false).to_string();
}

fn main(){
    aoc::run(24,&part1,&part2);
}