FROM python:3.11.2

WORKDIR /files

COPY requirements.txt .

RUN pip install -r requirements.txt

COPY db_reset.py .
COPY sensors.py .
COPY hexagons.json .
COPY sensors.json .