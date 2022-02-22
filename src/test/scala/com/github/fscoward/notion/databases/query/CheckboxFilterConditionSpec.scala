package com.github.fscoward.notion.databases.query

import io.circe._, io.circe.generic.auto._, io.circe.syntax._, io.circe.parser._

class CheckboxFilterConditionSpec extends munit.FunSuite {
  test("encode CheckboxFilterCondition to json") {
    val query: CheckboxFilterCondition =
      CheckboxFilterCondition(
        property = "Done",
        checkbox = EqualsCondition(equals = true)
      )
    val actual: Json = query.asJson
    val expected = parse("""
{
  "property": "Done",
  "checkbox": {
    "equals": true
  }
}
        """).getOrElse(Json.Null)
    assertEquals(actual, expected)
  }
}
