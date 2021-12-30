package com.github.fscoward.notion

import com.github.fscoward.notion.property.Property
import io.circe.{Decoder, Encoder, HCursor, Json}

/** @see
  *   https://developers.notion.com/reference/page
  */
case class Page(
    `object`: String,
    id: String,
    created_time: String,
    last_edited_time: String,
    archived: Option[Boolean] = None,
    parent: Parent,
    properties: Map[String, Property] = Map.empty[String, Property]
)

case class NotionAnnotation(
    bold: Boolean,
    italic: Boolean,
    strikethrough: Boolean,
    code: Boolean,
    color: String
    // TODO: Enum
)

case class Parent(
    `type`: String = "database_id",
    database_id: String
)

import io.circe._, io.circe.generic.auto._, io.circe.parser._, io.circe.syntax._
import cats.syntax.functor._
import io.circe.{Decoder, Encoder}

implicit val pageDecoder: Decoder[Page] = new Decoder[Page] {
  def apply(c: HCursor): Decoder.Result[Page] = {
    for {
      obj <- c.downField("object").as[String]
      id <- c.downField("id").as[String]
      createdTime <- c.downField("created_time").as[String]
      lastEditedTime <- c.downField("last_edited_time").as[String]
      archived <- c.downField("archived").as[Option[Boolean]]
      parent <- c.downField("parent").as[Parent]
      property <- c.get[Map[String, Property]]("properties")
    } yield {
      Page(obj, id, createdTime, lastEditedTime, archived, parent, property)
    }
  }
}
