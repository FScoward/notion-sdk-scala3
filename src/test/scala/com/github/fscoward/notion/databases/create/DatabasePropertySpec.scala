package com.github.fscoward.notion.databases.create

import io.circe._, io.circe.parser._
import io.circe.Encoder, io.circe.syntax._
import io.circe.generic.auto._

class DatabasePropertySpec extends munit.FunSuite {
  test("create database property") {
    val actual = Database(
      parent = Parent("b24dcaf87a2140d0a1d85b393cd2019b"),
      title = Seq(Title(text = TitleValue("Grocery List", None))),
      properties =
        Map("Name" -> TitleProperty(), "Description" -> RichTextProperty())
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
   "properties": {
    "Name": {
        "title": {}
    },
    "Description": {
      "rich_text": {}
    }
  }
}
      """
    assertEquals(actual.asJson, parse(expected).getOrElse(Json.Null))
  }
}
