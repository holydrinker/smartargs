# smartarg

A simple scala library to parse your command line arguments.

## Accepted argument format
"--name peppo --age 26 --play-bass true"

## Usage 
```scala
import SmartConverters._

def main(args: Array[String]){
  val smartargs = SmartArgs(args)
  val name = smartargs.getAs[String]("name")
  val age = smartargs.getAs[Int]("age")
  val canPlayBass = smartargs.getAs[Boolean]("play-bass")
}
```
