package com.github.fscoward.notion

import com.github.fscoward.notion.databases.create.{
  CheckboxProperty,
  Database,
  DateProperty,
  Format,
  NumberProperty,
  Parent,
  RichTextProperty,
  SelectProperty,
  SelectPropertyValue,
  Title,
  TitleProperty,
  TitleValue
}
import sttp.client3.*
import io.circe.*
import io.circe.generic.auto.*
import io.circe.parser.*
import io.circe.syntax.*

@main def hello(): Unit =
  val httpClient = new NotionApiClient()
//  println(httpClient.listDatabases)
//  println(httpClient.createPage(null, null))
//  val db = Database(
//    parent = Parent("b24dcaf87a2140d0a1d85b393cd2019b"),
//    title = Seq(Title(text = TitleValue("Grocery List", None))),
//    properties = Map(
//      "Name" -> TitleProperty(),
//      "Description" -> RichTextProperty(),
//      "In stock" -> CheckboxProperty(),
//      "Food group" -> SelectProperty(options =
//        Seq(
//          SelectPropertyValue("🥦Vegetable", "green"),
//          SelectPropertyValue("🍎Fruit", "red"),
//          SelectPropertyValue("💪Protein", "yellow")
//        )
//      ),
//      "Price" -> NumberProperty(Format("dollar")),
//      "Last ordered" -> DateProperty()
//    )
//  )
//  println(httpClient.createDatabase(db))
  val res = httpClient.updatePage(
    pageId = "96297f595fef48fd8498b631b76ab7b6",
    com.github.fscoward.notion.pages.create
      .Properties(
        Map(
          "Name" -> com.github.fscoward.notion.pages.create.TitleProperty(
            com.github.fscoward.notion.pages.create
              .TextPropertyValue(content = "Scala")
          )
        )
      )
  )
  println(res)
