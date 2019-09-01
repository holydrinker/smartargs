package holydrinker.smartargs.converters

trait SmartConverter[T] {

  def convert(arg: String): T

}

object SmartConverters {

  implicit val stringIdentity = new SmartConverter[String] {
    override def convert(arg: String): String =
      arg
  }

  implicit val stringToInt = new SmartConverter[Int] {
    override def convert(arg: String): Int = {
      arg.toInt
    }
  }

  implicit val stringToLong = new SmartConverter[Long] {
    override def convert(arg: String): Long = {
      arg.toLong
    }
  }

  implicit val stringToBoolean = new SmartConverter[Boolean] {
    override def convert(arg: String): Boolean = {
      arg.toBoolean
    }
  }

  implicit val stringToDouble = new SmartConverter[Double] {
    override def convert(arg: String): Double = {
      arg.toDouble
    }
  }

  implicit val strintToFloat = new SmartConverter[Float] {
    override def convert(arg: String): Float = {
      arg.toFloat
    }
  }
}