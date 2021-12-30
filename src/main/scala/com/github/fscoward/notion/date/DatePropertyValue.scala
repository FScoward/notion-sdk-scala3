package com.github.fscoward.notion.date

import com.github.fscoward.notion.property.Property
import com.github.fscoward.notion.select.SelectPropertyValue

// https://developers.notion.com/reference/page#date-property-values
case class DatePropertyValue(
    start: String,
    end: Option[String],
    time_zone: Option[String]
)
