use std::collections::HashSet;

use aoc2016::aoc;


pub fn part1(input:&str) -> String {
    let instrs = input.split(", ");
    let mut x = 0; 
    let mut y = 0;
    let mut idx = 0;
    let dirs = [(0,-1),(1,0),(0,1),(-1,0)];
    for instr in instrs {
        let dir = instr.chars().nth(0).unwrap();
        if dir == 'R' {
            idx += 1;
        }
        else {
            idx += 3;
        }
        idx %= 4;
        let dist = &instr[1..].parse::<i32>().unwrap();
        x += dist * dirs[idx].0;
        y += dist * dirs[idx].1;
    }
    return (x.abs() + y.abs()).to_string();
}

pub fn part2(input:&str) -> String {
    let instrs = input.split(", ");
    let mut x: i32 = 0;
    let mut y: i32 = 0;
    let mut idx = 0;
    let dirs = [(0,-1),(1,0),(0,1),(-1,0)];
    let mut set = HashSet::new();

    for instr in instrs {
        let dir = instr.chars().nth(0).unwrap();
        if dir == 'R' {
            idx += 1;
        }
        else {
            idx += 3;
        }
        idx %= 4;

        let dist = (&instr[1..]).parse::<i32>().unwrap();
        for _ in 0..dist {

            set.insert((x,y));
            x += dirs[idx].0;
            y += dirs[idx].1;
            if set.contains(&(x,y)) {
                return (x.abs() + y.abs()).to_string();
            }
        }
    }
    unreachable!();
}

fn main(){
    aoc::run(1,&part1,&part2);
}