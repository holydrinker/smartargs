# smartarg

A simple scala library to parse your command line arguments.

## Accepted argument format
```--name peppo --age 26 --play-bass true```

## Why smartargs?
- smartargs usage adoption is super fast and light, you just need to follow the accepted argument format.

- you can map your command line arguments directly into domain object, just write your own converter for your own business class

## Usage
#### Simple Usage 
```scala
import holydrinker.smartargs.core.SmartArgs
import holydrinker.smartargs.converters.SmartConverters._

def main(args: Array[String]){
  val smartargs = SmartArgs(args)
  val name = smartargs.getAs[String]("name")
  val age = smartargs.getAs[Int]("age")
  val canPlayBass = smartargs.getAs[Boolean]("play-bass")
}
```
#### Custom Usage
```scala
import holydrinker.smartargs.converters.SmartConverter
import holydrinker.smartargs.core.SmartArgs

/* Defining your own business object */
case class Person(name: String, age: Int, canPlayBass: Boolean)

/*
Define you own converter

You know that you are gonna pass the person argument like this
--person peppo_27_true
*/
object PersonConverter {
  implicit val personConverter = new SmartConverter[Person] {
    override def convert(arg: String): Person = {
      val split = arg.split("_")
      Person(
        name = split(0),
        age = split(1).toInt,
        canPlayBass = split(2).toBoolean)
    }
  }
}

/* Putting things together */
object BusinessObjectConverterUsage {
  import PersonConverter._

  // --person peppo_27_true
  def main(args: Array[String]): Unit = {
    val smartargs = SmartArgs(args)
    val person = smartargs.getAs[Person]("person")
    println(s"Name: ${person.name} Age: ${person.age} CanPlayBass: ${person.canPlayBass}")
  }
}
```