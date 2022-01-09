package com.github.fscoward.notion.databases.create

import io.circe._, io.circe.parser._
import io.circe.Encoder, io.circe.syntax._

class DatabasePropertySpec extends munit.FunSuite {
  test("create database property") {
    val actual = Parent("b24dcaf87a2140d0a1d85b393cd2019b")
    val expected = """
        {
          "type": "page_id",
          "page_id": "b24dcaf87a2140d0a1d85b393cd2019b"
        }
        """
    assertEquals(actual.asJson, parse(expected).getOrElse(Json.Null))
  }
}
