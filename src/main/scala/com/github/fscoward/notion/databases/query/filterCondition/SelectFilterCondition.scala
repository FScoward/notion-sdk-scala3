package com.github.fscoward.notion.databases.query.filterCondition

import com.github.fscoward.notion.databases.query.{Condition, FilterCondition}

case class SelectFilterCondition(property: String, select: Condition)
    extends FilterCondition
