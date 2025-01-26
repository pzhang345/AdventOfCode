use aoc2016::aoc;

pub fn part1(input:&str) -> String {
    let mut password = "".to_string();
    let mut curr_num = 0;
    while password.len() != 8 {
        let hash = format!("{:x}",md5::compute(input.to_string() + &curr_num.to_string()));
        if hash[0..5] == *"00000" {
            password.push(hash.chars().nth(5).unwrap());
        }
        curr_num += 1
    }
    return password;
}


pub fn part2(input:&str) -> String {
    let mut password:[char; 8] = [' ',' ',' ',' ',' ',' ',' ',' '];
    let mut added = 0;
    let mut curr_num = 0;
    while added != 8 {
        let hash = format!("{:x}",md5::compute(input.to_string() + &curr_num.to_string()));
        if hash[0..5] == *"00000" {
            let pos = hash.chars().nth(5).unwrap().to_digit(16).unwrap() as usize;
            if pos < 8 && password[pos] == ' ' {
                password[pos] = hash.chars().nth(6).unwrap();
                added += 1;
            }
        }
        curr_num += 1
    }
    return password.iter().collect();
}
fn main(){
    aoc::run(5,&part1,&part2);
}