import holydrinker.smartargs.core.SmartArgs
import holydrinker.smartargs.converters.DefaultConverters._
import holydrinker.smartargs.converters.SmartConverter

case class Artist(name: String, surname: String)

object Artist {
  implicit val artistConverter = new SmartConverter[Artist] {
    override def convert(arg: String): Artist = {
      val split = arg.split("-")
      Artist(name = split(0), surname = split(1))
    }
  }
}

object CustomSequenceConversion {

  def main(args: Array[String]): Unit = {
    val realArgs = Array("--artists", "Dave-Grohl,Simon-Neil")
    val smartArgs = SmartArgs(realArgs)

    implicit val seqArtistConverter = converter[Artist]
    val artists = smartArgs.getAs[Seq[Artist]]("artists")

    println(artists) // this prints: Seq(Artist(Dave,Grohl), Artist(Simon,Neil))
  }

}