package com.github.fscoward.notion

import io.circe.{Decoder, Encoder, HCursor, Json}

case class Page(
    `object`: String,
    id: String,
    created_time: String,
    last_edited_time: String,
    properties: Json
)

trait Property
case class Select(
  name: String,
  color: String
)
case class Selects (
  value: Seq[Select]
) extends Property

case class Number(
  value: Int
) extends Property

import io.circe._, io.circe.generic.auto._, io.circe.parser._, io.circe.syntax._
import cats.syntax.functor._
import io.circe.{ Decoder, Encoder }

implicit val propertyDecoder: Decoder[Property] =
  List[Decoder[Property]](
    Decoder[Seq[Select]].widen.map(s => Selects(s)),
    Decoder[Int].widen.map(Number)
  ).reduceLeft(_ or _)
  

implicit val decorder: Decoder[Page] = new Decoder[Page] {
  def apply(c: HCursor): Decoder.Result[Page] = {
    for {
      obj <- c.downField("object").as[String]
      id <- c.downField("id").as[String]
      createdTime <- c.downField("created_time").as[String]
      lastEditedTime <- c.downField("last_edited_time").as[String]
      properties <- c.downField("properties").as[Json]
    } yield {
    

      println("*****************************")
      println(properties.hcursor.keys)
      println(c.downField("properties").keys.map(_.map(key => c.downField("properties").get[Property](key).getOrElse(None))))
      // println(c.downField("properties").get[Seq[Select]]("Store availability"))
      println(c.downField("properties").get[Property]("Store availability"))
      println(c.downField("properties").get[Property]("Price"))
      println("*****************************")
      Page(obj, id, createdTime, lastEditedTime, properties)
    }
  }
}