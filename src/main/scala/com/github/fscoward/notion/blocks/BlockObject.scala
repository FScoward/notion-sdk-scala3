package com.github.fscoward.notion.blocks

import com.github.fscoward.notion.pages.annotation.NotionAnnotation
import com.github.fscoward.notion.pages.property.Text
import com.github.fscoward.notion.pages.url.URLProperty
import io.circe.*
import io.circe.Decoder.Result
import io.circe.generic.semiauto.*

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import scala.util.Try

case class BlockObject(
    `object`: String = "block",
    id: String,
    created_time: LocalDateTime,
    last_edited_time: LocalDateTime,
    has_children: Boolean,
    `type`: BlockType,
    archived: Boolean,
    to_do: ToDoBlock
)

case class TodoBlockContent(
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
implicit val todoBlockContentDecoder: Decoder[TodoBlockContent] =
  deriveDecoder[TodoBlockContent]

val dateTimeFormatter =
  DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
implicit val datetimeDecoder: Decoder[LocalDateTime] =
  Decoder.decodeLocalDateTimeWithFormatter(dateTimeFormatter)
implicit val blockDecoder: Decoder[BlockObject] = deriveDecoder[BlockObject]
