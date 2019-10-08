package holydrinker.smartargs

import holydrinker.smartargs.core.SmartArgs
import holydrinker.smartargs.converters.SmartConverters._


object WriteYouOwnConverter {

  case class Name(value: String)
  case class Age(value: Int)
  case class PlayBass(value: Boolean)

  val manualArgs = Array(
    "--name", "peppo",
    "--age", "27",
    "--play-bass", "1"
  )

  def main(args: Array[String]): Unit = {
    val smartargs = SmartArgs(manualArgs)

  }

}
