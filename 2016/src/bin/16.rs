use aoc2016::aoc;

pub fn part1(input:&str) -> String {
    let mut bits:Vec<u8> = input.chars().map(|c| if c == '1' {1} else {0}).collect();
    let size = 272;
    let mut odd_fact = size;
    while odd_fact % 2 == 0 {
        odd_fact /= 2;
    }
    let num2 = size/odd_fact;

    while bits.len() < size {
        let mut copy:Vec<u8> = bits.clone().iter().rev().map(|c| c ^ 1).collect();
        bits.push(0);
        bits.append(&mut copy);
    }
    let mut out = String::new();
    for str_idx in 0..odd_fact {
        let mut count = 0;
        for idx in 0..num2 {
            let curr_idx = str_idx * num2 + idx;
            count += bits[curr_idx];
        }
        out += &(1 - count % 2).to_string();
    }
    return out;
}


pub fn part2(input:&str) -> String {
    let mut bits:Vec<u8> = input.chars().map(|c| if c == '1' {1} else {0}).collect();
    let size = 35651584;
    let mut odd_fact = size;
    while odd_fact % 2 == 0 {
        odd_fact /= 2;
    }
    let num2 = size/odd_fact;

    while bits.len() < size {
        let mut copy:Vec<u8> = bits.clone().iter().rev().map(|c| c ^ 1).collect();
        bits.push(0);
        bits.append(&mut copy);
    }
    let mut out = String::new();
    for str_idx in 0..odd_fact {
        let mut count: u32 = 0;
        for idx in 0..num2 {
            let curr_idx = str_idx * num2 + idx;
            count += bits[curr_idx] as u32;
        }
        out += &(1 - count % 2).to_string();
    }
    return out;
}

fn main(){
    aoc::run(16,&part1,&part2);
}