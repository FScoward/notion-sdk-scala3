package com.github.fscoward.notion.select

import com.github.fscoward.notion.property.Property

case class SelectProperty(
    id: String,
    `type`: String = "select",
    select: SelectPropertyValue
) extends Property

case class SelectOptionsProperty(
    id: String,
    `type`: String = "select",
    select: SelectOptions
) extends Property

case class SelectOptions(
    options: Seq[SelectPropertyValue]
)
