package holydrinker.smartargs.examples

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
