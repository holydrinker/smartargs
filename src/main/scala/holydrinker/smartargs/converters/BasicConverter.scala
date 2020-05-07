package holydrinker.smartargs.converters


trait BasicConverter[T] extends SmartConverter[T]

object BasicConverter {

  implicit val stringIdentity = new BasicConverter[String] {
    override def convert(arg: String): String =
      arg
  }

  implicit val stringToInt = new BasicConverter[Int] {
    override def convert(arg: String): Int = {
      arg.toInt
    }
  }

  implicit val stringToLong = new BasicConverter[Long] {
    override def convert(arg: String): Long = {
      arg.toLong
    }
  }

  implicit val stringToBoolean = new BasicConverter[Boolean] {
    val trueValues = Set("true", "yes", "1")
    val falseValues = Set("false", "no", "0")

    override def convert(arg: String): Boolean = {
      val lowerArg = arg.toLowerCase
      if (trueValues.contains(lowerArg))
        true
      else if (falseValues.contains(lowerArg))
        false
      else
        throw new Exception("Cannot convert " + arg + "to Boolean.")
    }
  }

  implicit val stringToDouble = new BasicConverter[Double] {
    override def convert(arg: String): Double = {
      arg.toDouble
    }
  }

  implicit val stringToFloat = new BasicConverter[Float] {
    override def convert(arg: String): Float = {
      arg.toFloat
    }
  }

}


