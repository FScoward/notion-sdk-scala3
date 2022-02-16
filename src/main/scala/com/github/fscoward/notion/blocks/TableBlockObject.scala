package com.github.fscoward.notion.blocks

import java.time.LocalDateTime

case class TableBlockObject(
    id: String,
    created_time: LocalDateTime,
    last_edited_time: LocalDateTime,
    has_children: Boolean,
    `type`: BlockType,
    archived: Boolean,
    table: TableBlock
) extends BlockObject

case class TableBlock(
    table_width: Int,
    has_column_header: Boolean,
    has_row_header: Boolean
)
