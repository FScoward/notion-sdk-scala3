package com.github.fscoward.notion.pages.select

import com.github.fscoward.notion.pages.property.Property

case class SelectProperty(
    id: String,
    `type`: String = "select",
    select: SelectPropertyValue
) extends Property
