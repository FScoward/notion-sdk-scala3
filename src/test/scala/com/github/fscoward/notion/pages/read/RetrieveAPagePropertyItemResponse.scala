package com.github.fscoward.notion.pages.read

import io.circe.*
import io.circe.generic.auto.*
import io.circe.parser.*
import io.circe.syntax.*

case class PagePropertyItemResponse(
    `object`: String = "list",
    next_cursor: Option[String],
    has_more: Boolean
)

class RetrieveAPagePropertyItemResponseSpec extends munit.FunSuite {
  test("decode json") {
    val json =
      """
{
  "object": "list",
  "next_cursor": null,
  "has_more": false
}        
      """

    val actual = decode[PagePropertyItemResponse](json)
    val expected =
      PagePropertyItemResponse(next_cursor = None, has_more = false)
    assertEquals(actual, Right(expected))
  }
}
