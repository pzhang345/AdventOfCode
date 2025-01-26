use aoc2016::aoc;

pub fn part1(input:&str) -> String {
    let mut ranges:Vec<(u64,u64)> = input.lines().map(|x| {let tokens:Vec<u64> = x.split("-").map(|t| t.parse::<u64>().unwrap()).collect(); (tokens[0],tokens[1])}).collect();
    ranges.sort();
    let mut curr_num = 0;

    for (min,max) in ranges {
        if curr_num < min {
            return curr_num.to_string();
        }
        
        if curr_num <= max {
            curr_num = max + 1;
        }
    }
    return curr_num.to_string();
}


pub fn part2(input:&str) -> String {
    let mut ranges:Vec<(u64,u64)> = input.lines().map(|x| {let tokens:Vec<u64> = x.split("-").map(|t| t.parse::<u64>().unwrap()).collect(); (tokens[0],tokens[1])}).collect();
    ranges.sort();
    let full_range =(0,4294967295);
    let mut min_bound = 0;
    let mut max_bound = 0;
    let mut combined_range = vec![];
    for (min,max) in ranges {
        if max_bound < min {
            combined_range.push((min_bound,max_bound));
            min_bound = min;
        }
        
        if max_bound <= max {
            max_bound = max + 1;
        }
    }

    combined_range.push((min_bound,max_bound));
    let mut available = (combined_range[0].0 - full_range.0) + (full_range.1 - (combined_range[combined_range.len() - 1].1 - 1));
    for idx in 1..combined_range.len() {
        available += combined_range[idx].0 - combined_range[idx-1].1; 
    }
    
    return available.to_string();
}

fn main(){
    aoc::run(20,&part1,&part2);
}