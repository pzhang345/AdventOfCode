use std::collections::HashMap;

use aoc2016::aoc;

fn swap(arr:&mut Vec<char>,idx1:usize,idx2:usize) {
    let temp = arr[idx1];
    arr[idx1] = arr[idx2];
    arr[idx2] = temp;
}

fn swap_letter(arr:&mut Vec<char>,char1:char,char2:char) {
    for i in 0..arr.len() {
        if arr[i] == char1 {
            arr[i] = char2;
        }
        else if arr[i] == char2 {
            arr[i] = char1;
        }
    }
}

fn rotate(arr:&mut Vec<char>, num:usize) {
    let removed:Vec<char> = arr.drain(0..num).into_iter().collect();
    arr.extend(removed);
}
pub fn part1(input:&str) -> String {
    let mut arr:Vec<char> = "abcdefgh".chars().collect();
    for line in input.lines() {
        let tokens:Vec<&str> = line.split(" ").collect();
        if tokens[0] == "swap" {
            if tokens[1] == "letter" {
                let char1 = tokens[2].chars().nth(0).unwrap();
                let char2 = tokens[5].chars().nth(0).unwrap();   
                swap_letter(&mut arr, char1, char2);             
            }

            if tokens[1] == "position" {
                let idx1 = tokens[2].parse().unwrap();
                let idx2 = tokens[5].parse().unwrap();
                swap(&mut arr,idx1,idx2);
            }
        }
        else if tokens[0] == "rotate" {
            if tokens[1] == "left" {
                let num: usize = tokens[2].parse().unwrap();
                rotate(&mut arr, num);
            }
            else if tokens[1] == "right" {
                let num: usize = tokens[2].parse().unwrap();
                let rot_amount = arr.len() - num;
                rotate(&mut arr, rot_amount);
            }
            else {
                let mut rot_amount = arr.iter().position(|&r| r == tokens[6].chars().nth(0).unwrap()).unwrap();
                if rot_amount >= 4 {
                    rot_amount += 1;
                }
                rot_amount = (rot_amount + 1) % arr.len();
                rot_amount = arr.len() - rot_amount;
                rotate(&mut arr, rot_amount);
            }
        } 
        else if tokens[0] == "reverse" {
            let idx1:usize = tokens[2].parse().unwrap();
            let idx2:usize = tokens[4].parse().unwrap();
            for idx in 0..(idx2 - idx1 + 1)/2 {
                swap(&mut arr,idx1 + idx, idx2 - idx);
            }
        }
        else if tokens[0] == "move" {
            let remove = arr.remove(tokens[2].parse().unwrap());
            arr.insert(tokens[5].parse().unwrap(), remove);
        }
    }
    return arr.into_iter().collect();
}


pub fn part2(input:&str) -> String {
    let mut arr:Vec<char> = "fbgdceah".chars().collect();
    let mut rot_map = HashMap::new();
    for i in 0..arr.len(){
        let mut rot_amount = i;
        if rot_amount >= 4 {
            rot_amount += 1;
        }
        rot_amount = (rot_amount + 1) % arr.len();
        let final_pos = (i + rot_amount) % arr.len();
        rot_map.insert(final_pos,rot_amount);
    }
    for line in input.lines().rev() {
        let tokens:Vec<&str> = line.split(" ").collect();
        if tokens[0] == "swap" {
            if tokens[1] == "letter" {
                let char1 = tokens[2].chars().nth(0).unwrap();
                let char2 = tokens[5].chars().nth(0).unwrap();   
                swap_letter(&mut arr, char1, char2);             
            }

            if tokens[1] == "position" {
                let idx1 = tokens[2].parse().unwrap();
                let idx2 = tokens[5].parse().unwrap();
                swap(&mut arr,idx1,idx2);
            }
        }
        else if tokens[0] == "rotate" {
            if tokens[1] == "right" {
                let num: usize = tokens[2].parse().unwrap();
                rotate(&mut arr, num);
            }
            else if tokens[1] == "left" {
                let num: usize = tokens[2].parse().unwrap();
                let rot_amount = arr.len() - num;
                rotate(&mut arr, rot_amount);
            }
            else {
                let pos = arr.iter().position(|&r| r == tokens[6].chars().nth(0).unwrap()).unwrap();
                let rot_amount = rot_map[&pos];
                rotate(&mut arr, rot_amount);
            }
        }
        else if tokens[0] == "reverse" {
            let idx1:usize = tokens[2].parse().unwrap();
            let idx2:usize = tokens[4].parse().unwrap();
            for idx in 0..(idx2 - idx1 + 1)/2 {
                swap(&mut arr,idx1 + idx, idx2 - idx);
            }
        }
        else if tokens[0] == "move" {
            let remove = arr.remove(tokens[5].parse().unwrap());
            arr.insert(tokens[2].parse().unwrap(), remove);
        }
    }
    return arr.into_iter().collect();
}

fn main() {
    aoc::run(21,&part1,&part2);
}