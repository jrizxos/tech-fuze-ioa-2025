FROM python:3.11.2

WORKDIR /files

COPY requirements.txt .

RUN pip install -r requirements.txt

COPY ./sensor_sim/db_reset.py .
COPY ./sensor_sim/sensors.py .
COPY ./sensor_sim/data/hexagons.json .
COPY ./sensor_sim/data/sensors.json .