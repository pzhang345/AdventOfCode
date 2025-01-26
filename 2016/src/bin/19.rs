use std::collections::HashMap;

use aoc2016::aoc;

pub fn part1(input:&str) -> String {
    let size :i32= input.parse().unwrap();
    let mut linked_list:HashMap<i32,i32> = (1..size+1).map(|x| (x,(x+1))).collect();
    linked_list.insert(size,1); 

    let mut curr = 1;
    while curr != linked_list[&curr] {
        let next = linked_list[&curr];
        linked_list.insert(curr,linked_list[&next]);
        curr = linked_list[&next];
    }
    return curr.to_string();
}


pub fn part2(input:&str) -> String {
    let size :i32= input.parse().unwrap();
    let mut list:Vec<i32> = (1..size+1).collect();
    let mut idx = 0;
    while list.len() != 1 {
        let rm = (idx + list.len()/2) % list.len();
        list.remove(rm);
        if rm > idx {
            idx += 1;
        }
        idx = idx % list.len();
    }
    return list[0].to_string();
}

fn main(){
    aoc::run(19,&part1,&part2);
}