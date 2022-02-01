package com.github.fscoward.notion.blocks

import io.circe._, io.circe.generic.semiauto._

case class Block(
    `object`: String = "block",
    id: String
)

implicit val blockDecoder: Decoder[Block] = deriveDecoder[Block]
