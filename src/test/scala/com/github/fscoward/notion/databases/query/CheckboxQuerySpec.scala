package com.github.fscoward.notion.databases.query

import io.circe._, io.circe.generic.auto._, io.circe.syntax._, io.circe.parser._

class CheckboxQuerySpec extends munit.FunSuite {
  test("encode CheckboxQuery to json") {
    val query: CheckboxQuery =
      CheckboxQuery(
        propertyName = "Done",
        property = CheckboxPropertyType.equals,
        value = true
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
