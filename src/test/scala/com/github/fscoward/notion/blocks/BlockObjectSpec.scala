package com.github.fscoward.notion.blocks

import com.github.fscoward.notion.pages.annotation.NotionAnnotation
import com.github.fscoward.notion.pages.property.Text
import io.circe.*
import io.circe.generic.auto.*
import io.circe.parser.*
import io.circe.syntax.*

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class BlockObjectSpec extends munit.FunSuite {
  test("decode json") {
    val json =
      // sample data from: https://developers.notion.com/reference/retrieve-a-block
      """
{
    "object": "block",
    "id": "9bc30ad4-9373-46a5-84ab-0a7845ee52e6",
    "created_time": "2021-03-16T16:31:00.000Z",
    "last_edited_time": "2021-03-16T16:32:00.000Z",
    "has_children": false,
    "type": "to_do",
    "archived": false,
    "to_do": {
      "text": [
        {
          "type": "text",
          "text": {
            "content": "Lacinato kale",
            "link": null
          },
          "annotations": {
            "bold": false,
            "italic": false,
            "strikethrough": false,
            "underline": false,
            "code": false,
            "color": "default"
          }
        }
      ]
    }
}
      """

    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    val expected = BlockObject(
      id = "9bc30ad4-9373-46a5-84ab-0a7845ee52e6",
      created_time = LocalDateTime.parse(
        "2021-03-16T16:31:00.000Z",
        formatter
      ),
      last_edited_time = LocalDateTime.parse(
        "2021-03-16T16:32:00.000Z",
        formatter
      ),
      has_children = false,
      `type` = BlockType.to_do,
      archived = false,
      to_do = ToDoBlock(
        Seq(
          TodoBlockContent(
            text = Text(
              content = "Lacinato kale",
              link = None
            ),
            annotations = NotionAnnotation(
              false,
              false,
              false,
              false,
              "default"
            )
          )
        )
      )
    )
    val actual = decode[BlockObject](json)
    assertEquals(actual, Right(expected))
  }
}
