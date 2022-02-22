package com.github.fscoward.notion.databases.query

import com.github.fscoward.notion.databases.query.filterCondition.CheckboxFilterCondition
import io.circe.*
import io.circe.generic.auto.*
import io.circe.syntax.*
import io.circe.parser.*

class SingleFilterQuerySpec extends munit.FunSuite {
  test("encode Single Filter Query with Checkbox to json") {
    val singleFilter = SingleFilterQuery(filter =
      CheckboxFilterCondition(
        property = "Seen",
        checkbox = EqualsCondition(false)
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
