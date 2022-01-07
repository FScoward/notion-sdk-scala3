package com.github.fscoward.notion.property.create

import io.circe.*
import io.circe.generic.auto.*
import io.circe.parser.*
import io.circe.syntax.*

class PagePropertySpec extends munit.FunSuite {
  test("encode page property") {
    trait Property
    case class PageProperty(parent: PageParent, properties: Properties)
    case class PageParent(database_id: String)
    case class Properties(
        value: Map[String, Property]
    )
    case class TitleProperty(text: TextPropertyValue) extends Property {
      val id = "title"
    }
    case class RitchTextProperty(text: TextPropertyValue) extends Property {
      val id = "rich_text"
    }
    case class TextPropertyValue(content: String) {
      val `type`: String = "text"
    }

    implicit val encoder: Encoder[PageProperty] = new Encoder[PageProperty] {
      override def apply(a: PageProperty): Json = {
        val property: Seq[(String, Json)] = {
          a.properties.value.map { case (key, value) =>
            value match {
              case v: TitleProperty =>
                (key, Json.obj((v.id, Json.arr(v.asJson))))
              case v: RitchTextProperty =>
                (key, Json.obj((v.id, Json.arr(v.asJson))))
            }
          }.toSeq
        }
        Json.obj(
          ("parent", a.parent.asJson),
          (
            "properties",
            Json.obj(property: _*)
          )
        )
      }
    }

    val p = PageProperty(
      PageParent("7b1e0686274f4ae6b6373c2236b57080"),
      Properties(
        Map(
          "Name" -> TitleProperty(TextPropertyValue("this is content")),
          "Description" -> RitchTextProperty(
            TextPropertyValue("A dark green leafy vegetable")
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
         |    }
         |  }
         |}""".stripMargin

    assertEquals(actual.toString, expected)
  }
}
