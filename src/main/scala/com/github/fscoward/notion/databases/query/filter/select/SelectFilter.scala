package com.github.fscoward.notion.databases.query.filter.select

import com.github.fscoward.notion.databases.query.filter.Filter

case class SelectFilter(property: String, select: SelectFilterCondition)
    extends Filter
