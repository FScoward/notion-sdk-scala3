package com.github.fscoward.notion.databases.query

import com.github.fscoward.notion.databases.query.filterCondition.TextFilterCondition
import munit.FunSuite
import io.circe.*
import io.circe.generic.auto.*
import io.circe.syntax.*
import io.circe.parser.*

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
