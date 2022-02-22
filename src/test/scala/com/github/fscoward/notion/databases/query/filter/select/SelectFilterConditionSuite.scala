package com.github.fscoward.notion.databases.query.filter.select

import io.circe.*
import io.circe.generic.auto.*
import io.circe.parser.*
import io.circe.syntax.*
import munit.FunSuite

class SelectFilterConditionSuite extends FunSuite {
  test("encode SelectFilterCondition to json") {
    val selectFilterCondition = SelectFilter(
      property = "Status",
      select = IsNotEmptyCondition()
    )
    val actual = selectFilterCondition.asJson
    val expected = parse("""
{
  "property": "Status",
  "select": {
    "is_not_empty": true
  }
}
    """).getOrElse(Json.Null)
    assertEquals(actual, expected)
  }
}
