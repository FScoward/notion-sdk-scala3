package com.github.fscoward.notion.databases.query

import com.github.fscoward.notion.databases.query.filter.checkbox.{
  CheckboxFilter,
  EqualsCondition
}
import io.circe.*
import io.circe.generic.auto.*
import io.circe.syntax.*
import io.circe.parser.*

class SingleFilterQuerySuite extends munit.FunSuite {
  test("encode Single Filter Query with Checkbox to json") {
    val singleFilter = SingleFilterQuery(filter =
      CheckboxFilter(
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

    import com.github.fscoward.notion.databases.query.filter.checkbox.conditionEncoder
    val actual: Json = singleFilter.asJson
    assertEquals(actual, expected)
  }
}
