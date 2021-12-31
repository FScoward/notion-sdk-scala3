package com.github.fscoward.notion.pages.text

import com.github.fscoward.notion.pages.property.Property
import io.circe.Decoder.Result
import io.circe.generic.auto.*
import io.circe.{Decoder, HCursor}

case class TextProperty(
    id: String,
    `type`: String = "text",
    text: Seq[TextPropertyValue]
) extends Property

// NOTE: 定義が深くなるせいか型が消えるのでこの書き方で避ける
val textPropertyDecoder: Decoder[TextProperty] =
  new Decoder[TextProperty] {
    def apply(c: HCursor): Result[TextProperty] = {
      for {
        id <- c.downField("id").as[String]
        `type` <- c.downField("type").as[String]
        text <- c.downField("text").as[Seq[TextPropertyValue]]
      } yield {
        TextProperty(id, `type`, text)
      }
    }
  }
