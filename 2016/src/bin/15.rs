use aoc2016::aoc;

fn modular_exponent(mut n:i128 ,mut x:i128 , p:i128) -> i128{
    let mut ans = 1;
    if x<=0 { return 1; }
    loop {
        if x==1 { return (ans * n) % p; }
        if x&1==0 { n=( n * n ) % p; x>>=1;continue; }
        else { ans = (ans*n) % p; x-=1; }
    }
}

fn gcd(mut a:i128, mut b:i128) -> i128{
    if a==b { return a; }
    if b > a { let temp = a;a = b;b = temp; }
    while b>0 { let temp = a;a = b;b = temp%b; }
    return a;
}

fn mod_inverse (n:i128, p:i128) -> i128{
    if p<=1 || gcd(n, p)>1 { return 0; }
    return modular_exponent(n, p-2, p);
}

fn crt(mods:Vec<i128>,rems:Vec<i128>) -> i128 {
    let mut total = 1;
    for m in &mods {
        total *= m;
    }
    
    let mut result = 0;
    for idx in 0..mods.len() {
        let others = total/mods[idx];
        result += rems[idx] * others * mod_inverse(others, mods[idx]);
    }
    result = result % total;
    if result < 0 {
        result += total;
    }
    return result;
}

pub fn part1(input:&str) -> String {
    let mut modulus = vec![];
    let mut remainder = vec![];
    for (i,line) in input.lines().enumerate() {
        let tokens:Vec<&str> = line.split(" ").into_iter().collect();
        let m = tokens[3].parse::<i128>().unwrap();
        modulus.push(m);
        remainder.push(m - tokens[11][0..tokens[11].len()-1].parse::<i128>().unwrap() - i as i128 - 1);
    }
    return crt(modulus,remainder).to_string();
}


pub fn part2(input:&str) -> String {
    let mut modulus = vec![];
    let mut remainder = vec![];
    for (i,line) in input.lines().enumerate() {
        let tokens:Vec<&str> = line.split(" ").into_iter().collect();
        let m = tokens[3].parse::<i128>().unwrap();
        modulus.push(m);
        remainder.push(m - tokens[11][0..tokens[11].len()-1].parse::<i128>().unwrap() - i as i128 - 1);
    }
    modulus.push(11);
    remainder.push(11 - input.lines().count() as i128 - 1);
    return crt(modulus,remainder).to_string();
}

fn main(){
    aoc::run(15,&part1,&part2);
}