use std::collections::VecDeque;

use aoc2016::aoc;

pub fn part1(input:&str) -> String {
    let mut queue = VecDeque::from([(0,0,String::new())]);
    let dirs = [(0,-1),(0,1),(-1,0),(1,0)];
    let chars = ["U","D","L","R"];
    let valid = "bcdef";
    loop {
        let (x,y,str) = queue.pop_front().unwrap();
        let hash = format!("{:x}",md5::compute(input.to_string() + &str));
        for idx in 0..4 {
            if valid.contains(hash.chars().nth(idx).unwrap()) {
                let (dx,dy) = dirs[idx];
                let (nx,ny) = (x + dx, y + dy);
                let new_string = str.to_string() + chars[idx];
                if !(0 <= nx && nx < 4 && 0 <= ny && ny < 4) {
                    continue;
                }
                if (nx,ny) == (3,3) {
                    return new_string;
                }
                queue.push_back((nx,ny,new_string));
            }
        }
    }
}


pub fn part2(input:&str) -> String {
    let mut queue = VecDeque::from([(0,0,String::new())]);
    let dirs = [(0,-1),(0,1),(-1,0),(1,0)];
    let chars = ["U","D","L","R"];
    let valid = "bcdef";
    let mut max = 0;
    while queue.len() != 0 {
        let (x,y,str) = queue.pop_front().unwrap();
        let hash = format!("{:x}",md5::compute(input.to_string() + &str));
        for idx in 0..4 {
            if valid.contains(hash.chars().nth(idx).unwrap()) {
                let (dx,dy) = dirs[idx];
                let (nx,ny) = (x + dx, y + dy);
                let new_string = str.to_string() + chars[idx];
                if !(0 <= nx && nx < 4 && 0 <= ny && ny < 4) {
                    continue;
                }
                if (nx,ny) == (3,3) {
                    if new_string.len() > max {
                        max = new_string.len();
                    }
                    continue;
                }
                queue.push_back((nx,ny,new_string));
            }
        }
    }
    return max.to_string();
}

fn main(){
    aoc::run(17,&part1,&part2);
}