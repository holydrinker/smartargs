# smartarg

A simple scala library to parse your command line arguments.

## Usage 
```scala
import SmartConverters._

val args = Array(       
  "--name", "peppo",    
  "--age", "26",        
  "--play-bass", "true" 
)                      

val smartargs = SmartArgs(args)
val name = smartargs.getAs[String]("name")
val age = smartargs.getAs[Int]("age")
val canPlayBass = smartargs.getAs[Boolean]("play-bass")
```
