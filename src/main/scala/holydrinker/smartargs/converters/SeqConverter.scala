package holydrinker.smartargs.converters
import BasicConverter._

private[converters] class SeqConverter[T : SmartConverter] extends SmartConverter[Seq[T]] {
  override def convert(arg: String): Seq[T] = {
    val itemConverter = implicitly[SmartConverter[T]]
    arg.split(",").toSeq.map(itemConverter.convert)
  }
}

object SeqConverter {

  implicit val stringSeqConverter = new SeqConverter[String]

  implicit val intSeqConverter = new SeqConverter[Int]

  implicit val longSeqConverter = new SeqConverter[Long]

  implicit val booleanSeqConverter = new SeqConverter[Boolean]

  implicit val doubleSeqConverter = new SeqConverter[Double]

  implicit val floatSeqConverter = new SeqConverter[Float]

  def converter[T : SmartConverter]() = new SeqConverter[T]

}
