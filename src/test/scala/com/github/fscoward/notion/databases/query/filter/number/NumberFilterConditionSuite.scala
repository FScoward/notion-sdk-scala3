package com.github.fscoward.notion.databases.query.filter.number

import munit.FunSuite
import io.circe.*
import io.circe.generic.auto.*
import io.circe.syntax.*
import io.circe.parser.*

class NumberFilterConditionSuite extends FunSuite {
  test("equal condition") {
    val actual = EqualsCondition(10).asJson
    val expected = parse("""{"equals": 10}""").getOrElse(Json.Null)
    assertEquals(actual, expected)
  }
  test("does not equal condition") {
    val actual = DoesNotEqualCondition(100).asJson
    val expected = parse("""{"does_not_equal": 100}""").getOrElse(Json.Null)
    assertEquals(actual, expected)
  }
  test("is empty condition") {
    val actual = IsEmptyCondition().asJson
    val expected =
      parse("""{"is_empty": true}""").getOrElse(Json.Null)
    assertEquals(actual, expected)
  }
  test("is not empty condition") {
    val actual = IsNotEmptyCondition().asJson
    val expected =
      parse("""{"is_not_empty": true}""").getOrElse(Json.Null)
    assertEquals(actual, expected)
  }
}
