package com.github.fscoward.notion.databases.query

import munit.FunSuite
import io.circe._, io.circe.generic.auto._, io.circe.syntax._, io.circe.parser._

class TextFilterConditionSpec extends FunSuite {
  test("encode TextFilterCondition to json") {
    val filterCondition =
      TextFilterCondition(
        property = "Landmark",
        text = ContainsCondition("Bridge")
      )
    val actual = filterCondition.asJson
    val expected = parse("""
{
  "property": "Landmark",
  "text": {
    "contains": "Bridge"
  }
}
    """).getOrElse(Json.Null)
    assertEquals(actual, expected)
  }
}
