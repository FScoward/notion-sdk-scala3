package com.github.fscoward.notion.date

import com.github.fscoward.notion.property.Property

case class DateProperty(
    id: String,
    `type`: String = "date",
    date: DatePropertyValue
) extends Property
