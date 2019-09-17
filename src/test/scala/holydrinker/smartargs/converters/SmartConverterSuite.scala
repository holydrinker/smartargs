package holydrinker.smartargs.converters

import org.scalatest.FunSuite
import scala.util.Try

class SmartConverterSuite extends FunSuite {

  test ("identity conversion") {
    val converter = SmartConverters.stringIdentity
    val data = Seq(
      "hello" -> "hello",
      "LOL" -> "LOL"
    )

    val result = data map (x => x._1 == converter.convert(x._2))
    assert(result.forall(_ == true))
  }

  test ("integer conversion") {
    val converter = SmartConverters.stringToInt
    val data = Seq(
      "1" -> Some(1),
      "-1" -> Some(-1),
      "100" -> Some(100),
      "hello, world" -> None,
      "true" -> None
    )

    val result = data map (x => x._2 == Try(converter.convert(x._1)).toOption)
    assert(result.forall(_ == true))
  }

  test ("long conversion") {
    val converter = SmartConverters.stringToLong
    val data = Seq(
      "10000000" -> Some(10000000L),
      "-10000000" -> Some(-10000000L),
      "hello, world" -> None,
      "true" -> None
    )

    val result = data map (x => x._2 == Try(converter.convert(x._1)).toOption)
    assert(result.forall(_ == true))
  }

  test ("double conversion") {
    val converter = SmartConverters.stringToDouble
    val data = Seq(
      "123.456" -> Some(123.456D),
      "-123.456" -> Some(-123.456D),
      "hello, world" -> None,
      "true" -> None
    )

    val result = data map (x => x._2 == Try(converter.convert(x._1)).toOption)
    assert(result.forall(_ == true))
  }

  test ("float conversion") {
    val converter = SmartConverters.stringToFloat
    val data = Seq(
      "123.456" -> Some(123.456F),
      "-123.456" -> Some(-123.456F),
      "hello, world" -> None,
      "true" -> None
    )

    val result = data map (x => x._2 == Try(converter.convert(x._1)).toOption)
    assert(result.forall(_ == true))
  }

  test ("boolean conversion") {
    val converter = SmartConverters.stringToBoolean
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

    val result = data map (x => x._2 == Try(converter.convert(x._1)).toOption)
    assert(result.forall(_ == true))
  }
}
