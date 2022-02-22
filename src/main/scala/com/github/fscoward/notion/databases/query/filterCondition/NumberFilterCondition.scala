package com.github.fscoward.notion.databases.query.filterCondition

import com.github.fscoward.notion.databases.query.Condition

case class NumberFilterCondition(property: String, number: Condition)
    extends FilterCondition
