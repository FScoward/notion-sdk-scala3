package com.github.fscoward.notion.databases.create

import io.circe._, io.circe.parser._
import io.circe.Encoder, io.circe.syntax._
import io.circe.generic.auto._

class DatabasePropertySpec extends munit.FunSuite {
  test("create database property") {
    val actual = Database(
      parent = Parent("b24dcaf87a2140d0a1d85b393cd2019b"),
      title = Seq(Title(text = TitleValue("Grocery List", None))),
      properties = Map(
        "Name" -> TitleProperty(),
        "Description" -> RichTextProperty(),
        "In stock" -> CheckboxProperty(),
        "Food group" -> SelectProperty(options =
          Seq(
            SelectPropertyValue("🥦Vegetable", "green"),
            SelectPropertyValue("🍎Fruit", "red"),
            SelectPropertyValue("💪Protein", "yellow")
          )
        ),
        "Price" -> NumberProperty(Format("dollar"))
      )
    )
    val expected = """
{
   "parent":{
      "type":"page_id",
      "page_id":"b24dcaf87a2140d0a1d85b393cd2019b"
   },
   "title":[
      {
         "type":"text",
         "text":{
            "content":"Grocery List",
            "link":null
         }
      }
   ],
   "properties":{
      "Name":{
         "title":{
            
         }
      },
      "Description":{
         "rich_text":{
            
         }
      },
      "In stock":{
         "checkbox":{
            
         }
      },
      "Food group":{
         "select":{
            "options":[
               {
                  "name":"🥦Vegetable",
                  "color":"green"
               },
               {
                  "name":"🍎Fruit",
                  "color":"red"
               },
               {
                  "name":"💪Protein",
                  "color":"yellow"
               }
            ]
         }
      },
      "Price": {
        "number": {
          "format": "dollar"
        }
      }
   }
}
      """
    assertEquals(actual.asJson, parse(expected).getOrElse(Json.Null))
  }
}
