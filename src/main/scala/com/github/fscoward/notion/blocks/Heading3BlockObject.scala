package com.github.fscoward.notion.blocks

import java.time.LocalDateTime

case class Heading3BlockObject(
    id: String,
    created_time: LocalDateTime,
    last_edited_time: LocalDateTime,
    has_children: Boolean,
    `type`: BlockType,
    archived: Boolean,
    heading_3: HeadingBlock
) extends BlockObject
