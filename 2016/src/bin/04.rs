use std::collections::HashMap;

use aoc2016::aoc;

fn is_real(room:&str) -> bool {
    let check = &room[room.len()-6..room.len()-1];
        let mut char_map: HashMap<char, i32> = HashMap::new();
        for char in (&room[..room.len()-11]).chars() {
            if char == '-' {
                continue;
            }
            let char_val = char_map.get(&char).unwrap_or(&0) + 1;
            char_map.insert(char,char_val);
        }
        let mut hash_vec: Vec<(&char, &i32)> = char_map.iter().collect();
        hash_vec.sort_by_key(|a| (-a.1,a.0));

        let mut checksum = "".to_string();
        for i in 0..5 {
            checksum.push(*hash_vec[i].0);
        }

        return checksum == check;
}

fn decrypt(line:&str, rot:i32) -> String {
    let rot = rot % 26;
    let mut room = "".to_string();
    for char in (&line).chars() {
        if char == '-' {
            room.push(' ');
        }
        else {
            let char_add = (((char as u8 - b'a' + rot as u8) % 26) + b'a') as char;
            room.push(char_add);
        }
    }
    return room;
}

pub fn part1(input:&str) -> String {
    let mut sum = 0;
    for line in input.lines() {
        if is_real(line) {
            let val = line[line.len()-10..line.len()-7].parse::<i32>().unwrap();
            sum += val;
        }
    }
    return sum.to_string();
}


pub fn part2(input:&str) -> String {
    for line in input.lines() {
        if is_real(line) {
            let rot = line[line.len()-10..line.len()-7].parse::<i32>().unwrap();
            let room = decrypt(&line[..line.len() - 11], rot);
            
            if room.contains("north"){
                return rot.to_string();
            }
        }
    }
    unreachable!();
}
fn main(){
    aoc::run(4,&part1,&part2);
}