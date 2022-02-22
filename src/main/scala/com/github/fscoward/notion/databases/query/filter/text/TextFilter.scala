package com.github.fscoward.notion.databases.query.filter.text

import com.github.fscoward.notion.databases.query.filter.Filter

case class TextFilter(property: String, text: TextFilterCondition)
    extends Filter
