package com.github.fscoward.notion.blocks

import java.time.LocalDateTime

case class QuoteBlockObject(
    id: String,
    created_time: LocalDateTime,
    last_edited_time: LocalDateTime,
    has_children: Boolean,
    `type`: BlockType,
    archived: Boolean,
    quote: QuoteBlock
) extends BlockObject
