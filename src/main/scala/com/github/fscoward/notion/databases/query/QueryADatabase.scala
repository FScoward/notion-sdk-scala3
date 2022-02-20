package com.github.fscoward.notion.databases.query

import com.github.fscoward.notion.Page
import com.github.fscoward.notion.pageDecoder

case class QueryADatabase(
    `object`: String = "list",
    results: Seq[Page],
    next_cursor: Option[String],
    has_more: Boolean
)
