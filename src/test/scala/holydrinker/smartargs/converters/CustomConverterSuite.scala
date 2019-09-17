package holydrinker.smartargs.converters

import org.scalatest.FunSuite

import scala.util.Try

class CustomConverterSuite extends FunSuite {

  test ("simple wrapper test") {
    val converter = DomainImplicits.stringToName
    val data = Seq(
      "peppo" -> Some(Name("peppo"))
    )

    val result = data map (x => x._2 == Try(converter.convert(x._1)).toOption)
    assert(result.forall(_ == true))
  }
}

object DomainImplicits {
  implicit val stringToName = new SmartConverter[Name] {
    override def convert(arg: String): Name =
      Name(arg)
  }
}

case class Name(value: String)
