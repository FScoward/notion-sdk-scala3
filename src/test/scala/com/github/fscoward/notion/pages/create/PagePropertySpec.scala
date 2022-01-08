package com.github.fscoward.notion.pages.create

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
          "Price" -> NumberProperty(2.5)
        )
      ),
      Children(Seq(Heading2Object(TextObject("Lacinato kale"))))
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
         |    }
         |  ]
         |}""".stripMargin

    assertEquals(actual.toString, expected)
  }
}
