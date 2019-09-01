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
val name = smartargs.getAs[Int]("age")
val name = smartargs.getAs[Boolean]("play-bass")
```
