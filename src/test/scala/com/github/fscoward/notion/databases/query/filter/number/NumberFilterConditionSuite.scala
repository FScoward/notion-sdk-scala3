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
  test("greater than condition") {
    val actual = GreaterThanCondition(101).asJson
    val expected = parse("""{"greater_than": 101}""").getOrElse(Json.Null)
    assertEquals(actual, expected)
  }
  test("less than condition") {
    val actual = LessThanCondition(99).asJson
    val expected = parse("""{"less_than": 99}""").getOrElse(Json.Null)
    assertEquals(actual, expected)
  }
  test("greater than or equal to condition") {
    val actual = GreaterThanEqualToCondition(102).asJson
    val expected =
      parse("""{"greater_than_or_equal_to": 102}""").getOrElse(Json.Null)
    assertEquals(actual, expected)
  }
  test("less than or equal to condition") {
    val actual = LessThanEqualToCondition(98).asJson
    val expected =
      parse("""{"less_than_or_equal_to": 98}""").getOrElse(Json.Null)
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
