package com.github.fscoward.notion.databases.query.filter.people

import munit.FunSuite

import io.circe.*
import io.circe.generic.auto.*
import io.circe.parser.*
import io.circe.syntax.*

import java.util.UUID

class PeopleFilterConditionSuite extends FunSuite {
  test("contains") {
    val uuid = UUID.randomUUID().toString
    val actual = ContainsCondition(contains = uuid).asJson
    val expected = parse(s"""{"contains": "$uuid"}""").getOrElse(Json.Null)
    assertEquals(actual, expected)
  }
  test("does not contains") {
    val uuid = UUID.randomUUID().toString
    val actual = DoesNotContainsCondition(does_not_contain = uuid).asJson
    val expected =
      parse(s"""{"does_not_contain": "$uuid"}""").getOrElse(Json.Null)
    assertEquals(actual, expected)
  }
}
