package com.github.fscoward.notion

import io.circe.{Decoder, Encoder, HCursor, Json}

case class Page(
    `object`: String,
    id: String,
    created_time: String,
    last_edited_time: String,
    properties: Map[String, Property]
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

case class Properties(properties: Map[String, Property])

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
    } yield {
    
      val keys = c.downField("properties").keys.getOrElse(Nil)
      println("*****************************")
      val z = keys.map { key =>
            val p = c.downField("properties").get[Property](key).toOption
            (key, p)
      }.filter(_._2.isDefined).foldLeft(Map.empty[String, Property])((x,y) => x ++ Map(y._1 -> y._2.get))

      println(z)
      println("*****************************")
      val page = Page(obj, id, createdTime, lastEditedTime, z)
      println(page)
      page
    }
  }
}