package com.github.fscoward.notion.pages.read

import com.github.fscoward.notion.pages.annotation.NotionAnnotation
import com.github.fscoward.notion.pages.property.{RichTextProperty, Text}
import io.circe.*
import io.circe.Decoder.Result
import io.circe.generic.auto.*
import io.circe.parser.*
import io.circe.syntax.*
import cats.syntax.functor.*
import com.github.fscoward.notion.pages.property_item.{
  PagePropertyItem,
  RichText
}

case class PagePropertyItemResponse(
    `object`: String = "list",
    results: Seq[PagePropertyItem],
    next_cursor: Option[String],
    has_more: Boolean
)

import com.github.fscoward.notion.pages.property.propertyDecoder
implicit val pagePropertyItemDecoder: Decoder[PagePropertyItem] =
  List[Decoder[PagePropertyItem]](
    Decoder[RichText].widen
  ).reduceLeft(_ or _)

class RetrieveAPagePropertyItemResponseSpec extends munit.FunSuite {
  test("decode json") {
    val json =
      """
{
  "object": "list",
  "results": [
    {
      "object": "property_item",
      "type": "rich_text",
      "rich_text": {
        "type": "text",
        "text": {
          "content": "アイウエオ",
          "link": null
        },
        "annotations": {
          "bold": false,
          "italic": false,
          "strikethrough": false,
          "underline": false,
          "code": false,
          "color": "default"
        },
        "plain_text": "アイウエオ",
        "href": null
      }
    }
  ],
  "next_cursor": null,
  "has_more": false
}        
      """

    implicit val pagePropertyItemResponseDecoder
        : Decoder[PagePropertyItemResponse] =
      (c: HCursor) => {
        for {
          obj <- c.downField("object").as[String]
          results <- c.downField("results").as[Seq[PagePropertyItem]]
          next_cursor <- c.downField("next_cursor").as[Option[String]]
          has_more <- c.downField("has_more").as[Boolean]
        } yield PagePropertyItemResponse(obj, results, next_cursor, has_more)
      }
    val actual = decode[PagePropertyItemResponse](json)
    val expected =
      PagePropertyItemResponse(
        next_cursor = None,
        results = Seq(
          RichText(
            `object` = "property_item",
            `type` = "rich_text",
            rich_text = RichTextProperty(
              plain_text = "アイウエオ",
              href = None,
              annotations =
                NotionAnnotation(false, false, false, false, "default"),
              text = Text(content = "アイウエオ", link = None)
            )
          )
        ),
        has_more = false
      )
    assertEquals(actual, Right(expected))
  }
}
