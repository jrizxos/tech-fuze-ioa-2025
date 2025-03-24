import mysql.connector
from time import sleep
import threading as th
import random as ra
from datetime import datetime
import requests
import json

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
    
    with conn.cursor() as cur:
        try:
            cur.execute("delete from sensor")
            conn.commit()
            cur.execute("delete from hexagon")
            conn.commit()
            cur.execute("delete from connection")
            conn.commit()
        except Exception as e:
            print(e)
            exit(1)

    populate_sensor(conn)
    populate_hexagon(conn)
    
    print("All tables populated")
    exit(0)


def populate_sensor(conn: mysql.connector.CMySQLConnection):
    try:
        with open("./misc/sensors.json", "r") as file:
            data = json.load(file)
    except json.JSONDecodeError as e:
        print("Could not read sensor.json")

    with conn.cursor() as cur:
        try:
            for id in data.keys():
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
                sql = f"insert into sensor (id, concentration, last_reading) values(%s, %s, %s)"
                val = (int(id), concentation, datetime.now())
                cur.execute(sql, val)
                conn.commit()
        except Exception as e:
            print(f"Could not populate table sensor, {e}")

def populate_hexagon(conn):
    try:
        with open("./misc/hexagons.json", "r") as file:
            data = json.load(file)
    except json.JSONDecodeError as e:
        print("Could not read sensor.json")

    with conn.cursor() as cur:
        try:
            for id in data.keys():
                sql = f"insert into hexagon (id, latitude, longtitude) values(%s, %s, %s)"
                val = (int(id), int(data[id]["x"]), int(data[id]["y"]))
                cur.execute(sql, val)
                conn.commit()

                populate_connection(conn, id, data[id]["sensors"])
        except Exception as e:
            print(f"Could not populate table hexagon {e}")

def populate_connection(conn, hex_id, sens_ids):
    with conn.cursor() as cur:
        try:
            for sens_id in sens_ids:
                        sql = f"insert into connection (hexagon_id, sensor_id) values(%s, %s)"
                        val = (int(hex_id), int(sens_id))
                        cur.execute(sql, val)
                        conn.commit()
        except Exception as e:
            print(f"Could not populate table connection {e}")


        



if __name__ == "__main__":
    main()