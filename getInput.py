import requests
import os
import sys

script_dir = os.path.dirname(os.path.abspath(__file__)) 
os.chdir(script_dir)

cookie = {"session":open("sessionID").read()}

def getInput(year,day):
    if not os.path.isdir(f"Inputs/{year}"):
        os.mkdir(f"Inputs/{year}")
    
    if not os.path.exists(f"Inputs/{year}/{str(day).zfill(2)}.txt"):
        conn = requests.get(f"https://adventofcode.com/{year}/day/{day}/input",cookies=cookie)

        if conn.status_code == 404:
            raise Exception("Invaild year or day")

        if conn.text.strip() == "Puzzle inputs differ by user.  Please log in to get your puzzle input.":
            raise Exception("Session ID is invalid")
        
        f = open(f"Inputs/{year}/{str(day).zfill(2)}.txt","w")
        f.write(conn.text.strip())
        f.close()
        conn.close()

    return open(f"Inputs/{year}/{str(day).zfill(2)}.txt").read()

if __name__ == "__main__":
    getInput(int(sys.argv[1]),int(sys.argv[2]))