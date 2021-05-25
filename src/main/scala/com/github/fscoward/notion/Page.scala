package com.github.fscoward.notion

import io.circe.{Decoder, Encoder, HCursor, Json}

case class Page(
    `object`: String,
    id: String,
    created_time: String,
    last_edited_time: String,
    properties: Map[String, Property]
)

case class LinkObject(
  `type`: String = "url",
  url: String
)

case class NotionAnnotation(
  bold: Boolean,
  italic: Boolean,
  strikethrough: Boolean,
  code: Boolean,
  color: String // TODO: Enum
)

case class Properties(properties: Map[String, Property])

import io.circe._, io.circe.generic.auto._, io.circe.parser._, io.circe.syntax._
import cats.syntax.functor._
import io.circe.{ Decoder, Encoder }

implicit val propertyDecoder: Decoder[Property] =
  List[Decoder[Property]](
    Decoder[TextObject].widen,
    Decoder[Seq[Select]].widen.map(s => Selects(s)),
    Decoder[Int].widen.map(Number.apply),
    Decoder[MultiSelect].widen,
    Decoder[Boolean].widen.map(Bool.apply),
    Decoder[Title].widen
  ).reduceLeft(_ or _)

implicit val pageDecoder: Decoder[Page] = new Decoder[Page] {
  def apply(c: HCursor): Decoder.Result[Page] = {
    for {
      obj <- c.downField("object").as[String]
      id <- c.downField("id").as[String]
      createdTime <- c.downField("created_time").as[String]
      lastEditedTime <- c.downField("last_edited_time").as[String]
      property <- c.get[Map[String, Property]]("properties")
    } yield {
      Page(obj, id, createdTime, lastEditedTime, property)
    }
  }
}