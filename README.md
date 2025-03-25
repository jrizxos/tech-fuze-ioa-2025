# Prototype app for TechFuse 2025 Hackathon

## Background

As part of iGEM Ioannina 2025, we took part in TechFuse 2025 Hackathon. Our task was to develop a solution to a current or future challenge facing our city, Ioannina.

## The Problem
Ioannina has the worst air quality in Greece, posing a significant problem for its citizens, particularly those with respiratory conditions. Any solution that involves 
clearing the atmosphere would require years of application to take effect, so we focused on how to help in the meantime.

## The Solution
The solution we devised consists of three parts:
- A network of sensors installed throughout the city
- A web app
- A wearable device

### Sensors
After some research, we found which type of sensors would be best for our use case. High-end sensors will be installed at the most crowded areas and low-end ones everywhere else.
This allows us to minimize costs while covering the whole city. These sensors will constantly monitor the air quality and send the data to our database. Finally, A Bluetooth antenna 
will be installed on each sensor, to enable the functionality of the wearable device.

### Wed app
Our app will feature a map of the city with a hexagonal grid. Each hexagon represents a group of sensors covering a specific area.
Colors ranging from green to red will indicate the air quality of each hexagonal area. The map updates in real-time to reflect any changes.

### Wearable device
Users can buy a wearable device which connects to the sensor network. If at any time the wearer approaches an area with poor air quality, the device will notify them  accordingly.


## Web app specifications

For the creation of the app we used Java Spring and React for the backend/frontend and Mysql for the database. 
The database holds all the sensors' readings and the sensors of each hexagon. Since the app doesn't use user data, there isn't an account requiremnt. 
The sensor network may expand in the future, we ho
Users can zoom in and out on the map for better readability and are able to set their own range of air quality, for personilized results.

## Credits
Frontend: John Rizos

Backend: Zisis Psalidas

Graphics: Iliana Rapti

<hr>

## Usage

In order to run all the components of the application, you need to install Java 21, MySQL, Node.js, and Python 11.
Otherwise, you can also use Docker and run the `dockerization` script.

<!-- Research: Sokratis -->

<!-- Bussiness plan: George  -->
