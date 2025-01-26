use std::collections::HashMap;

use aoc2016::aoc;


pub fn part1(input:&str) -> String {
    let mut maps = vec![];
    for line in input.lines() {
        for (idx,char) in line.chars().enumerate() {
            if idx >= maps.len() {
                maps.push(HashMap::new());
            }
            let map = maps.get_mut(idx).unwrap();
            let val = map.get(&char).unwrap_or(&0) + 1;
            map.insert(char,val);
        }
    }

    let mut word = "".to_string();
    for map in maps {
        let mut max = 0;
        let mut maxc = ' ';
        for (k,v) in map {
            if v > max {
                max = v;
                maxc = k;
            }
        }
        word.push(maxc);
    }
    return word;
}


pub fn part2(input:&str) -> String {
    let mut maps = vec![];
    for line in input.lines() {
        for (idx,char) in line.chars().enumerate() {
            if idx >= maps.len() {
                maps.push(HashMap::new());
            }
            let map = maps.get_mut(idx).unwrap();
            let val = map.get(&char).unwrap_or(&0) + 1;
            map.insert(char,val);
        }
    }

    let mut word = "".to_string();
    for map in maps {
        let mut min = i32::MAX;
        let mut minc = ' ';
        for (k,v) in map {
            if v < min {
                min = v;
                minc = k;
            }
        }
        word.push(minc);
    }
    return word;
}

fn main(){
    aoc::run(6,&part1,&part2);
}