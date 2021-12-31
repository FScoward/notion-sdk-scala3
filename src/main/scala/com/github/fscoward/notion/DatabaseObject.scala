package com.github.fscoward.notion

import cats.data.Validated
import cats.syntax.functor.*
import com.github.fscoward.notion.checkbox.CheckboxProperty
import com.github.fscoward.notion.date.{DateProperty, DatePropertyValue}
import com.github.fscoward.notion.multi_select.MultiSelectProperty
import com.github.fscoward.notion.select.{SelectProperty, SelectPropertyValue}
import com.github.fscoward.notion.text.{
  TextProperty,
  TextPropertyValue,
  textPropertyDecoder
}
import com.github.fscoward.notion.url.URLProperty
import com.github.fscoward.notion.property.Property
import io.circe.*
import io.circe.Decoder.Result
import io.circe.generic.auto.*
import io.circe.parser.*
import io.circe.syntax.*

case class DatabaseObject(
    `object`: String = "database",
    id: String,
    created_time: String,
    last_edited_time: String,
    title: Seq[TitlePropertyValue],
    properties: Map[String, Property] = Map.empty[String, Property]
)

implicit val databaseObjectDecoder: Decoder[DatabaseObject] =
  new Decoder[DatabaseObject] {
    override def apply(c: HCursor): Result[DatabaseObject] = {
      for {
        obj <- c.downField("object").as[String]
        id <- c.downField("id").as[String]
        createdTime <- c.downField("created_time").as[String]
        lastEditedTime <- c.downField("last_edited_time").as[String]
        title <- c.downField("title").as[Seq[TitlePropertyValue]]
        property <- c.get[Map[String, Property]]("properties")
      } yield {
        DatabaseObject(
          `object` = obj,
          id = id,
          created_time = createdTime,
          last_edited_time = lastEditedTime,
          title = title,
          properties = property
        )
      }
    }
  }
