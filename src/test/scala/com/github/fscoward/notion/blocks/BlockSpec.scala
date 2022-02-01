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
    "object": "block"
}
      """

    val expected = Block()
    val actual = decode[Block](json)
    assertEquals(actual, Right(expected))
  }
}
