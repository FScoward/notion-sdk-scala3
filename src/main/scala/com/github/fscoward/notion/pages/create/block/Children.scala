package com.github.fscoward.notion.pages.create.block

import io.circe.*
import io.circe.generic.auto.*
import io.circe.parser.*
import io.circe.syntax.*

case class Children(value: Seq[BlockObject])

implicit val childrenEncoder: Encoder[Children] = new Encoder[Children] {
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
