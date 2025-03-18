import aqlogo from "./assets/logo.png"
import map from "./assets/ioannina.png"
import hex from "./assets/hex1.png"
import hexr from "./assets/hexr.png"
import hexo from "./assets/hexo.png"
import hexy from "./assets/hexy.png"
import hexg from "./assets/hexg.png"
import './App.css'

export interface MapPoint {
  x: number; 
  y: number; 
  data: number;
}

const xoff = 24.7;
const yoff = 36.7;

export const points: MapPoint[] = [
  {x: 100+xoff*0,
   y: 100+xoff*0,
   data: 1
  },
  {x: 100+xoff*1,
   y: 100+yoff*1,
   data: 2
  },
  {x: 100+xoff*2,
   y: 100+yoff*2,
   data: 3
  },
  {x: 100+xoff*3,
    y: 100+yoff*1,
    data: 3
   },
   {x: 100+xoff*4,
    y: 100+yoff*2,
    data: 3
   },
   {x: 100+xoff*4,
    y: 100+yoff*0,
    data: 2
   },
   {x: 100+xoff*2,
    y: 100+yoff*0,
    data: 2
   },
   {x: 100+xoff*5,
    y: 100+yoff*1,
    data: 2
   },
  {x: 100+xoff*1,
    y: 100+yoff*3,
    data: 4
   },
  {x: 100+xoff*3,
  y: 100+yoff*3,
  data: 4
  },
  {x: 100+xoff*0,
    y: 100+yoff*2,
    data: 4
   },

];

function App() {
  return (
    <>  
      <head>
      <title>Aqioannina</title>
      <link rel="icon" type="image/x-icon" href="/favicon.ico" />
      </head>
        
        <div>
          <img src={aqlogo} className="logo" />
        </div>
        
        <div className="map-container">
          {
            points.map((point, index) => 
              {
                return (
                  <>
                    <img src={hex} className="map-point" 
                    key={index}
                    style={{top: point.y, left: point.x}}/>
                    <img src={point.data==1?hexg:
                              point.data==2?hexy:
                              point.data==3?hexo:
                              hexr
                    } className="map-point" 
                    key={index}
                    style={{top: point.y, left: point.x, opacity: "0.5"}}/>
                  </>
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
