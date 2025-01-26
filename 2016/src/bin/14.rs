use std::collections::{HashMap, HashSet};

use aoc2016::aoc;

fn string_check(hash:String) -> (char,Vec<char>) {
    let mut first3 = ' ';
    let mut fives = vec![];
    let mut count = 0;
    let mut last_char = ' ';
    for c in hash.chars() {
        if c == last_char {
            count += 1;
            if count == 3 && first3 == ' ' {
                first3 = c;
            }
            else if count == 5 {
                fives.push(c);
            }
        }
        else {
            last_char = c;
            count = 1;
        }
    }

    return (first3,fives)

}
fn md5(input:String) -> String {
    return format!("{:x}",md5::compute(input));
}
pub fn part1(input:&str) -> String {
    let mut password = HashSet::new();
    let mut map = HashMap::new();
    for i in "0123456789abcdef".chars() {
        map.insert(i, vec![]);
    }
    let mut index = 0;
    let mut max = i32::MAX;
    while index <= max {
        let hash = md5(input.to_string() + &index.to_string());
        let (triple,fives) = string_check(hash);
        for c in fives {
            for idxs in (0..map[&c].len()).rev() {
                if index - map[&c][idxs] <= 1000 {
                    password.insert(map[&c][idxs]);
                    if password.len() > 64 {
                        max = map[&c][idxs] + 1000;
                    }
                }
                else {
                    map.get_mut(&c).unwrap().remove(idxs);
                }
            }
        }
        if triple != ' ' {
            map.get_mut(&triple).unwrap().push(index);
        }
        index += 1;
    }
    let mut one_time_pad = password.into_iter().collect::<Vec<i32>>();
    one_time_pad.sort();
    return one_time_pad[63].to_string();
}


pub fn part2(input:&str) -> String {
    let mut password = HashSet::new();
    let mut map = HashMap::new();
    for i in "0123456789abcdef".chars() {
        map.insert(i, vec![]);
    }
    let mut index = 0;
    let mut max = i32::MAX;
    while index <= max {
        let mut hash = input.to_string() + &index.to_string();
        for _ in 0..2017 {
            hash = md5(hash);
        }
        let (triple,fives) = string_check(hash);
        for c in fives {
            for idxs in (0..map[&c].len()).rev() {
                if index - map[&c][idxs] <= 1000 {
                    password.insert(map[&c][idxs]);
                    if password.len() > 64 {
                        max = map[&c][idxs] + 1000;
                    }
                }
                else {
                    map.get_mut(&c).unwrap().remove(idxs);
                }
            }
        }
        if triple != ' ' {
            map.get_mut(&triple).unwrap().push(index);
        }
        index += 1;
    }
    let mut one_time_pad = password.into_iter().collect::<Vec<i32>>();
    one_time_pad.sort();
    return one_time_pad[63].to_string();
}

fn main(){
    aoc::run(14,&part1,&part2);
}