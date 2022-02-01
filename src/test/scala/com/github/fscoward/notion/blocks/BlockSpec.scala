package com.github.fscoward.notion.blocks

import io.circe.*
import io.circe.generic.auto.*
import io.circe.parser.*
import io.circe.syntax.*

class BlockSpec extends munit.FunSuite {
  test("decode json") {
    val json =
      // sample data from: https://developers.notion.com/reference/retrieve-a-block
      """
{
    "object": "block",
    "id": "9bc30ad4-9373-46a5-84ab-0a7845ee52e6"
}
      """

    val expected = Block(id = "9bc30ad4-9373-46a5-84ab-0a7845ee52e6")
    val actual = decode[Block](json)
    assertEquals(actual, Right(expected))
  }
}
