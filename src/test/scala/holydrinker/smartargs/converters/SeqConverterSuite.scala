package holydrinker.smartargs.converters

import org.scalatest.FunSuite
import holydrinker.smartargs.core.SmartArgs

class SeqConverterSuite extends FunSuite with SeqConverters {

  test ("convert a list of string with comma as sep") {
    val args = Array("--artist", "dave,simon")
    val smartArgs = SmartArgs(args)

    val actual = smartArgs.getAs[Seq[String]]("artist")
    val expected = Seq("dave", "simon")
    assert(expected == actual)
  }

  test ("convert a list of int with comma as sep") {
    val args = Array("--numbers", "1,2")
    val smartArgs = SmartArgs(args)

    val actual = smartArgs.getAs[Seq[Int]]("numbers")
    val expected = Seq(1, 2)
    assert(expected == actual)
  }

  test ("convert a list of int together with a single int") {
    val args = Array(
      "--numbers", "1,2",
      "--single", "3"
    )
    val smartArgs = SmartArgs(args)

    val actualNumbers = smartArgs.getAs[Seq[Int]]("numbers")
    val expectedNumbers = Seq(1, 2)
    val actualSingle = smartArgs.getAs[Int]("single")
    val expectedSingle = 3

    assert(actualNumbers == expectedNumbers)
    assert(actualSingle == expectedSingle)
  }

  test ("convert a list of long with comma as sep") {
    val args = Array("--numbers", "1,2")
    val smartArgs = SmartArgs(args)
    val actual = smartArgs.getAs[Seq[Long]]("numbers")
    val expected = Seq(1L, 2L)
    assert(actual == expected)
  }

  test ("convert a list of boolean with comma as sep") {
    val args = Array("--flags", "true,false")
    val smartArgs = SmartArgs(args)
    val actual = smartArgs.getAs[Seq[Boolean]]("flags")
    val expected = Seq(true, false)
    assert(actual == expected)
  }

  test ("convert a list of double with comma as sep") {
    val args = Array("--numbers", "1.1,2.2")
    val smartArgs = SmartArgs(args)
    val actual = smartArgs.getAs[Seq[Double]]("numbers")
    val expected = Seq(1.1, 2.2)
    assert(actual == expected)
    assert(actual.isInstanceOf[Seq[Double]])
  }

  test ("convert a list of float with comma as sep") {
    val args = Array("--numbers", "1.1,2.2")
    val smartArgs = SmartArgs(args)
    val actual = smartArgs.getAs[Seq[Float]]("numbers")
    val expected = Seq(1.1F, 2.2F)
    assert(actual == expected)
  }

  test ("convert a list of a provided case class with comma as sep") {
    case class Artist(name: String, surname: String)
    implicit val artistConverter = new SmartConverter[Artist] {
      override def convert(arg: String): Artist = {
        val split = arg.split("-")
        Artist(name = split(0), surname = split(1))
      }
    }

    val args = Array("--artists", "Dave-Grohl,Simon-Neil")
    val smartArgs = SmartArgs(args)
    implicit val customerConverter = converter[Artist]
    val actual = smartArgs.getAs[Seq[Artist]]("artists")
    val expected = Seq(Artist("Dave", "Grohl"), Artist("Simon", "Neil"))
    assert(actual == expected)
  }

}
