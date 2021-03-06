package com.github.fscoward.notion.pages.property_item

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
  RetrieveAPagePropertyItemResponse,
  RichText
}

class RetrieveAPagePropertyItemResponseSuite extends munit.FunSuite {
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

    val actual = decode[RetrieveAPagePropertyItemResponse](json)
    val expected =
      RetrieveAPagePropertyItemResponse(
        next_cursor = None,
        results = Seq(
          RichText(
            `object` = "property_item",
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
