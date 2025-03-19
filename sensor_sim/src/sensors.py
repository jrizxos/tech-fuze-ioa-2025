import mysql.connector
from time import sleep
import threading as th
import random as ra
from datetime import datetime
import requests

mutex = th.Lock()
stopEvent = th.Event()
def main():
    conn = None
    times = 0
    while not conn and times < 10:
        try:
            #response = requests.get("http://mysql-container:3306")
            conn = mysql.connector.connect(
                user="tester",
                password="password",
                host="localhost",
                port=3305,
                database="data"

            )
        except mysql.connector.Error as e:
            print(f"Error connecting to MySQL Platform: {e}")
            sleep(5)
            times += 1
        
    if not conn:
        print("Unable to connect to database")
        exit(1)
    
    
    threads=[]
    for i in range(1, 25):
        try:
            print(f"Starting thread {i}...")
            thread = th.Thread(target=loop, args=(conn, i))
            threads.append(thread)
            thread.start()
        except Exception as e:
            threads.append(None)
            print(f"Thread {i} could not start, error: {e}")
        sleep(0.05)
    
    try:
        th.Thread(target=interupt).start()
    except Exception as e:
        print("Interupt thread failed, stopping proccess")
        stopEvent.set()

    
    for thread in threads:
        if type(thread)==th.Thread:
            thread.join()
    
    print("All threads stopped succesfully")
    exit(0)



def interupt():
    input("Press enter to stop")
    print("Stopping all threads...")
    stopEvent.set()
    



def loop(conn: mysql.connector.CMySQLConnection, id) -> None:
    cur = conn.cursor()
    while not stopEvent.is_set():
        delay = ra.randint(10, 20)
        sleep(delay)
        with mutex:
            try:
                value = ra.randint(50, 500)
                sql = f"update sensor set concentration=%s, last_reading=%s where id=%s"
                values = (value, datetime.now(), id)
                cur.execute(sql, values)
                conn.commit()
                print(f"Thread {id} updated table sensor")
            except Exception as e:
                print(f"Thread {id} could not update db, error: {e}")
    
    print(f"Thread {id} stopped")
        



if __name__ == "__main__":
    main()