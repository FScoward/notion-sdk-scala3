package com.github.fscoward.notion.pages.date

import com.github.fscoward.notion.pages.property.{Property, PropertyType}

case class DateProperty(
    id: String,
    `type`: PropertyType = PropertyType.date,
    date: DatePropertyValue
) extends Property
