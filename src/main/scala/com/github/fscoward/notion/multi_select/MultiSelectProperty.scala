package com.github.fscoward.notion.multi_select

import com.github.fscoward.notion.property.Property
import com.github.fscoward.notion.select.SelectPropertyValue

case class MultiSelectProperty(
    id: String,
    `type`: String = "multi_select",
    multi_select: Seq[SelectPropertyValue]
) extends Property
