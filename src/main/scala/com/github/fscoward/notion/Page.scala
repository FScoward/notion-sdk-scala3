package com.github.fscoward.notion

import io.circe.{Decoder, Encoder, HCursor, Json}

type Properties = Map[String, Any]
case class Page(
    `object`: String,
    id: String,
    created_time: String,
    last_edited_time: String,
    properties: Json
)

implicit val decorder: Decoder[Page] = new Decoder[Page] {
  def apply(c: HCursor): Decoder.Result[Page] = {
    for {
      obj <- c.downField("object").as[String]
      id <- c.downField("id").as[String]
      createdTime <- c.downField("created_time").as[String]
      lastEditedTime <- c.downField("last_edited_time").as[String]
      properties <- c.downField("properties").as[Json]
    } yield {
      Page(obj, id, createdTime, lastEditedTime, properties)
    }
  }
}