package com.github.fscoward.notion.blocks

import java.time.LocalDateTime

case class Heading1BlockObject(
    id: String,
    created_time: LocalDateTime,
    last_edited_time: LocalDateTime,
    has_children: Boolean,
    `type`: BlockType,
    archived: Boolean,
    heading_1: Heading1Block
) extends BlockObject
