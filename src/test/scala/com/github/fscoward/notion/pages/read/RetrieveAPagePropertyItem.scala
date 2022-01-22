package com.github.fscoward.notion.pages.read

import io.circe.*
import io.circe.generic.auto.*
import io.circe.parser.*
import io.circe.syntax.*

case class PagePropertyItem(
    `object`: String = "list",
    next_cursor: Option[String],
    has_more: Boolean
)

class RetrieveAPagePropertyItem extends munit.FunSuite {
  test("decode json") {
    val json =
      """
{
  "object": "list",
  "next_cursor": null,
  "has_more": false
}        
      """

    val actual = decode[PagePropertyItem](json)
    val expected = PagePropertyItem(next_cursor = None, has_more = false)
    assertEquals(actual, Right(expected))
  }
}
