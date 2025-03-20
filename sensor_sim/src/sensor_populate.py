import mysql.connector
from time import sleep
import threading as th
import random as ra
from datetime import datetime
import requests

vals=[
    (1, 1,),
    (1, 2,),
    (1, 3,),
    (1, 4,),
    (1, 7,),
    (1, 8,),
    (2, 4,),
    (2, 6,),
    (2, 8,),
    (2, 10,),
    (2, 13,),
    (2, 14,),
    (3, 13,),
    (3, 14,),
    (3, 18,),
    (3, 16,),
    (3, 22,),
    (3, 20,),
    (4, 17,),
    (4, 18,),
    (4, 21,),
    (4, 22,),
    (4, 23,),
    (4, 24,),
    (5, 11,),
    (5, 12,),
    (5, 15,),
    (5, 17,),
    (5, 19,),
    (5, 21,),
    (6, 5,),
    (6, 3,),
    (6, 9,),
    (6, 7,),
    (6, 6,),
    (6, 12,),
    (7, 7,),
    (7, 8,),
    (7, 12,),
    (7, 13,),
    (7, 17,),
    (7, 18,)
    ]

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
    
    populate_sensor(conn)
    populate_hexagon(conn)
    populate_connection(conn)
    
    print("All threads stopped succesfully")
    exit(0)


def populate_sensor(conn: mysql.connector.CMySQLConnection):
    with conn.cursor() as cur:
        try:
            for i in range(1, 25):
                sql = f"insert into sensor (id, concentration, last_reading) values(%s, %s, %s)"
                val = (i, ra.randint(50, 500), datetime.now())
                cur.execute(sql, val)
                conn.commit()
        except Exception as e:
            print(f"Could not populate table sensor, {e}")

def populate_hexagon(conn):
    with conn.cursor() as cur:
        try:
            for i in range(1, 8):
                sql = f"insert into hexagon (id, latitude, longtitude) values(%s, %s, %s)"
                val = (i, 0, 0)
                cur.execute(sql, val)
                conn.commit()
        except Exception as e:
            print(f"Could not populate table hexagon {e}")

def populate_connection(conn):
    with conn.cursor() as cur:
        try:
            for i in range(42):
                sql = f"insert into connection (hexagon_id, sensor_id) values(%s, %s)"
                cur.execute(sql, vals[i])
                conn.commit()
        except Exception as e:
            print(f"Could not populate table connection {e}")


        



if __name__ == "__main__":
    main()