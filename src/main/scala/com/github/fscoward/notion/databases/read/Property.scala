package com.github.fscoward.notion.databases.read

import cats.data.Validated
import io.circe.{ACursor, Decoder, DecodingFailure, HCursor}
import io.circe.*
import io.circe.Decoder.Result
import io.circe.generic.auto.*
import io.circe.parser.*
import io.circe.syntax.*
import cats.syntax.functor.*
import com.github.fscoward.notion.databases.read.*

trait Property

implicit val propertyDecoder: Decoder[Property] =
  List[Decoder[Property]](
    selectOptionsPropertyDecoder.widen,
    textPropertyDecoder.widen,
    datePropertyDecoder.widen,
    checkboxPropertyDecoder.widen,
    multiSelectOptionsPropertyDecoder.widen,
    Decoder[URLProperty].widen,
    Decoder[TitleProperty].widen
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
