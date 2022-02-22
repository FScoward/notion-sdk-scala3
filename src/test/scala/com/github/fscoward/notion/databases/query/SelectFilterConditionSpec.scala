package com.github.fscoward.notion.databases.query

import com.github.fscoward.notion.databases.query.filterCondition.SelectFilterCondition
import munit.FunSuite
import io.circe.*
import io.circe.generic.auto.*
import io.circe.syntax.*
import io.circe.parser.*

class SelectFilterConditionSpec extends FunSuite {
  test("encode SelectFilterCondition to json") {
    val selectFilterCondition = SelectFilterCondition(
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
