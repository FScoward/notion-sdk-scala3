package com.github.fscoward.notion

import io.circe.{Decoder, Encoder, HCursor, Json, ACursor}
import io.circe.DecodingFailure
import cats.data.Validated

trait Property



implicit def propertiesDecoder: Decoder[Map[String, Property]] = new Decoder[Map[String, Property]]{ 

  def decodeProperties(properties: HCursor): Map[String, Property] = {
    val keys = properties.keys.getOrElse(Nil)
    keys.map { key =>
      val property = properties.get[Property](key).toOption
      (key, property)
    }.filter(_._2.isDefined)
    .foldLeft(Map.empty[String, Property])((x,y) => x ++ Map(y._1 -> y._2.get))
  }

  def apply(c: HCursor): Decoder.Result[Map[String, Property]] = tryDecode(c)

  override def tryDecode(c: ACursor): Decoder.Result[Map[String, Property]] = c match {
    case hc: HCursor => 
      Right(decodeProperties(hc))
      // decoder(hc) match {
      //   case Right(a) => Right(decodeProperties(hc))
      //   case Left(df) => Left(df)
      // }
    case _ =>
      Left(
        DecodingFailure("Attempt to decode value on failed cursor", c.history)
      )
  }

  override def tryDecodeAccumulating(c: ACursor): Decoder.AccumulatingResult[Map[String, Property]] = c match {
    case hc: HCursor => decodeAccumulating(hc)
    case _ =>
      Validated.invalidNel(
        DecodingFailure("Attempt to decode value on failed cursor", c.history)
      )
  }
}

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

case class Bool(
  value: Boolean
) extends Property

case class MultiSelect(
  id: String,
  name: String,
  color: String
) extends Property

// trait RichText extends Property {
//   val plain_text: String
//   val href: Option[String]
//   val annotations: NotionAnnotation
//   val `type`: String // TODO: Enum
// }

trait RichText extends Property {
  val plain_text: String
  val href: Option[String]
  val annotations: NotionAnnotation
  val `type`: String // TODO: Enum
}

case class TextObject(
  plain_text: String,
  href: Option[String],
  annotations: NotionAnnotation,
  `type`: String, // TODO: Enum
  content: String,
  link: LinkObject
) extends RichText

