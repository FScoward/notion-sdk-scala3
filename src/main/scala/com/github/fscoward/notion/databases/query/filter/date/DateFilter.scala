package com.github.fscoward.notion.databases.query.filter.date

import com.github.fscoward.notion.databases.query.filter.Filter

case class DateFilter(property: String, date: DateFilterCondition)
    extends Filter
