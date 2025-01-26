use aoc2016::aoc;

use std::collections::HashMap;

fn get_value(register:&HashMap<&str,i32>,val:&str) -> i32 {
    let possible_number = val.parse::<i32>();
    return match possible_number {
        Ok(ok) => ok,
        Err(_) => *register.get(&val).unwrap(),
    }
}

fn cpy(register:&mut HashMap<&str,i32>,args:&Vec<&str>) -> i32 {
    if register.contains_key(args[2]) {
        *register.get_mut(args[2]).unwrap() = get_value(register, args[1]);
    }
    return 1;
}

fn inc(register:&mut HashMap<&str,i32>,args:&Vec<&str>) -> i32 {
    if register.contains_key(args[1]) {
        *register.get_mut(args[1]).unwrap() += 1;
    }
    return 1;
}

fn dec(register:&mut HashMap<&str,i32>,args:&Vec<&str>) -> i32 {
    if register.contains_key(args[1]) {
        *register.get_mut(args[1]).unwrap() -= 1;
    }
    return 1;
}

fn jnz(register:&mut HashMap<&str,i32>,args:&Vec<&str>) -> i32 {
    if get_value(register, args[1]) != 0 {
        return get_value(register, args[2]);
    }
    return 1;
}

fn tgl(register:&HashMap<&str,i32>,program:&mut Vec<Vec<&str>>,idx:i32) {
    let change_idx = get_value(register,program[idx as usize][1]) + idx;
    if !(0 <= change_idx && change_idx < program.len() as i32) {
        return;
    }

    let line = program.get_mut(change_idx as usize).unwrap();

    if line[0] == "tgl" || line[0] == "dec" {
        line[0] = "inc";
    }
    else if line[0] == "inc" {
        line[0] = "dec";
    }
    else if line[0] == "cpy" {
        line[0] = "jnz";
    }
    else if line[0] == "jnz" {
        line[0] = "cpy";
    }
}

fn run(mut program:Vec<Vec<&str>>,mut register: HashMap<&str,i32>,confidence_out:i32) -> bool{
    let mut idx = 0;
    let mut out_count = 0;
    type Command = dyn Fn(&mut HashMap<&str,i32>,&Vec<&str>) -> i32;
    let mut instructions: HashMap<&str, Box<Command>> = HashMap::new();
    instructions.insert("cpy", Box::new(cpy));
    instructions.insert("inc", Box::new(inc));
    instructions.insert("dec", Box::new(dec));
    instructions.insert("jnz", Box::new(jnz));

    while idx < program.len() as i32 {
        let tokens = &program[idx as usize];
        if tokens[0] == "out"{
            if get_value(&register, tokens[1]) != out_count % 2 {
                return false;
            }
            out_count += 1;
            if out_count > confidence_out {
                return true;
            }
            idx += 1;
        }
        else if tokens[0] == "tgl" {
            tgl(&register,&mut program, idx);
            idx += 1;
        }
        else{
            idx += instructions.get(tokens[0]).unwrap()(&mut register,tokens);
        }
    }
    return false;
}

pub fn part1(input:&str) -> String {
    let program = input.lines().map(|line| line.split(" ").collect::<Vec<&str>>()).collect::<Vec<Vec<&str>>>();
    let mut a_reg = 0;
    let confidence_out = 10;
    loop{
        let register = HashMap::from([("a",a_reg),("b",0),("c",0),("d",0)]);
        if run(program.clone(),register,confidence_out){
            return a_reg.to_string();
        }
        a_reg += 1;
    }
    
}

fn main(){
    aoc::run_part(25,"Part 1",&part1);
}