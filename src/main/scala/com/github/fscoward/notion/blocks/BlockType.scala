package com.github.fscoward.notion.blocks

import io.circe.Decoder

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
  case heading_1 extends BlockType
  case heading_2 extends BlockType
  case heading_3 extends BlockType
  case table extends BlockType
  case divider extends BlockType
end BlockType

implicit val blockTypeDecoder: Decoder[BlockType] =
  Decoder.decodeString.emapTry(blockType => {
    Try(BlockType.valueOf(blockType))
  })
