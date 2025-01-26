use std::fs;
use std::path::Path;
use std::process::Command;
use std::time::{Duration, Instant};

fn get_input(day:u8) -> String {
    let path = "../Inputs/2016/".to_string() + &format!("{:0>2}", day.to_string()) + ".txt";
    if !Path::new(&path).exists() {
        let status = Command::new("py").args(["../getInput.py","2016",&day.to_string()]).status().expect("command not working");
        assert!(status.success());
    }
    return fs::read_to_string(path).expect("File not found");
}


fn bench_part(input:&str,f: &dyn Fn(&str)->String) -> (String,Duration){
    let now = Instant::now();
    let answer = f(input);
    let elapsed = now.elapsed();
    return (answer,elapsed)
}

fn execute_part(input:&str,f: &dyn Fn(&str)->String,name:&str) {
    let (answer,elapsed) = bench_part(input, f);

    println!("{name}: {answer}");
    println!("Elapsed: {:.2?}", elapsed);
    println!();
}

pub fn test(f: &dyn Fn(&str)->String) {
    let input = fs::read_to_string("test.txt").expect("File not found");

    execute_part(&input,f,"Test");
}

pub fn run(day:u8, part1: &dyn Fn(&str)->String,part2: &dyn Fn(&str)->String) {
    let input = get_input(day);

    execute_part(&input, part1, "Part 1");
    execute_part(&input, part2, "Part 2");
}

pub fn run_part(day:u8, name:&str, part: &dyn Fn(&str)->String) {
    let input = get_input(day);

    execute_part(&input, part, name);
}