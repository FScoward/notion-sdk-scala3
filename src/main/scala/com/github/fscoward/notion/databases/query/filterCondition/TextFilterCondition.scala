package com.github.fscoward.notion.databases.query.filterCondition

import com.github.fscoward.notion.databases.query.{Condition, FilterCondition}

case class TextFilterCondition(property: String, text: Condition)
    extends FilterCondition
