package com.github.fscoward.notion.pages.multi_select

import com.github.fscoward.notion.pages.property.Property
import com.github.fscoward.notion.pages.select.SelectPropertyValue

case class MultiSelectProperty(
    id: String,
    `type`: String = "multi_select",
    multi_select: Seq[SelectPropertyValue]
) extends Property
