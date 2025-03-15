import aqlogo from "./assets/logo.png"
import map from "./assets/ioannina.png"
import hex from "./assets/hex1.png"
import './App.css'

export interface MapPoint {
  x: number; 
  y: number; 
  data: number;
}

const xoff = 24.7;
const yoff = 36.7;

export const points: MapPoint[] = [
  {x: 100,
   y: 100,
   data: 1
  },
  {x: 100+xoff,
   y: 100+yoff,
   data: 1
  },
  {x: 100+xoff*2,
   y: 100+yoff*2,
   data: 1
  },
  {x: 100+xoff*3,
  y: 100+yoff*3,
  data: 1
   },
];

function App() {
  return (
    <>
        <div>
          <img src={aqlogo} className="logo" />
        </div>
        
        <div className="map-container">
          {
            points.map((point, index) => 
              {
                return (
                  <img src={hex} className="map-point" 
                  key={index}
                  style={{top: point.y, left: point.x}}/>
                )
              }
            )
          }
          <img src={map} className="map"/>
        </div>
    </>
  )
}

export default App
