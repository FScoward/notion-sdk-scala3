package com.github.fscoward.notion.databases.query

import munit.FunSuite
import io.circe.*
import io.circe.generic.auto.*
import io.circe.syntax.*
import io.circe.parser.*

class ConditionSuite extends FunSuite {
  test("equal condition") {
    val actual = EqualsCondition(true).asJson
    val expected = parse("""{"equals": true}""").getOrElse(Json.Null)
    assertEquals(actual, expected)
  }
  test("does not equal condition") {
    val actual = DoesNotEqualCondition(true).asJson
    val expected = parse("""{"does_not_equal": true}""").getOrElse(Json.Null)
    assertEquals(actual, expected)
  }
  test("contains condition") {
    val actual = ContainsCondition("abc").asJson
    val expected = parse("""{"contains": "abc"}""").getOrElse(Json.Null)
    assertEquals(actual, expected)
  }
  test("does not contain condition") {
    val actual = DoesNotContainCondition("abc").asJson
    val expected =
      parse("""{"does_not_contain": "abc"}""").getOrElse(Json.Null)
    assertEquals(actual, expected)
  }
  test("starts with condition") {
    val actual = StartsWithCondition("abc").asJson
    val expected =
      parse("""{"starts_with": "abc"}""").getOrElse(Json.Null)
    assertEquals(actual, expected)
  }
  test("ends with condition") {
    val actual = EndsWithCondition("abc").asJson
    val expected =
      parse("""{"ends_with": "abc"}""").getOrElse(Json.Null)
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
