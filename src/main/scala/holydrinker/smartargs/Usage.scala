package holydrinker.smartargs

import holydrinker.smartargs.core.SmartArgs
import holydrinker.smartargs.converters.SmartConverters._

object Usage {

  def main(args: Array[String]): Unit = {

    val manualArgs = Array(
      "--name", "peppo",
      "--age", "27",
      "--play-bass", "1"
    )

    val smartargs = SmartArgs(manualArgs)
    val name = smartargs.getAs[String]("name")
    val age = smartargs.getAs[Int]("age")
    val canPlayBass = smartargs.getAs[Boolean]("play-bass")
    println("done")
  }

}
