package com.github.fscoward.notion.pages.property_item

case class RetrieveAPagePropertyItemResponse(
    `object`: String = "list",
    results: Seq[PagePropertyItem],
    next_cursor: Option[String],
    has_more: Boolean
)
