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

  val formatter: DateTimeFormatter =
    DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")

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
          },
          "plain_text": "Lacinato kale",
          "href": null
        }
      ],
      "checked": false
    }
}
      """

    val expected = TodoBlockObject(
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
          TextBlockContent(
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
            ),
            plain_text = "Lacinato kale",
            href = None
          )
        ),
        checked = false
      )
    )
    val actual = decode[TodoBlockObject](json)
    assertEquals(actual, Right(expected))
  }

  test("decode child_page") {
    val json = """
{
  "object": "block",
  "id": "96297f59-5fef-48fd-8498-b631b76ab7b6",
  "created_time": "2022-01-21T14:40:00.000Z",
  "last_edited_time": "2022-02-06T15:01:00.000Z",
  "has_children": true,
  "archived": false,
  "type": "child_page",
  "child_page": {
    "title": "Valkyrie"
  }
}
      """

    val expected = ChildPageBlockObject(
      id = "96297f59-5fef-48fd-8498-b631b76ab7b6",
      created_time = LocalDateTime.parse("2022-01-21T14:40:00.000Z", formatter),
      last_edited_time =
        LocalDateTime.parse("2022-02-06T15:01:00.000Z", formatter),
      has_children = true,
      `type` = BlockType.child_page,
      archived = false,
      child_page = ChildPageBlock("Valkyrie")
    )
    val actual = decode[ChildPageBlockObject](json)
    assertEquals(actual, Right(expected))
  }

  test("decode child_database") {
    val json = """
{
  "object": "block",
  "id": "ef917eff-3685-41d7-86e2-ee4b3641d3dd",
  "created_time": "2022-01-15T18:02:00.000Z",
  "last_edited_time": "2022-01-21T14:40:00.000Z",
  "has_children": false,
  "archived": false,
  "type": "child_database",
  "child_database": {
    "title": "Grocery List"
  }
}
      """
    val expected = ChildDatabaseBlockObject(
      id = "ef917eff-3685-41d7-86e2-ee4b3641d3dd",
      created_time = LocalDateTime.parse("2022-01-15T18:02:00.000Z", formatter),
      last_edited_time =
        LocalDateTime.parse("2022-01-21T14:40:00.000Z", formatter),
      has_children = false,
      `type` = BlockType.child_database,
      archived = false,
      child_database = ChildDatabaseBlock("Grocery List")
    )

    val actual = decode[ChildDatabaseBlockObject](json)
    assertEquals(actual, Right(expected))
  }

  test("decode paragraph") {
    val json = """
{
  "object": "block",
  "id": "b4887c6c-4de1-4425-95e9-0c0bb096a001",
  "created_time": "2021-05-23T02:49:00.000Z",
  "last_edited_time": "2021-05-23T02:49:00.000Z",
  "has_children": false,
  "archived": false,
  "type": "paragraph",
  "paragraph": {
    "text": [
      {
        "type": "text",
        "text": {
          "content": "test",
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
        "plain_text": "test",
        "href": null
      }
    ]
  }
}
      """
    val expected = ParagraphBlockObject(
      id = "b4887c6c-4de1-4425-95e9-0c0bb096a001",
      created_time = LocalDateTime.parse("2021-05-23T02:49:00.000Z", formatter),
      last_edited_time =
        LocalDateTime.parse("2021-05-23T02:49:00.000Z", formatter),
      has_children = false,
      `type` = BlockType.paragraph,
      archived = false,
      paragraph = ParagraphBlock(text =
        Seq(
          TextBlockContent(
            text = Text(
              content = "test",
              link = None
            ),
            annotations = NotionAnnotation(
              false,
              false,
              false,
              false,
              "default"
            ),
            plain_text = "test",
            href = None
          )
        )
      )
    )
    val actual = decode[ParagraphBlockObject](json)
    assertEquals(actual, Right(expected))
  }

}
