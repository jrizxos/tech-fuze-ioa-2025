import aq_logo from "./assets/logo.png";
import map from "./assets/ioannina.png";
import hex_r from "./assets/hexr.png";
import hex_o from "./assets/hexo.png";
import hex_y from "./assets/hexy.png";
import hex_g from "./assets/hexg.png";
import "./App.css";
import jsonData from "../../misc/layer_positions.json";
import { useRef, useState, useEffect } from "react";
import axios from "axios";

/* Map image dimensions */
const img_width = 2755;
const img_height = 2553;

export interface MapPoint {
  x: number;
  y: number;
  data: number;
  id: string; // Store the hexagon ID
}

/* Main */
function App() {
  const imgRef = useRef<HTMLImageElement>(null);
  const [renderedDimensions, setRenderedDimensions] = useState({ width: 0, height: 0 });
  const [mapPoints, setMapPoints] = useState<MapPoint[]>([]);

  /* Update dimensions */
  useEffect(() => {
    const updateDimensions = () => {
      if (imgRef.current) {
        setRenderedDimensions({
          width: imgRef.current.offsetWidth,
          height: imgRef.current.offsetHeight,
        });
      }
    };

    if (imgRef.current && imgRef.current.complete) {
      updateDimensions();
    }

    const imageElement = imgRef.current;
    if (imageElement) {
      imageElement.addEventListener("load", updateDimensions);
    }

    window.addEventListener("resize", updateDimensions);

    return () => {
      if (imageElement) {
        imageElement.removeEventListener("load", updateDimensions);
      }
      window.removeEventListener("resize", updateDimensions);
    };
  }, []);

  /* Get the map points */
  const scalePoint = (
    x: number,
    y: number,
    magic: number,
    renderedDimensions: { width: number; height: number },
    img_width: number,
    img_height: number
  ) => {
    const x_new = (x + magic) * (renderedDimensions.width / img_width);
    const y_new = (y + magic) * (renderedDimensions.height / img_height);
    return { x: x_new, y: y_new };
  };

  const parseJsonToMapPoints = (
    json: Record<string, number[]>,
    renderedDimensions: { width: number; height: number },
    img_width: number,
    img_height: number,
    magic: number
  ): MapPoint[] => {
    return Object.entries(json)
      .map(([key, [x, y]]) => {
        const { x: scaledX, y: scaledY } = scalePoint(x, y, magic, renderedDimensions, img_width, img_height);
        return { x: scaledX, y: scaledY, data: Infinity, id: key };
      });
  };

  /* Load hexagons and fetch data */
  useEffect(() => {
    const initialPoints = parseJsonToMapPoints(jsonData, renderedDimensions, img_width, img_height, 6.5);
    setMapPoints(initialPoints);

    initialPoints.forEach((point) => {
      axios
        .get(`http://localhost:8080/api/hexagon/${point.id}`)
        .then((response) => {
          setMapPoints((prev) =>
            prev.map((p) => (p.id === point.id ? { ...p, data: response.data } : p))
          );
        })
        .catch((error) => console.error(`Error fetching data for ${point.id}:`, error));
    });
  }, [renderedDimensions]); // Re-run when image resizes

  return (
    <>
      <head>
        <title>Aqioannina</title>
        <link rel="icon" type="image/x-icon" href="/favicon.ico" />
      </head>

      <div>
        <img src={aq_logo} className="logo" />
      </div>

      <div className="map-container">
        {mapPoints.map((point, index) => (
          <img
            src={
              point.data === Infinity
                ? hex_r // Default color before data loads
                : point.data < 25
                ? hex_g
                : point.data < 50
                ? hex_y
                : point.data < 200
                ? hex_o
                : hex_r
            }
            className="map-point"
            key={point.id} // Use `id` as key instead of index
            style={{ top: point.y, left: point.x, opacity: "0.35" }}
          />
        ))}
        <img ref={imgRef} src={map} className="map" />
      </div>
    </>
  );
}

export default App;
