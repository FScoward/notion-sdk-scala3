package com.github.fscoward.notion.databases.query.filter.text

import io.circe.*
import io.circe.generic.auto.*
import io.circe.parser.*
import io.circe.syntax.*
import munit.FunSuite

class TextFilterConditionSuite extends FunSuite {
  test("encode TextFilterCondition to json") {
    val filterCondition =
      TextFilter(
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
