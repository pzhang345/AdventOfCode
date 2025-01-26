use std::collections::{HashSet, VecDeque};

use aoc2016::aoc;

fn is_wall(x:i32,y:i32,des_num:i32) -> bool{
    let mut num = x*x + 3*x + 2*x*y + y + y*y + des_num;
    let mut count = 0;
    while num > 0 {
        count += num & 1;
        num = num >> 1;
    }
    return count % 2 == 1;
}
pub fn part1(input:&str) -> String {
    let mut queue = VecDeque::from([(1,1,0)]);
    let mut done = HashSet::from([(1,1)]);
    let dirs = [(-1,0),(0,-1),(0,1),(1,0)];
    let des_num = input.parse::<i32>().unwrap();
    loop {
        let (x,y,steps) = queue.pop_front().unwrap();
        for (dx,dy) in dirs {
            let (nx,ny) = (x + dx, y + dy);
            if nx < 0 || ny < 0 || done.contains(&(nx,ny)) || is_wall(nx, ny, des_num){
                continue;
            }
            else if (nx,ny) == (31,39) {
                return (steps + 1).to_string();
            }
            else {
                done.insert((nx,ny));
                queue.push_back((nx,ny,steps + 1));
            }
        }
    }
}


pub fn part2(input:&str) -> String {
    let mut queue = VecDeque::from([(1,1,0)]);
    let mut done = HashSet::from([(1,1)]);
    let dirs = [(-1,0),(0,-1),(0,1),(1,0)];
    let des_num = input.parse::<i32>().unwrap();
    loop {
        let (x,y,steps) = queue.pop_front().unwrap();
        if steps == 50 {
            return done.len().to_string();
        }
        for (dx,dy) in dirs {
            let (nx,ny) = (x + dx, y + dy);
            if nx < 0 || ny < 0 || done.contains(&(nx,ny)) || is_wall(nx, ny, des_num){
                continue;
            }
            else {
                done.insert((nx,ny));
                queue.push_back((nx,ny,steps + 1));
            }
        }
    }
}

fn main(){
    aoc::run(13,&part1,&part2);
}