package com.github.fscoward.notion.databases.query.filter.date

import munit.FunSuite
import io.circe.*
import io.circe.generic.auto.*
import io.circe.parser.*
import io.circe.syntax.*

import java.time.OffsetDateTime

class DateFilterConditionSuite extends FunSuite {
  test("encode DateFilterCondition to json") {
    val actual =
      EqualsCondition(
        OffsetDateTime.parse("2021-10-15T12:00:00-07:00")
      ).asJson
    val expected = parse("""
{
  "equals": "2021-10-15T12:00:00-07:00"
}
        """).getOrElse(Json.Null)
    assertEquals(actual, expected)
  }

  test("encode DateFilterCondition to json") {
    val actual = PastWeek(past_week = ()).asJson
    val expected = parse("""{"past_week": {}}""").getOrElse(Json.Null)
    assertEquals(actual, expected)
  }
}
