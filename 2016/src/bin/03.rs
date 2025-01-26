use aoc2016::aoc;

pub fn part1(input:&str) -> String {
    let mut valid = 0;

    for line in input.lines() {
        let sides = line.split_whitespace();
        let mut max = 0;
        let mut sum = 0;
        for side in sides {
            let val = side.parse::<i32>().unwrap();
            sum += val;
            if val > max {
                max = val;
            }
        }
        if 2 * max < sum {
            valid += 1;
        }
    }
    return valid.to_string();
}
pub fn part2(input:&str) -> String {
    let mut valid = 0;
    let mut max = [0,0,0];
    let mut sum = [0,0,0];
    for (i,line) in input.lines().enumerate() {
        let sides = line.split_whitespace();
        for (idx,side) in sides.enumerate() {
            let val = side.parse::<i32>().unwrap();
            sum[idx] += val;
            if val > max[idx] {
                max[idx] = val;
            }
        }

        if i % 3 == 2 {
            for idx in 0..3 {
                if 2 * max[idx] < sum[idx] {
                    valid += 1;
                }
            }
            max = [0,0,0];
            sum = [0,0,0];
        }
    }
    return valid.to_string();
}

fn main(){
    aoc::run(3,&part1,&part2);
}