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
  {x: 100+xoff*1,
    y: 100+xoff*-1.5,
    data: 1
   },
  {x: 100+xoff*0,
   y: 100+xoff*0,
   data: 10
  },
  {x: 100+xoff*5,
    y: 100+xoff*4.5,
    data: 10
   },
   {x: 100+xoff*6,
    y: 100+xoff*6,
    data: 10
   },
   {x: 100+xoff*7,
    y: 100+xoff*7.5,
    data: 10
   },
   {x: 100+xoff*8,
    y: 100+xoff*9,
    data: 10
   },
   
   {x: 100+xoff*6,
    y: 100+xoff*3,
    data: 37
   },
   {x: 100+xoff*7,
    y: 100+xoff*4.5,
    data: 37
   },
   {x: 100+xoff*8,
    y: 100+xoff*6,
    data: 37
   },
   {x: 100+xoff*9,
    y: 100+xoff*7.5,
    data: 37
   },
   {x: 100+xoff*10,
    y: 100+xoff*9,
    data: 37
   },

   {x: 100+xoff*8,
    y: 100+xoff*3,
    data: 55
   },
   {x: 100+xoff*9,
    y: 100+xoff*4.5,
    data: 55
   },
   {x: 100+xoff*10,
    y: 100+xoff*6,
    data: 300
   },
   {x: 100+xoff*11,
    y: 100+xoff*7.5,
    data: 300
   },
   {x: 100+xoff*12,
    y: 100+xoff*9,
    data: 37
   },

  {x: 100+xoff*1,
   y: 100+yoff*1,
   data: 20
  },
  {x: 100+xoff*2,
   y: 100+yoff*2,
   data: 41
  },
  {x: 100+xoff*3,
    y: 100+yoff*1,
    data: 120
   },
   {x: 100+xoff*4,
    y: 100+yoff*2,
    data: 150
   },
   {x: 100+xoff*4,
    y: 100+yoff*0,
    data: 40
   },
   {x: 100+xoff*2,
    y: 100+yoff*0,
    data: 29
   },
   {x: 100+xoff*5,
    y: 100+yoff*1,
    data: 27
   },
  {x: 100+xoff*1,
    y: 100+yoff*3,
    data: 350
   },
  {x: 100+xoff*3,
  y: 100+yoff*3,
  data: 350
  },
  {x: 100+xoff*0,
    y: 100+yoff*2,
    data: 350
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
                   <img src={(point.data<25)?hexg:
                             (25<=point.data && point.data<50)?hexy:
                             (50<=point.data && point.data<200)?hexo:
                              hexr
                    } className="map-point" 
                    key={index}
                    style={{top: point.y, left: point.x, opacity: "0.35"}}/>
                    <img src={hex} className="map-point" 
                    key={index}
                    style={{top: point.y, left: point.x, opacity: "0.75"}}/>
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
