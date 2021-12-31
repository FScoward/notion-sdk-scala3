package com.github.fscoward.notion

import io.circe.*
import io.circe.generic.auto.*
import io.circe.parser.*
import io.circe.syntax.*
import cats.syntax.functor.*
import com.github.fscoward.notion.pages.property.*
import io.circe.{Decoder, Encoder}

case class ListDatabase(results: Seq[Database])

case class Database(
    `object`: String,
    id: String,
    title: String,
    properties: Map[String, Property]
)

implicit val databaseDecoder: Decoder[Database] = new Decoder[Database] {
  def apply(c: HCursor): Decoder.Result[Database] = {
    for {
      obj <- c.downField("object").as[String]
      id <- c.downField("id").as[String]
      title <- c.downField("title").as[String]
      property <- c.get[Map[String, Property]]("properties")
    } yield {
      Database(obj, id, title, property)
    }
  }
}
