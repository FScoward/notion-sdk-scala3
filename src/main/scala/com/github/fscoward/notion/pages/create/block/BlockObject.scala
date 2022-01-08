package com.github.fscoward.notion.pages.create.block

import io.circe.*
import io.circe.generic.auto.*
import io.circe.parser.*
import io.circe.syntax.*

trait BlockObject {
  final val `object`: String = "block"
  val `type`: String
}

case class TextObject(content: String, link: Option[String] = None) {
  val `type`: String = "text"
}

implicit val textObjectEncoder: Encoder[TextObject] = new Encoder[TextObject] {
  override def apply(a: TextObject): Json = {
    Json.obj(
      ("type", Json.fromString(a.`type`)),
      (
        "text",
        Json
          .obj(
            ("content", Json.fromString(a.content)),
            ("link", a.link.asJson)
          )
          .dropNullValues
      )
    )
  }
}
