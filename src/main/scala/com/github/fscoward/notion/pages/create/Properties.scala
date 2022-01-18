package com.github.fscoward.notion.pages.create

import io.circe.*
import io.circe.generic.auto.*
import io.circe.parser.*
import io.circe.syntax.*

case class Properties(
    value: Map[String, Property]
)

implicit val pagePropertiesEncoder: Encoder[Properties] =
  new Encoder[Properties] {
    override def apply(properties: Properties): Json = {
      Json.obj(properties.value.map { case (key, value) =>
        value match {
          case v: TitleProperty =>
            (key, Json.obj((v.id, Json.arr(v.asJson))))
          case v: RitchTextProperty =>
            (key, Json.obj((v.id, Json.arr(v.asJson))))
          case v: SelectProperty =>
            (key, v.asJson)
          case v: NumberProperty =>
            (key, v.asJson)
          case v: EmailProperty =>
            (key, v.asJson)
          case v: MultiSelectProperty =>
            (key, v.asJson)
        }
      }.toSeq: _*)

    }
  }
