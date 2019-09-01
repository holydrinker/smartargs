package holydrinker.smartargs

import org.scalatest.FunSuite
import holydrinker.smartargs.converters.SmartConverters._

class SmartArgsSuite extends FunSuite {

  test("correctly build with simple args") {
    val args1 = Array(
      "--name", "peppo",
      "--surname", "lorusso"
    )

    val args2 = Array(
      "--name", "peppo"
    )

    val args3 = Array.empty[String]

    val actual1 = SmartArgs(args1)
    val expected1 = SmartArgs(Map(
      "name" -> "peppo",
      "surname" -> "lorusso"
    ))

    val actual2 = SmartArgs(args2)
    val expected2 = SmartArgs(Map(
      "name" -> "peppo"
    ))

    val actual3 = SmartArgs(args3)
    val expected3 = SmartArgs(Map.empty[String, String])

    assert(actual1 == expected1)
    assert(actual2 == expected2)
    assert(actual3 == expected3)
  }

  test("correctly access to simple args after build") {
    val args = Array(
      "--name", "peppo",
      "--age", "26",
      "--play-bass", "true"
    )

    val smartargs = SmartArgs(args)
                                                                      
    val actualName = smartargs.getAs[String]("name")
    val expectedName = "peppo"

    val actualAge = smartargs.getAs[Int]("age")
    val expectedAge = 26

    val actualPlayBass = smartargs.getAs[Boolean]("play-bass")
    val expectedPlayBass = true

    assert(actualName == expectedName)
    assert(actualAge == expectedAge)
    assert(actualPlayBass == expectedPlayBass)
  }
}
