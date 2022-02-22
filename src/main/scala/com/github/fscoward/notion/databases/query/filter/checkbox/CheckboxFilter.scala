package com.github.fscoward.notion.databases.query.filter.checkbox

import com.github.fscoward.notion.databases.query.filter.Filter

case class CheckboxFilter(
    property: String,
    checkbox: CheckboxFilterCondition
) extends Filter
