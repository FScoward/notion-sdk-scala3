package com.github.fscoward.notion.blocks

import io.circe.*
import io.circe.generic.semiauto.*

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import scala.util.Try

enum BlockType:
  case paragraph extends BlockType
  case bulleted_list_item extends BlockType
  case numbered_list_item extends BlockType
  case toggle extends BlockType
  case to_do extends BlockType
  case quote extends BlockType
  case callout extends BlockType
  case synced_block extends BlockType
  case template extends BlockType
  case column extends BlockType
  case child_page extends BlockType
  case child_database extends BlockType
  case header_1 extends BlockType
  case header_2 extends BlockType
  case header_3 extends BlockType
  case table extends BlockType
end BlockType

case class Block(
    `object`: String = "block",
    id: String,
    created_time: LocalDateTime,
    last_edited_time: LocalDateTime,
    has_children: Boolean,
    `type`: BlockType
)

val dateTimeFormatter =
  DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
implicit val datetimeDecoder: Decoder[LocalDateTime] =
  Decoder.decodeLocalDateTimeWithFormatter(dateTimeFormatter)

implicit val blockTypeDecoder: Decoder[BlockType] =
  Decoder.decodeString.emapTry(blockType => {
    Try(BlockType.valueOf(blockType))
  })
implicit val blockDecoder: Decoder[Block] = deriveDecoder[Block]
