package holydrinker.smartargs.core

import holydrinker.smartargs.converters._

/**
 * A SmartArgs object is a wrapper for a dictionary that contains a key -> value binding from
 * arguments names to arguments values
 *
 * @param map key - value dictionary
 */
final case class SmartArgs (map: Map[String, String]) {

  /**
   * The methods returns the argument value bound to the argument name passed in input.
   * The method requires that an evidence of a SmartConverter exists.
   *
   * @param name argument name to which retrieve the argument value
   * @tparam T the type of the returned argument value
   * @return the argument value
   */
  def getAs[T : SmartConverter](name: String): T = {
    val item = map(name)
    implicitly[SmartConverter[T]].convert(item)
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
