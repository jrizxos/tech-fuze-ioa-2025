import aq_logo from "./assets/logo.png"
import map from "./assets/ioannina.png"
import hex_r from "./assets/hexr.png"
import hex_o from "./assets/hexo.png"
import hex_y from "./assets/hexy.png"
import hex_g from "./assets/hexg.png"
import './App.css'
import jsonData from './layer_positions.json';

import { useRef, useState, useEffect } from 'react';


/* map image dimensions */
const img_width = 2755;
const img_height = 2553;

export interface MapPoint {
  x: number; 
  y: number; 
  data: number;
}

/* main */
function App() {
  const imgRef = useRef<HTMLImageElement>(null);   
  const [renderedDimensions, setRenderedDimensions] = useState({ width: 0, height: 0 });

  useEffect(() => {                                                   /* this updates the points that are placed on the map */
    const updateDimensions = () => {
      if (imgRef.current) {                                           /* if the image element exists */
        setRenderedDimensions({                                       /* update the current dimensions state */
          width: imgRef.current.offsetWidth,
          height: imgRef.current.offsetHeight,
        });
      }
    };

    if (imgRef.current && imgRef.current.complete) {                   /* if the image has loaded */
      updateDimensions();                                              /* store its dimensions */
    }

    const imageElement = imgRef.current;                               /* in case the image hasn't loaded yet */
    if (imageElement) {                                                /* add an event listener to the image */
      imageElement.addEventListener('load', updateDimensions);         /* to update the dimensions when it does */
    }

    window.addEventListener('resize', updateDimensions);               /* also add an event listener for when the window is resized */

    return () => {                                                     /* cleanup function for when the component is unmounted */
      if (imageElement) {
        imageElement.removeEventListener('load', updateDimensions);    /* this is to avoid memory leaks */
      }
      window.removeEventListener('resize', updateDimensions);
    };
  }, []);

  /* get the map points */
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
    return Object.values(json)
      .filter((arr): arr is [number, number] => arr.length === 2) // Ensure valid tuples
      .map(([x, y]) => {
        const { x: scaledX, y: scaledY } = scalePoint(x, y, magic, renderedDimensions, img_width, img_height);
        return { x: scaledX, y: scaledY, data: 0 };
      });
  };
  const mapPoints = parseJsonToMapPoints(jsonData, renderedDimensions, img_width, img_height, 6.5);

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
          {
            mapPoints.map((point, index) => 
              {
                return (
                  <>
                   <img src={(point.data<25)?hex_g:
                             (25<=point.data && point.data<50)?hex_y:
                             (50<=point.data && point.data<200)?hex_o:
                              hex_r
                    } className="map-point" 
                    key={index}
                    style={{top: point.y, left: point.x, opacity: "0.35"}}/>
                  </>
                )
              }
            )
          }
          <img ref={imgRef} src={map} className="map"/>
        </div>
    </>
  )
}

export default App
