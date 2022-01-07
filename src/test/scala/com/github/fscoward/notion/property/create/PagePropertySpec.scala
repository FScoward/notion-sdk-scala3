package com.github.fscoward.notion.property.create

import io.circe.*
import io.circe.generic.auto.*
import io.circe.parser.*
import io.circe.syntax.*

class PagePropertySpec extends munit.FunSuite {
  test("encode page property") {
    case class PageProperty(parent: PageParent, properties: Property)
    case class PageParent(database_id: String)
    case class PropertyBody(property: Seq[Map[String, Text]])
    case class Property(
        keyName: String,
        propertyId: String,
        property: PropertyBody
    )
    case class Text(content: String)

    implicit val encoder: Encoder[PageProperty] = new Encoder[PageProperty] {
      override def apply(a: PageProperty): Json = {
        Json.obj(
          ("parent", a.parent.asJson),
          (
            "properties",
            Json.obj(
              (
                a.properties.keyName,
                Json.obj(
                  (
                    a.properties.propertyId,
                    a.properties.property.property.asJson
                  )
                )
              )
            )
          )
        )
      }
    }

    val p = PageProperty(
      PageParent("7b1e0686274f4ae6b6373c2236b57080"),
      Property(
        keyName = "Name",
        propertyId = "title",
        property = PropertyBody(Seq(Map("text" -> Text("this is content"))))
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
        |    }
        |  }
        |}""".stripMargin

    assertEquals(actual.toString, expected)
  }
}
