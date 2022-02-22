package com.github.fscoward.notion.databases.query

case class TextFilterCondition(property: String, text: Condition)
    extends FilterCondition
