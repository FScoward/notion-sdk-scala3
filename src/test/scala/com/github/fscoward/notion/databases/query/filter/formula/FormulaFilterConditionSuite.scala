package com.github.fscoward.notion.databases.query.filter.formula

import munit.FunSuite
import io.circe.*
import io.circe.generic.auto.*
import io.circe.parser.*
import io.circe.syntax.*
import com.github.fscoward.notion.databases.query.filter.*
import text.conditionEncoder
import checkbox.conditionEncoder
import number.conditionEncoder
import date.conditionEncoder

import java.time.OffsetDateTime

class FormulaFilterConditionSuite extends FunSuite {
  test("text") {
    val actual: Json = FormulaTextFilter(
      property = "FormulaProp",
      text = text.ContainsCondition("Bridge")
    ).asJson

    val expected = parse("""
{
  "property": "FormulaProp",
  "text": {
        "contains": "Bridge"
  }
}
        """).getOrElse(Json.Null)
    assertEquals(actual, expected)
  }

  test("checkbox") {
    val actual: Json = FormulaCheckboxFilter(
      property = "FormulaProp",
      checkbox = checkbox.EqualsCondition(true)
    ).asJson

    val expected = parse("""
{
  "property": "FormulaProp",
  "checkbox": {
        "equals": true
  }
}
        """).getOrElse(Json.Null)
    assertEquals(actual, expected)
  }

  test("number") {
    val actual: Json = FormulaNumberFilter(
      property = "FormulaProp",
      number = number.EqualsCondition(10)
    ).asJson

    val expected = parse("""
{
  "property": "FormulaProp",
  "number": {
        "equals": 10
  }
}
        """).getOrElse(Json.Null)
    assertEquals(actual, expected)
  }

  test("date") {
    val datetimeStr = "2021-10-15T12:00:00-07:00"
    val actual: Json = FormulaDateFilter(
      property = "FormulaProp",
      date = date.EqualsCondition(OffsetDateTime.parse(datetimeStr))
    ).asJson

    val expected = parse(s"""
{
  "property": "FormulaProp",
  "date": {
    "equals": "$datetimeStr"
  }
}
        """).getOrElse(Json.Null)
    assertEquals(actual, expected)
  }
}
