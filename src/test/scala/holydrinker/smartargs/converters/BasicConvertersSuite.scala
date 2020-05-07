package holydrinker.smartargs.converters

import org.scalatest.FunSuite
import scala.util.Try

class BasicConvertersSuite extends FunSuite with BasicConverters {

  test ("identity conversion") {
    val data = Seq(
      "hello" -> "hello",
      "LOL" -> "LOL"
    )

    val result = data map (x => x._1 == stringIdentity.convert(x._2))
    assert(result.forall(_ == true))
  }

  test ("integer conversion") {
    val data = Seq(
      "1" -> Some(1),
      "-1" -> Some(-1),
      "100" -> Some(100),
      "hello, world" -> None,
      "true" -> None
    )

    val result = data map (x => x._2 == Try(stringToInt.convert(x._1)).toOption)
    assert(result.forall(_ == true))
  }

  test ("long conversion") {
    val data = Seq(
      "10000000" -> Some(10000000L),
      "-10000000" -> Some(-10000000L),
      "hello, world" -> None,
      "true" -> None
    )

    val result = data map (x => x._2 == Try(stringToLong.convert(x._1)).toOption)
    assert(result.forall(_ == true))
  }

  test ("double conversion") {
    val data = Seq(
      "123.456" -> Some(123.456D),
      "-123.456" -> Some(-123.456D),
      "hello, world" -> None,
      "true" -> None
    )

    val result = data map (x => x._2 == Try(stringToDouble.convert(x._1)).toOption)
    assert(result.forall(_ == true))
  }

  test ("float conversion") {
    val data = Seq(
      "123.456" -> Some(123.456F),
      "-123.456" -> Some(-123.456F),
      "hello, world" -> None,
      "true" -> None
    )

    val result = data map (x => x._2 == Try(stringToFloat.convert(x._1)).toOption)
    assert(result.forall(_ == true))
  }

  test ("boolean conversion") {
    val data = Seq(
      "true" -> Some(true),
      "TRUE" -> Some(true),
      "True" -> Some(true),
      "TruE" -> Some(true),
      "1" -> Some(true),
      "yes" -> Some(true),
      "YES" -> Some(true),
      "false" -> Some(false),
      "FALSE" -> Some(false),
      "False" -> Some(false),
      "FalsE" -> Some(false),
      "0" -> Some(false),
      "no" -> Some(false),
      "NO" -> Some(false),
      "hello, world" -> None,
      "2" -> None,
      "3" -> None
    )

    val result = data map (x => x._2 == Try(stringToBoolean.convert(x._1)).toOption)
    assert(result.forall(_ == true))
  }
}
