use std::collections::HashSet;

use aoc2016::aoc;

fn run_instr(input:&str,board:&mut HashSet<[u8;2]>,size:[u8;2]) {
    let tokens:Vec<&str> = input.split(" ").collect();
    if tokens[0] == "rect" {
        let coords:Vec<u8> = tokens[1].split("x").into_iter().map(|x| x.parse::<u8>().unwrap()).collect();
        rect(board,coords[0],coords[1])
    }
    else if tokens[0] == "rotate" {
        let axis = if tokens[1] == "row" {1} else {0};
        let val:u8 = tokens[2].split("=").last().unwrap().parse().unwrap();
        let offset:u8 = tokens[4].parse().unwrap();
        rotate(board,axis,val,offset,size[1-axis]);
    }

}

fn rect(board:&mut HashSet<[u8;2]>,max_x:u8,max_y:u8) {
    for y in 0..max_y {
        for x in 0.. max_x {
            board.insert([x,y]);
        }
    }
}

fn rotate(board:&mut HashSet<[u8;2]>,axis:usize,val:u8,offset:u8,size:u8) {
    let other_axis = 1 - axis;
    let mut remove = vec![];
    let mut add = vec![];
    for coord in board.clone().into_iter() {
        if coord[axis] == val {
            remove.push(coord);

            let mut array = [0,0];
            array[axis] = val;
            array[other_axis] = (coord[other_axis] + offset) % size;
            add.push(array);
        }
    }

    for elem in remove {
        board.remove(&elem);
    }

    for elem in add {
        board.insert(elem);
    }
}

fn board_to_string(board:&HashSet<[u8;2]>,size:[u8;2]) -> String{
    let mut out = "".to_string();
    for y in 0..size[1] {
        for x in 0..size[0] {
            if board.contains(&[x,y]) {
                out += "#";
            }
            else {
                out += " ";
            }
        }
        out += "\n";
    }
    return out;
}
pub fn part1(input:&str) -> String {
    let mut board = HashSet::new();
    let size = [50,6];
    for line in input.lines() {
        run_instr(line, &mut board,size);
    }
    return board.len().to_string();
}


pub fn part2(input:&str) -> String {
    let mut board = HashSet::new();
    let size = [50,6];
    for line in input.lines() {
        run_instr(line, &mut board,size);
    }
    return format!("\n{}",board_to_string(&board, size));
}

fn main(){
    aoc::run(8,&part1,&part2);
}