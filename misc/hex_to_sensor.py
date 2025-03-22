import json
import numpy as np
import matplotlib.pyplot as plt
from scipy.spatial import cKDTree

def remove_close_sensors(points, radius, new_hexagons, index_to_hexagon):
    tree = cKDTree(points)                                                          # KDTree for fast nearest neighbor lookup
    to_replace = {}                                                                 # key = indice of point to remove, value = indice of point that replaces the removed point

    for i in range(len(points)):
        if i in to_replace:
            continue                                                                # skip if already marked for removal
        neighbors = tree.query_ball_point(points[i], radius)                        # find neighbors within radius
        for j in neighbors:
            if j > i:                                                               # remove duplicates but keep the first occurrence
                to_replace[j] = i

    for point in to_replace:
        owner = index_to_hexagon[point]
        owner_points = new_hexagons[owner]['sensors']
        #print(points[point].tolist(), 'from', owner, owner_points)
        owner_points.remove(points[point].tolist())
        replacer = to_replace[point]
        owner_points.append(points[replacer].tolist())

    return np.array([p for i, p in enumerate(points) if i not in to_replace])

if __name__=='__main__':
    point_offsets = np.array([[0,64],[37,0],[111,0],[146,64],[111,128],[37,128]])       # offet for the vertexes of the hexagon

    new_hexagons = {}                                                                   # new hexagon database

    with open('layer_positions.json') as f:
        hexagon_dict = json.load(f)
        print(len(hexagon_dict),' hexagons in hexagon_dict')

        sensors = []
        sensor_index = 0
        index_to_hexagon = {}
        for hexagon in list(hexagon_dict.keys()):
            sensors_pos = hexagon_dict[hexagon]+point_offsets
            new_hexagons[hexagon] = {'x':hexagon_dict[hexagon][0],'y':hexagon_dict[hexagon][1],'sensors':[]}
            for sensor_pos in sensors_pos:
                sensors.append(sensor_pos)
                new_hexagons[hexagon]['sensors'].append(sensor_pos.tolist())
                index_to_hexagon[sensor_index] = hexagon
                sensor_index+=1

        sensors = np.array(sensors)
        culled_sensors = remove_close_sensors(sensors, 5, new_hexagons, index_to_hexagon).tolist()

        print(len(culled_sensors), 'sensors total')    

        sensors_dict = {}
        seen_sensors = {}
        sensor_index = 0

        for hexagon in new_hexagons:
            hex_sensors = [tuple(x) for x in new_hexagons[hexagon]['sensors']]
            sensor_ids = []
            for sensor in hex_sensors:
                if(sensor not in seen_sensors):
                    seen_sensors[sensor] = sensor_index
                    sensors_dict[sensor_index] = {'x':sensor[0], 'y':sensor[1]}
                    sensor_ids.append(sensor_index)
                    sensor_index+=1
                else:
                    sensor_ids.append(seen_sensors[sensor])
            new_hexagons[hexagon]['sensors'] = sensor_ids

        with open('sensors.json', 'w') as fp:
            json.dump(sensors_dict, fp)
        
        with open('hexagons.json', 'w') as fp:
            json.dump(new_hexagons, fp)

        print('Output json written!')