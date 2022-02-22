package com.github.fscoward.notion.databases.query.filter.checkbox

import io.circe.*
import io.circe.generic.auto.*
import io.circe.parser.*
import io.circe.syntax.*

class CheckboxFilterConditionSpec extends munit.FunSuite {
  test("encode CheckboxFilterCondition to json") {
    val query: CheckboxFilter =
      CheckboxFilter(
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
