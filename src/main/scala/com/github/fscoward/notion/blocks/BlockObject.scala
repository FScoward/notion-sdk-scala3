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

trait BlockObject {
  val `object`: String = "block"
  val id: String
  val created_time: LocalDateTime
  val last_edited_time: LocalDateTime
  val has_children: Boolean
  val `type`: BlockType
  val archived: Boolean
}

case class TodoBlockObject(
    id: String,
    created_time: LocalDateTime,
    last_edited_time: LocalDateTime,
    has_children: Boolean,
    `type`: BlockType,
    archived: Boolean,
    to_do: ToDoBlock
) extends BlockObject

val dateTimeFormatter =
  DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
implicit val datetimeDecoder: Decoder[LocalDateTime] =
  Decoder.decodeLocalDateTimeWithFormatter(dateTimeFormatter)
implicit val blockDecoder: Decoder[TodoBlockObject] =
  deriveDecoder[TodoBlockObject]
