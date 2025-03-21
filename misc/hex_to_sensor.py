import json
import numpy as np
from scipy.spatial import cKDTree

def remove_close_points(points, radius):
    tree = cKDTree(points)  # Build a KDTree for fast nearest neighbor lookup
    to_remove = set()  # Indices of points to remove
    
    for i in range(len(points)):
        if i in to_remove:
            continue  # Skip if already marked for removal
        neighbors = tree.query_ball_point(points[i], radius)  # Find neighbors within radius
        for j in neighbors:
            if j > i:  # Remove duplicates but keep the first occurrence
                to_remove.add(j)
    
    return np.array([p for i, p in enumerate(points) if i not in to_remove])


point_offsets = np.array([[0,64],[37,0],[111,0],[146,64],[111,128],[37,128]])

hexes = {}

with open('layer_positions.json') as f:
    hex_pos = json.load(f)
    print(len(hex_pos))

    sensors = []
    for hex in hex_pos:
         sensors_pos = hex_pos[hex]+point_offsets
         for sensor_pos in sensors_pos:
            sensors.append(sensor_pos)
    sensors = np.array(sensors)
    print(sensors.shape)
    culled_sensors = remove_close_points(sensors, radius=2)
    print(culled_sensors.shape)