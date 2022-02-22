package com.github.fscoward.notion.databases.query

case class CheckboxFilterCondition(
    property: String,
    checkbox: Condition
) extends FilterCondition
