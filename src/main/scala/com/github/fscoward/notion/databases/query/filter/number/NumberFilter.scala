package com.github.fscoward.notion.databases.query.filter.number

import com.github.fscoward.notion.databases.query.filter.Filter

case class NumberFilter(property: String, number: NumberFilterCondition)
    extends Filter
