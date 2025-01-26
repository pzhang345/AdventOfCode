use std::collections::{HashMap, HashSet};

use aoc2016::aoc;

fn support_tls(ip:&str) -> bool{
    let mut idx = 0;
    let mut in_brackets = false;
    let mut found = false;
    while idx <= ip.len() - 4 {
        let str = &ip[idx..idx+4];
        if str.contains("[") {
            in_brackets = true;
            idx += 4;
            continue;
        }
        else if str.contains("]") {
            in_brackets = false;
            idx += 4;
            continue;
        }
        if is_abba(str) {
            if in_brackets {
                return false
            }
            else {
                found = true;
            }
        }
        idx += 1
    }
    return found;
}

fn is_abba(input:&str) -> bool {
    let chars:Vec<char> = input.chars().collect();
    return chars[0] == chars[3] && chars[1] == chars[2] && chars[0] != chars[1];
}

fn support_ssl(ip:&str) -> bool {
    let mut idx = 0;
    let mut in_brackets = false;
    let mut maps = HashMap::from([(false,HashSet::new()),(true,HashSet::new())]);
    while idx <= ip.len() - 3 {
        let str = &ip[idx..idx+3];

        if str.contains("[") {
            in_brackets = true;
            idx += 3;
            continue;
        }
        else if str.contains("]") {
            in_brackets = false;
            idx += 3;
            continue;
        }

        if is_aba(str) {
            let current_map= maps.get_mut(&in_brackets).unwrap();
            if current_map.contains(str) {
                return true;
            }
            let oppisite_map= maps.get_mut(&!in_brackets).unwrap();
            oppisite_map.insert(get_bab(str));
        }
        idx += 1;
    }
    return false;
}

fn get_bab(input:&str) -> String {
    let chars:Vec<char> = input.chars().collect();
    let str_chars = [chars[1],chars[0],chars[1]];
    return str_chars.iter().collect();
}

fn is_aba(input:&str) -> bool {
    let chars:Vec<char> = input.chars().collect();
    return chars[0] == chars[2] && chars[0] != chars[1];
}

pub fn part1(input:&str) -> String {
    let mut count = 0;
    for line in input.lines() {
        if support_tls(line) {
            count += 1;
        }
    }
    return count.to_string();
}


pub fn part2(input:&str) -> String {
    let mut count = 0;
    for line in input.lines() {
        if support_ssl(line) {
            count += 1;
        }
    }
    return count.to_string();
}

fn main(){
    aoc::run(7,&part1,&part2);
}