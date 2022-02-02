package com.github.fscoward.notion.blocks

import io.circe.*
import io.circe.generic.semiauto.*

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

case class Block(
    `object`: String = "block",
    id: String,
    created_time: LocalDateTime,
    last_edited_time: LocalDateTime,
    has_children: Boolean
)

val dateTimeFormatter =
  DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
implicit val datetimeDecoder: Decoder[LocalDateTime] =
  Decoder.decodeLocalDateTimeWithFormatter(dateTimeFormatter)
implicit val blockDecoder: Decoder[Block] = deriveDecoder[Block]
