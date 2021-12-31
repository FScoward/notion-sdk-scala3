package com.github.fscoward.notion.pages.date

import com.github.fscoward.notion.pages.property.Property
import com.github.fscoward.notion.pages.select.SelectPropertyValue

// https://developers.notion.com/reference/page#date-property-values
case class DatePropertyValue(
    start: String,
    end: Option[String],
    time_zone: Option[String]
)
