package com.github.fscoward.notion.pages.property

import cats.data.Validated
import cats.syntax.functor.*
import com.github.fscoward.notion.pages.annotation.NotionAnnotation
import com.github.fscoward.notion.pages.checkbox.CheckboxProperty
import com.github.fscoward.notion.pages.createdTime.CreatedTimeProperty
import com.github.fscoward.notion.pages.date.DateProperty
import com.github.fscoward.notion.pages.multi_select.MultiSelectProperty
import com.github.fscoward.notion.pages.relation.RelationProperty
import com.github.fscoward.notion.pages.select.SelectProperty
import com.github.fscoward.notion.pages.text.*
import com.github.fscoward.notion.pages.url.URLProperty
import io.circe.*
import io.circe.Decoder.Result
import io.circe.generic.auto.*
import io.circe.parser.*
import io.circe.syntax.*

trait Property

implicit val propertyDecoder: Decoder[Property] =
  List[Decoder[Property]](
    Decoder[RichTextProperty].widen,
    Decoder[SelectProperty].widen,
    textPropertyDecoder.widen,
    Decoder[DateProperty].widen,
    Decoder[URLProperty].widen,
    Decoder[CheckboxProperty].widen,
    Decoder[MultiSelectProperty].widen,
    Decoder[TitleProperty].widen,
    Decoder[RelationProperty].widen,
    Decoder[CreatedTimeProperty].widen
  ).reduceLeft(_ or _)

implicit def propertiesDecoder: Decoder[Map[String, Property]] =
  new Decoder[Map[String, Property]] {

    def decodeProperties(c: HCursor): Decoder.Result[Map[String, Property]] = {
      val keys = c.keys.getOrElse(Nil)
      val res = keys
        .map { key =>
          val property = c.get[Property](key).toTry
          (key, property)
        }
        // TODO 削除
        .filter(_._2.isSuccess)
      // if(res.exists(_._2.isFailure)) return Left(DecodingFailure(s"Attempt to decode value on failed cursor: ${res.filter(_._2.isFailure).map(_._1)}", c.history))

      Right(
        res.foldLeft(Map.empty[String, Property])((x, y) =>
          x ++ Map(y._1 -> y._2.get)
        )
      )
    }

    def apply(c: HCursor): Decoder.Result[Map[String, Property]] = tryDecode(c)

    override def tryDecode(c: ACursor): Decoder.Result[Map[String, Property]] =
      c match {
        case hc: HCursor =>
          decodeProperties(hc)
        case _ =>
          Left(
            DecodingFailure(
              "Attempt to decode value on failed cursor",
              c.history
            )
          )
      }

    override def tryDecodeAccumulating(
        c: ACursor
    ): Decoder.AccumulatingResult[Map[String, Property]] = c match {
      case hc: HCursor => decodeAccumulating(hc)
      case _ =>
        Validated.invalidNel(
          DecodingFailure("Attempt to decode value on failed cursor", c.history)
        )
    }
  }

case class Select(
    name: String,
    color: Color
) extends Property
case class Selects(
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
    color: Color
) extends Property

enum Color:
  case default, gray, brown, red, yellow, green, purple, pink
end Color

implicit val decodeColor: Decoder[Color] = Decoder.decodeString.emapTry { str =>
  scala.util.Try(Color.valueOf(str))
}

trait RichText extends Property {
  val plain_text: String
  val href: Option[String]
  val annotations: NotionAnnotation
  val `type`: PropertyType = PropertyType.rich_text
}

case class TitleProperty(
    id: String,
    `type`: PropertyType = PropertyType.title,
    title: Seq[TitlePropertyValue]
) extends Property

case class TitlePropertyValue(
    `type`: String = "text",
    text: Text,
    annotations: NotionAnnotation,
    plain_text: String,
    href: Option[String],
    inline_object: Option[String]
) extends Property

case class RichTextProperty(
    plain_text: String,
    href: Option[String],
    annotations: NotionAnnotation,
    text: Text
//    link: URLProperty
) extends RichText

case class TextObjects(
    value: Seq[RichTextProperty]
) extends Property

case class Text(
    content: String,
    link: Option[URLProperty]
)

case class TextObj(
    content: String,
    link: Option[LinkObj]
)
case class LinkObj(
    `type`: String = "url",
    url: String
)

case class File(name: String)
