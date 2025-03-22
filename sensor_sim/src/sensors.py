import mysql.connector
from time import sleep
import threading as th
import random as ra
from datetime import datetime
import requests

ranges = {50: [0, 50], 75: [51, 100], 90: [101, 200], 100: [201, 300]}

# mutex = th.Lock()
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
    
    thread = th.Thread(target=interupt)
    thread.start()

    loop(conn)
    
    # threads=[]
    # for i in range(1, 25):
    #     try:
    #         print(f"Starting thread {i}...")
    #         thread = th.Thread(target=loop, args=(conn, i))
    #         threads.append(thread)
    #         thread.start()
    #     except Exception as e:
    #         threads.append(None)
    #         print(f"Thread {i} could not start, error: {e}")
    #     sleep(0.05)
    
    # try:
    #     th.Thread(target=interupt).start()
    # except Exception as e:
    #     print("Interupt thread failed, stopping proccess")
    #     stopEvent.set()

    
    # for thread in threads:
    #     if type(thread)==th.Thread:
    #         thread.join()
    
    # print("All threads stopped succesfully")
    conn.close()
    exit(0)



def interupt():
    input("Press enter to stop")
    print("Stopping all threads...")
    stopEvent.set()
    



def loop(conn: mysql.connector.CMySQLConnection) -> None:
    with conn.cursor() as cur:
        cur.execute("select count(*) from sensor")
        sensors = cur.fetchone()[0]
        while not stopEvent.is_set():
            delay = 0.05
            sleep(delay)
            # with mutex:
            id = ra.randint(1, sensors)
            rang = ra.randint(0, 100)
            if rang <= 50:
                concentation = ra.randint(0, 50)
            elif rang <= 75:
                concentation = ra.randint(51, 100)
            elif rang <= 85:
                concentation = ra.randint(101, 150)
            elif rang <= 90:
                concentation = ra.randint(151, 200)
            else:
                concentation = ra.randint(200, 300)
            sql = f"update sensor set concentration=%s, last_reading=%s where id=%s"
            vals = (concentation, datetime.now(), id)
            try:
                cur.execute(sql, vals)
                conn.commit()
                # print(f"Thread {id} updated table sensor")
            except Exception as e:
                print(f"Could not update sensor with id {id}")
                # print(f"Thread {id} could not update db, error: {e}")
            # print(f"Updated sensor {id} with value {concentation}")
        
        # print(f"Thread {id} stopped")
        



if __name__ == "__main__":
    main()