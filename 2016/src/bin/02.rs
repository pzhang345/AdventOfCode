use std::collections::HashMap;

use aoc2016::aoc;

pub fn part1(input:&str) -> String {
    let (mut x,mut y) = (1,1);
    let mut code = "".to_string();
    let map = HashMap::from([('U',(0,-1)),('D',(0,1)),('L',(-1,0)),('R',(1,0))]);
    for line in input.lines() {
        for char in line.chars() {
            let (dx,dy) = map.get(&char).unwrap();
            if 0 <= y + dy && y + dy <= 2 && 0 <= x + dx && x + dx <= 2 {
                x += dx;
                y += dy;
            }
        }
        code += &(y * 3 + x + 1).to_string();
    }
    return code;
}

pub fn part2(input:&str) -> String {
    let (mut x,mut y) = (0,2);
    let mut code = "".to_string();
    let button_map = HashMap::from([((2,0),'1'),((1,1),'2'),((2,1),'3'),((3,1),'4'),((0,2),'5'),((1,2),'6'),((2,2),'7'),((3,2),'8'),((4,2),'9'),((1,3),'A'),((2,3),'B'),((3,3),'C'),((2,4),'D')]);
    let move_map = HashMap::from([('U',(0,-1)),('D',(0,1)),('L',(-1,0)),('R',(1,0))]);
    for line in input.lines() {
        for char in line.chars() {
            let (dx,dy) = move_map.get(&char).unwrap();
            if button_map.contains_key(&(x + dx,y + dy)) {
                x += dx;
                y += dy;
            }
        }
        code.push(*button_map.get(&(x,y)).unwrap());
    }
    return code;
}

fn main(){
    aoc::run(2,&part1,&part2);
}