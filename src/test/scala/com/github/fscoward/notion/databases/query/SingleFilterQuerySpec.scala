package com.github.fscoward.notion.databases.query

import io.circe._, io.circe.generic.auto._, io.circe.syntax._, io.circe.parser._

class SingleFilterQuerySpec extends munit.FunSuite {
  test("encode Single Filter Query with Checkbox to json") {
    val singleFilter = SingleFilterQuery(filter =
      CheckboxFilterCondition(
        propertyName = "Seen",
        CheckboxPropertyType.equals,
        value = false
      )
    )
    val expected = parse("""
{
    "filter": {
        "property": "Seen",
        "checkbox": {
          "equals": false
        }
    }
}
        """).getOrElse(Json.Null)

    val actual: Json = singleFilter.asJson
    assertEquals(actual, expected)
  }
}
