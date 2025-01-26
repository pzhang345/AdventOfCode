use std::collections::HashSet;

use aoc2016::aoc;

pub fn part1(input:&str) -> String {
    let mut prev = input.to_string();
    let mut count = input.chars().filter(|x| *x == '.').count();
    let trap = HashSet::from(["^..","..^","^^.",".^^"]);
    for _ in 1..40 {
        let mut top = ".".to_string() + &prev[0..1];
        let mut curr_level = String::new();
        for x in 0..prev.len() {
            if x == prev.len() - 1 {
                top.push('.');
            }
            else {
                top += &prev[x+1..x+2];
            }
            if trap.contains(&top[..]) {
                curr_level.push('^');
            }
            else {
                curr_level.push('.');
                count += 1;
            }

            top = top[1..].to_string();
        }
        prev = curr_level;
    } 
    return count.to_string();
}


pub fn part2(input:&str) -> String {
    let mut prev = input.to_string();
    let mut count = input.chars().filter(|x| *x == '.').count();
    let trap = HashSet::from(["^..","..^","^^.",".^^"]);
    for _ in 1..400000 {
        let mut top = ".".to_string() + &prev[0..1];
        let mut curr_level = String::new();
        for x in 0..prev.len() {
            if x == prev.len() - 1 {
                top.push('.');
            }
            else {
                top += &prev[x+1..x+2];
            }
            if trap.contains(&top[..]) {
                curr_level.push('^');
            }
            else {
                curr_level.push('.');
                count += 1;
            }

            top = top[1..].to_string();
        }
        prev = curr_level;
    } 
    return count.to_string();
}

fn main(){
    aoc::run(18,&part1,&part2);
}