package holydrinker.smartargs.converters


trait BasicConverters {

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

  implicit val stringToDouble = new SmartConverter[Double] {
    override def convert(arg: String): Double = {
      arg.toDouble
    }
  }

  implicit val stringToFloat = new SmartConverter[Float] {
    override def convert(arg: String): Float = {
      arg.toFloat
    }
  }

}


