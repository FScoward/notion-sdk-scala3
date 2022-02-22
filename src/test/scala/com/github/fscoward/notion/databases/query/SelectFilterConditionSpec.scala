package com.github.fscoward.notion.databases.query

import munit.FunSuite
import io.circe._, io.circe.generic.auto._, io.circe.syntax._, io.circe.parser._

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
