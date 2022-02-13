package com.github.fscoward.notion.blocks

import com.github.fscoward.notion.pages.annotation.NotionAnnotation
import com.github.fscoward.notion.pages.property.Text
import com.github.fscoward.notion.pages.url.URLProperty
import io.circe.generic.semiauto.deriveDecoder
import io.circe.{Decoder, HCursor}

case class TextBlockContent(
    `type`: String = "text",
    text: Text,
    annotations: NotionAnnotation,
    plain_text: String,
    href: Option[String]
)

implicit val urlPropertyDecoder: Decoder[URLProperty] = deriveDecoder

implicit val textDecoder: Decoder[Text] = (c: HCursor) =>
  for {
    content <- c.downField("content").as[String]
    link <- c.downField("link").as[Option[URLProperty]]
  } yield Text(content = content, link = link)

implicit val todoBlockContentDecoder: Decoder[TextBlockContent] =
  deriveDecoder[TextBlockContent]
