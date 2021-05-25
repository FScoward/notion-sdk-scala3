package com.github.fscoward.notion

import io.circe.{Decoder, Encoder, HCursor, Json, ACursor}
import io.circe.DecodingFailure
import cats.data.Validated
import io.circe._, io.circe.generic.auto._, io.circe.parser._, io.circe.syntax._
import cats.syntax.functor._

trait Property

implicit val propertyDecoder: Decoder[Property] =
  List[Decoder[Property]](
    Decoder[TextObject].widen,
    Decoder[Seq[Select]].widen.map(s => Selects(s)),
    Decoder[Int].widen.map(Number.apply),
    Decoder[MultiSelect].widen,
    Decoder[Boolean].widen.map(Bool.apply),
    Decoder[Title].widen
  ).reduceLeft(_ or _)


implicit def propertiesDecoder: Decoder[Map[String, Property]] = new Decoder[Map[String, Property]]{ 

  def decodeProperties(c: HCursor): Decoder.Result[Map[String, Property]] = {
    val keys = c.keys.getOrElse(Nil)
    val res = keys.map { key =>
      val property = c.get[Property](key).toTry
      (key, property)
    }
    // TODO 削除
    .filter(_._2.isSuccess)
    // if(res.exists(_._2.isFailure)) return Left(DecodingFailure(s"Attempt to decode value on failed cursor: ${res.filter(_._2.isFailure).map(_._1)}", c.history))

    Right(res.foldLeft(Map.empty[String, Property])((x,y) => x ++ Map(y._1 -> y._2.get)))
  }

  def apply(c: HCursor): Decoder.Result[Map[String, Property]] = tryDecode(c)

  override def tryDecode(c: ACursor): Decoder.Result[Map[String, Property]] = c match {
    case hc: HCursor => 
      decodeProperties(hc)
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

case class Title(
  `type`: String,
  id: Option[String],
  title: Option[String]
) extends Property

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

