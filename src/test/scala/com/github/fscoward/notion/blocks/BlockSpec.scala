package com.github.fscoward.notion.blocks

import io.circe.*
import io.circe.generic.auto.*
import io.circe.parser.*
import io.circe.syntax.*

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class BlockSpec extends munit.FunSuite {
  test("decode json") {
    val json =
      // sample data from: https://developers.notion.com/reference/retrieve-a-block
      """
{
    "object": "block",
    "id": "9bc30ad4-9373-46a5-84ab-0a7845ee52e6",
    "created_time": "2021-03-16T16:31:00.000Z",
    "last_edited_time": "2021-03-16T16:32:00.000Z",
    "has_children": false
}
      """

    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    val expected = Block(
      id = "9bc30ad4-9373-46a5-84ab-0a7845ee52e6",
      created_time = LocalDateTime.parse(
        "2021-03-16T16:31:00.000Z",
        formatter
      ),
      last_edited_time = LocalDateTime.parse(
        "2021-03-16T16:32:00.000Z",
        formatter
      ),
      has_children = false
    )
    val actual = decode[Block](json)
    assertEquals(actual, Right(expected))
  }
}
