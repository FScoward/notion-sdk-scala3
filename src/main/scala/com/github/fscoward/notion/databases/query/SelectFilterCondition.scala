package com.github.fscoward.notion.databases.query

case class SelectFilterCondition(property: String, select: Condition)
    extends FilterCondition
