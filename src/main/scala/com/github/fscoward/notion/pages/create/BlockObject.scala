package com.github.fscoward.notion.pages.create

import io.circe.*
import io.circe.generic.auto.*
import io.circe.parser.*
import io.circe.syntax.*

trait BlockObject {
  final val `object`: String = "block"
  val `type`: String
}

trait HeadingObject extends BlockObject {
  val text: Seq[TextObject]
}
case class Heading2Object(text: Seq[TextObject]) extends HeadingObject {
  val `type` = "heading_2"
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
