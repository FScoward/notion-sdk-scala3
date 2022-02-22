package com.github.fscoward.notion.databases.query.filterCondition

import com.github.fscoward.notion.databases.query.{Condition, FilterCondition}

case class CheckboxFilterCondition(
    property: String,
    checkbox: Condition
) extends FilterCondition
