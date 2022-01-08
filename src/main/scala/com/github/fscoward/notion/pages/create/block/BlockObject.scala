package com.github.fscoward.notion.pages.create.block

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
case class Children(value: Seq[BlockObject])

implicit val chidrenEncoder: Encoder[Children] = new Encoder[Children] {
  override def apply(a: Children): Json = {
    Json.arr(a.value.map(blockObject => {
      blockObject match {
        case h2: Heading2Object =>
          Json.obj(
            ("object", Json.fromString(h2.`object`)),
            ("type", Json.fromString(h2.`type`)),
            (h2.`type`, h2.asJson)
          )
      }
    }): _*)
  }
}
