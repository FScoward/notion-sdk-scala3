package com.github.fscoward.notion.pages.date

import com.github.fscoward.notion.pages.property.Property

case class DateProperty(
    id: String,
    `type`: String = "date",
    date: DatePropertyValue
) extends Property
