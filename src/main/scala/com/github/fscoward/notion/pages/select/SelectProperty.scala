package com.github.fscoward.notion.pages.select

import com.github.fscoward.notion.pages.property.{Property, PropertyType}

case class SelectProperty(
    id: String,
    `type`: PropertyType = PropertyType.select,
    select: SelectPropertyValue
) extends Property
