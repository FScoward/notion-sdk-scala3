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
    Decoder[Boolean].widen.map(Bool.apply)
  ).reduceLeft(_ or _)

implicit val decoder: Decoder[Page] = new Decoder[Page] {
  def apply(c: HCursor): Decoder.Result[Page] = {
    for {
      obj <- c.downField("object").as[String]
      id <- c.downField("id").as[String]
      createdTime <- c.downField("created_time").as[String]
      lastEditedTime <- c.downField("last_edited_time").as[String]
      propertyKeys <- c.get[Json]("properties").map(_.hcursor.keys.getOrElse(Nil))
    } yield {
      val properties: Map[String, Property] = propertyKeys.map { key =>
        val property = c.downField("properties").get[Property](key).toOption
        (key, property)
      }.filter(_._2.isDefined).foldLeft(Map.empty[String, Property])((x,y) => x ++ Map(y._1 -> y._2.get))
      Page(obj, id, createdTime, lastEditedTime, properties)
    }
  }
}