package com.github.fscoward.notion.databases.create

import io.circe.Json.JObject
import io.circe.{Decoder, Encoder, HCursor, Json}

import io.circe.*
import io.circe.generic.auto.*
import io.circe.syntax.*

case class Database(
    parent: Parent,
    title: Seq[Title],
    properties: Map[String, Property]
)

implicit val propertiesEncoder: Encoder[Map[String, Property]] =
  new Encoder[Map[String, Property]] {
    override def apply(a: Map[String, Property]): Json = {
      val seq = a.map {
        case (key, value: TitleProperty) =>
          (key, Json.obj((value.`type`, value.value.asJson)))
        case (key, value: RichTextProperty) =>
          (key, value.asJson)
        case (key, value: CheckboxProperty) =>
          (key, value.asJson)
      }.toSeq
      Json.fromFields(seq)
    }
  }
