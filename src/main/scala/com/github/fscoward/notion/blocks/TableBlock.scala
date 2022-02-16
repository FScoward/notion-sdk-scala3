package com.github.fscoward.notion.blocks

case class TableBlock(
    table_width: Int,
    has_column_header: Boolean,
    has_row_header: Boolean
)
