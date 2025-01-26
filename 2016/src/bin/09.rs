use aoc2016::aoc;

fn decompress(input:&str) -> u64 {
    let chars:Vec<char> = input.chars().collect();
    let mut idx = 0;
    let mut count = 0;
    while idx < input.len() {
        if chars[idx]  == '(' {
            let paren_end = input[idx..].find(')').unwrap();
            let marker = &input[idx+1..idx+paren_end];
            let increase:Vec<u64> = marker.split("x").map(|x| x.parse::<u64>().unwrap()).collect();
            let nested = &input[(idx + paren_end + 1)..(idx + paren_end + 1 + increase[0] as usize)];
            count += decompress(nested) * increase[1];
            idx += paren_end + 1 + increase[0] as usize;
        }
        else{
            idx += 1;
            count += 1;
        }
    }
    return count;

}

pub fn part1(input:&str) -> String {
    let input:String = input.chars().filter(|c| !c.is_whitespace()).collect();
    let chars:Vec<char> = input.chars().collect();
    let mut idx = 0;
    let mut count = 0;
    while idx < input.len() {
        if chars[idx]  == '(' {
            let paren_end = input[idx..].find(')').unwrap();
            let marker = &input[idx+1..idx+paren_end];
            let increase:Vec<i32> = marker.split("x").map(|x| x.parse::<i32>().unwrap()).collect();
            count += increase[0] * increase[1];
            idx += paren_end + 1 + increase[0] as usize;
        } 
        else {
            idx += 1;
            count += 1;
        }
    }
    return count.to_string();
}


pub fn part2(input:&str) -> String {
    let input:String = input.chars().filter(|c| !c.is_whitespace()).collect();
    return decompress(&input).to_string();
}

fn main(){
    aoc::run(9,&part1,&part2);
}