package com.github.fscoward.notion.pages.create

import com.github.fscoward.notion.pages.create.block.{
  Children,
  Heading2Object,
  ParagraphBlock,
  TextObject,
  URL
}
import io.circe.*
import io.circe.generic.auto.*
import io.circe.parser.*
import io.circe.syntax.*

class PagePropertySpec extends munit.FunSuite {
  test("encode page property") {

    val p = PageProperty(
      PageParent("7b1e0686274f4ae6b6373c2236b57080"),
      Properties(
        Map(
          "Name" -> TitleProperty(TextPropertyValue("this is content")),
          "Description" -> RitchTextProperty(
            TextPropertyValue("A dark green leafy vegetable")
          ),
          "Food group" -> SelectProperty(
            SelectPropertyValue("Vegetable")
          ),
          "Price" -> NumberProperty(2.5),
          "Mail Address" -> EmailProperty("example1@example.com")
        )
      ),
      Children(
        Seq(
          Heading2Object(Seq(TextObject("Lacinato kale"))),
          ParagraphBlock(
            text = Seq(
              TextObject(
                content =
                  "Lacinato kale is a variety of kale with a long tradition in Italian cuisine, especially that of Tuscany. It is also known as Tuscan kale, Italian kale, dinosaur kale, kale, flat back kale, palm tree kale, or black Tuscan palm.",
                link = Some(URL("https://en.wikipedia.org/wiki/Lacinato_kale"))
              )
            )
          )
        )
      )
    )
    val actual: Json = p.asJson

    val expected =
      """|{
         |  "parent" : {
         |    "database_id" : "7b1e0686274f4ae6b6373c2236b57080"
         |  },
         |  "properties" : {
         |    "Name" : {
         |      "title" : [
         |        {
         |          "text" : {
         |            "content" : "this is content"
         |          }
         |        }
         |      ]
         |    },
         |    "Description" : {
         |      "rich_text" : [
         |        {
         |          "text" : {
         |            "content" : "A dark green leafy vegetable"
         |          }
         |        }
         |      ]
         |    },
         |    "Food group" : {
         |      "select" : {
         |        "name" : "Vegetable"
         |      }
         |    },
         |    "Price" : {
         |      "number" : 2.5
         |    },
         |    "Mail Address" : {
         |      "email" : "example1@example.com"
         |    }
         |  },
         |  "children" : [
         |    {
         |      "object" : "block",
         |      "type" : "heading_2",
         |      "heading_2" : {
         |        "text" : [
         |          {
         |            "type" : "text",
         |            "text" : {
         |              "content" : "Lacinato kale"
         |            }
         |          }
         |        ]
         |      }
         |    },
         |    {
         |      "object" : "block",
         |      "type" : "paragraph",
         |      "paragraph" : {
         |        "text" : [
         |          {
         |            "type" : "text",
         |            "text" : {
         |              "content" : "Lacinato kale is a variety of kale with a long tradition in Italian cuisine, especially that of Tuscany. It is also known as Tuscan kale, Italian kale, dinosaur kale, kale, flat back kale, palm tree kale, or black Tuscan palm.",
         |              "link" : {
         |                "url" : "https://en.wikipedia.org/wiki/Lacinato_kale"
         |              }
         |            }
         |          }
         |        ]
         |      }
         |    }
         |  ]
         |}""".stripMargin

    assertEquals(actual, parse(expected).getOrElse(Json.Null))
  }
}
