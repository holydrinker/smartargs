package holydrinker.smartargs.examples

import holydrinker.smartargs.core.SmartArgs
import holydrinker.smartargs.converters.SmartConverter

/* Defining very simple domain objects */
case class Name(value: String)
case class Age(value: Int)
case class CanPlayBass(value: Boolean)

/* Defining custom converters for them */
object SimpleCustomConverters {

  implicit val nameConverter = new SmartConverter[Name] {
    override def convert(arg: String): Name = Name(arg)
  }

  implicit val ageConverter = new SmartConverter[Age] {
    override def convert(arg: String): Age = Age(arg.toInt)
  }

  implicit val canPlayBassConverter = new SmartConverter[CanPlayBass] {
    override def convert(arg: String): CanPlayBass = arg match {
      case "1" =>CanPlayBass(true)
      case _   => CanPlayBass(false)
    }
  }
}

/* Putting pieces together */
object SimpleCustomConvertersUsage {

  import SimpleCustomConverters._

  def main(args: Array[String]): Unit = {
    val realArgs = Array(
      "--name", "peppo",
      "--age", "27",
      "--play-bass", "1")

    val smartargs = SmartArgs(realArgs)
    val name = smartargs.getAs[Name]("name")
    val age = smartargs.getAs[Age]("age")
    val canPlayBass = smartargs.getAs[CanPlayBass]("play-bass")

    println(s"Name: ${name.value}, Age: ${age.value}, Can play bass: ${canPlayBass.value}")
  }
}
