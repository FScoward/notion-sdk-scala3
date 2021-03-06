package com.github.fscoward.notion.blocks

import com.github.fscoward.notion.pages.annotation.NotionAnnotation
import com.github.fscoward.notion.pages.property.Text
import io.circe.*
import io.circe.generic.auto.*
import io.circe.parser.*
import io.circe.syntax.*

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class BlockObjectSuite extends munit.FunSuite {

  val formatter: DateTimeFormatter =
    DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")

  test("decode todo") {
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

  test("decode quote") {
    val json = """
{
  "object": "block",
  "id": "642f3572-6fcf-4f40-bcb1-0da3f0e2a5d1",
  "created_time": "2022-02-13T12:43:00.000Z",
  "last_edited_time": "2022-02-13T12:43:00.000Z",
  "has_children": false,
  "archived": false,
  "type": "quote",
  "quote": {
    "text": [
      {
        "type": "text",
        "text": {
          "content": "??????????????????",
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
        "plain_text": "??????????????????",
        "href": null
      }
    ]
  }
}
        """
    val expected = QuoteBlockObject(
      id = "642f3572-6fcf-4f40-bcb1-0da3f0e2a5d1",
      created_time = LocalDateTime.parse("2022-02-13T12:43:00.000Z", formatter),
      last_edited_time =
        LocalDateTime.parse("2022-02-13T12:43:00.000Z", formatter),
      has_children = false,
      `type` = BlockType.quote,
      archived = false,
      quote = QuoteBlock(text =
        Seq(
          TextBlockContent(
            text = Text(
              content = "??????????????????",
              link = None
            ),
            annotations = NotionAnnotation(
              false,
              false,
              false,
              false,
              "default"
            ),
            plain_text = "??????????????????",
            href = None
          )
        )
      )
    )
    val actual = decode[QuoteBlockObject](json)
    assertEquals(actual, Right(expected))
  }

  test("decode heading_1") {
    val json = """
{
  "object": "block",
  "id": "a123b6c7-28dc-4af5-ae05-0221356ba7f3",
  "created_time": "2022-02-13T15:42:00.000Z",
  "last_edited_time": "2022-02-13T15:42:00.000Z",
  "has_children": false,
  "archived": false,
  "type": "heading_1",
  "heading_1": {
    "text": [
      {
        "type": "text",
        "text": {
          "content": "?????????????????????",
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
        "plain_text": "?????????????????????",
        "href": null
      }
    ]
  }
}
        """
    val expected = Heading1BlockObject(
      id = "a123b6c7-28dc-4af5-ae05-0221356ba7f3",
      created_time = LocalDateTime.parse("2022-02-13T15:42:00.000Z", formatter),
      last_edited_time =
        LocalDateTime.parse("2022-02-13T15:42:00.000Z", formatter),
      has_children = false,
      `type` = BlockType.heading_1,
      archived = false,
      heading_1 = HeadingBlock(text =
        Seq(
          TextBlockContent(
            text = Text(
              content = "?????????????????????",
              link = None
            ),
            annotations = NotionAnnotation(
              false,
              false,
              false,
              false,
              "default"
            ),
            plain_text = "?????????????????????",
            href = None
          )
        )
      )
    )
    val actual = decode[Heading1BlockObject](json)
    assertEquals(actual, Right(expected))
  }

  test("decode heading_2") {
    val json = """
{
  "object": "block",
  "id": "15727d46-2ccc-4309-b90f-05ef50036b63",
  "created_time": "2022-02-14T15:20:00.000Z",
  "last_edited_time": "2022-02-14T15:20:00.000Z",
  "has_children": false,
  "archived": false,
  "type": "heading_2",
  "heading_2": {
    "text": [
      {
        "type": "text",
        "text": {
          "content": "?????????2????????????",
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
        "plain_text": "?????????2????????????",
        "href": null
      }
    ]
  }
}
        """
    val expected = Heading2BlockObject(
      id = "15727d46-2ccc-4309-b90f-05ef50036b63",
      created_time = LocalDateTime.parse("2022-02-14T15:20:00.000Z", formatter),
      last_edited_time =
        LocalDateTime.parse("2022-02-14T15:20:00.000Z", formatter),
      has_children = false,
      `type` = BlockType.heading_2,
      archived = false,
      heading_2 = HeadingBlock(text =
        Seq(
          TextBlockContent(
            text = Text(
              content = "?????????2????????????",
              link = None
            ),
            annotations = NotionAnnotation(
              false,
              false,
              false,
              false,
              "default"
            ),
            plain_text = "?????????2????????????",
            href = None
          )
        )
      )
    )
    val actual = decode[Heading2BlockObject](json)
    assertEquals(actual, Right(expected))
  }

  test("decode heading_3") {
    val json = """
{
  "object": "block",
  "id": "d03d32a5-2e5e-4ea3-867f-8bf1231e6aaa",
  "created_time": "2022-02-15T16:19:00.000Z",
  "last_edited_time": "2022-02-15T16:19:00.000Z",
  "has_children": false,
  "archived": false,
  "type": "heading_3",
  "heading_3": {
    "text": [
      {
        "type": "text",
        "text": {
          "content": "?????????3????????????",
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
        "plain_text": "?????????3????????????",
        "href": null
      }
    ]
  }
}
        """
    val expected = Heading3BlockObject(
      id = "d03d32a5-2e5e-4ea3-867f-8bf1231e6aaa",
      created_time = LocalDateTime.parse("2022-02-15T16:19:00.000Z", formatter),
      last_edited_time =
        LocalDateTime.parse("2022-02-15T16:19:00.000Z", formatter),
      has_children = false,
      `type` = BlockType.heading_3,
      archived = false,
      heading_3 = HeadingBlock(text =
        Seq(
          TextBlockContent(
            text = Text(
              content = "?????????3????????????",
              link = None
            ),
            annotations = NotionAnnotation(
              false,
              false,
              false,
              false,
              "default"
            ),
            plain_text = "?????????3????????????",
            href = None
          )
        )
      )
    )
    val actual = decode[Heading3BlockObject](json)
    assertEquals(actual, Right(expected))
  }

  test("decode table") {
    val json = """
{
  "object": "block",
  "id": "11155e3f-0ecb-4d3e-925a-35f4f5d18e18",
  "created_time": "2022-02-16T16:23:00.000Z",
  "last_edited_time": "2022-02-16T16:23:00.000Z",
  "has_children": true,
  "archived": false,
  "type": "table",
  "table": {
    "table_width": 2,
    "has_column_header": false,
    "has_row_header": false
  }
}
        """

    val actual = decode[TableBlockObject](json)
    val expected = TableBlockObject(
      id = "11155e3f-0ecb-4d3e-925a-35f4f5d18e18",
      created_time = LocalDateTime.parse("2022-02-16T16:23:00.000Z", formatter),
      last_edited_time =
        LocalDateTime.parse("2022-02-16T16:23:00.000Z", formatter),
      has_children = true,
      `type` = BlockType.table,
      archived = false,
      table = TableBlock(
        table_width = 2,
        has_column_header = false,
        has_row_header = false
      )
    )
    assertEquals(actual, Right(expected))
  }

  test("decode divider") {
    val json = """
{
  "object": "block",
  "id": "0792b859-1de7-4b86-8f68-7be26ad799cd",
  "created_time": "2022-02-16T16:44:00.000Z",
  "last_edited_time": "2022-02-16T16:44:00.000Z",
  "has_children": false,
  "archived": false,
  "type": "divider",
  "divider": {}
}
        """

    val actual = decode[DividerBlockObject](json)
    val expected = DividerBlockObject(
      id = "0792b859-1de7-4b86-8f68-7be26ad799cd",
      created_time = LocalDateTime.parse("2022-02-16T16:44:00.000Z", formatter),
      last_edited_time =
        LocalDateTime.parse("2022-02-16T16:44:00.000Z", formatter),
      has_children = false,
      `type` = BlockType.divider,
      archived = false,
      divider = ()
    )
    assertEquals(actual, Right(expected))

  }

}
