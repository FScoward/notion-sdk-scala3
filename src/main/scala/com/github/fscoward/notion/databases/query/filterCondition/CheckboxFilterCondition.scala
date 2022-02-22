package com.github.fscoward.notion.databases.query.filterCondition

import com.github.fscoward.notion.databases.query.Condition

case class CheckboxFilterCondition(
    property: String,
    checkbox: Condition
) extends FilterCondition
