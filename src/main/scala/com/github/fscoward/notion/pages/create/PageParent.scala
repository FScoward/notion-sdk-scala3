package com.github.fscoward.notion.pages.create

import io.circe.*
import io.circe.generic.auto.*
import io.circe.parser.*
import io.circe.syntax.*

case class PageParent(database_id: String)

implicit val encoder: Encoder[PageProperty] = new Encoder[PageProperty] {
  override def apply(a: PageProperty): Json = {
    val property: Seq[(String, Json)] = {
      a.properties.value.map { case (key, value) =>
        value match {
          case v: TitleProperty =>
            (key, Json.obj((v.id, Json.arr(v.asJson))))
          case v: RitchTextProperty =>
            (key, Json.obj((v.id, Json.arr(v.asJson))))
          case v: SelectProperty =>
            (key, v.asJson)
        }
      }.toSeq
    }
    Json.obj(
      ("parent", a.parent.asJson),
      (
        "properties",
        Json.obj(property: _*)
      )
    )
  }
}
