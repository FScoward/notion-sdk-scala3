package com.github.fscoward.notion.pages.annotation

case class NotionAnnotation(
    bold: Boolean,
    italic: Boolean,
    strikethrough: Boolean,
    code: Boolean,
    color: String
    // TODO: Enum
)
