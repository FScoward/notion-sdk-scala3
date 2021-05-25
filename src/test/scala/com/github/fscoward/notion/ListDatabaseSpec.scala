package com.github.fscoward.notion

import io.circe._, io.circe.generic.auto._, io.circe.parser._, io.circe.syntax._

class ListDatabaseSpec extends munit.FunSuite {
  test("decode json") {
    val resultJson = """
    |{
    |  "results": [
    |    {
    |      "object": "database",
    |      "id": "668d797c-76fa-4934-9b05-ad288df2d136",
    |      "title": "Grocery list",
    |      "properties": {
    |        "Name": {
    |          "type": "title",
    |          "title": {}
    |        },
    |        "Description": {
    |          "type": "text",
    |          "text": {}
    |        }
    |      }
    |    },
    |    {
    |      "object": "database",
    |      "id": "74ba0cb2-732c-4d2f-954a-fcaa0d93a898",
    |      "title": "Pantry",
    |      "properties": {
    |        "Name": {
    |          "type": "title",
    |          "title": {}
    |        },
    |        "Description": {
    |          "type": "rich_text",
    |          "rich_text": {}
    |        }
    |      }
    |    }
    |  ],
    |  "next_cursor": "MTY3NDE4NGYtZTdiYy00NzFlLWE0NjctODcxOTIyYWU3ZmM3",
    |  "has_more": false
    |}
    """.stripMargin

    val expected = ""
    val actual = decode[ListDatabase](resultJson)

    println(actual)
    assert(actual.isRight)
    // TODO
    // assert(actual == expected)
  }
}
