import holydrinker.smartargs.core.SmartArgs
import holydrinker.smartargs.converters.DefaultConverters._

object BasicSequenceConversion {

  def main(args: Array[String]): Unit = {
    val realArgs = Array("--artists", "Dave-Grohl,Simon-Neil")
    val smartArgs = SmartArgs(realArgs)
    val artists = smartArgs.getAs[Seq[String]]("artists")
    println(artists) // this prints: Seq(Dave-Grohl, Simon-Neil)
  }

}
