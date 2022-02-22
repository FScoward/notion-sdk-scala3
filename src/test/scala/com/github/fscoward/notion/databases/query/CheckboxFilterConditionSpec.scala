package com.github.fscoward.notion.databases.query

import com.github.fscoward.notion.databases.query.filterCondition.CheckboxFilterCondition
import io.circe.*
import io.circe.generic.auto.*
import io.circe.syntax.*
import io.circe.parser.*

class CheckboxFilterConditionSpec extends munit.FunSuite {
  test("encode CheckboxFilterCondition to json") {
    val query: CheckboxFilterCondition =
      filterCondition.CheckboxFilterCondition(
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
