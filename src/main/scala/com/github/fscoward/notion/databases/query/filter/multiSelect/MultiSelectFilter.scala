package com.github.fscoward.notion.databases.query.filter.multiSelect

import com.github.fscoward.notion.databases.query.filter.Filter
import com.github.fscoward.notion.databases.query.filter.select.SelectFilterCondition

case class MultiSelectFilter(
    property: String,
    multi_select: SelectFilterCondition
) extends Filter
