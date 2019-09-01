package holydrinker.smartargs

import holydrinker.smartargs.converters.SmartConverter

case class SmartArgs (map: Map[String, String]) {

  def getAs[T](name: String)(implicit converter: SmartConverter[T]): T = {
    val item = map(name)
    converter.convert(item)
  }

}

object SmartArgs {

  def apply(args: Array[String]): SmartArgs = {
    makeArgMapOpt(args) match {
      case Some(argMap) =>
        SmartArgs(argMap)
      case None =>
        throw new Exception("Wrong arguments format!")
    }
  }

  private def makeArgMapOpt(args: Array[String]): Option[Map[String, String]] = {

    def loop(args: Seq[String], acc: Map[String, String]): Option[Map[String, String]] = {
      args.toList match {
        case Nil =>
          Some(acc)
        case _ :: Nil =>
          None
        case key :: value :: xs if validKeyValuePair(key, value) =>
          loop(xs, appendKeyValuePair(key, value, acc))
        case _ =>
          None
      }
    }

    loop(args.toList, Map.empty[String, String])
  }

  private def validKeyValuePair(key: String, value: String): Boolean = {
    key.startsWith("--")
  }

  private def appendKeyValuePair(key: String, value: String, map: Map[String, String]): Map[String, String] = {
    val keyWithoutPrefix = key.substring(2)
    map + (keyWithoutPrefix -> value)
  }

}
