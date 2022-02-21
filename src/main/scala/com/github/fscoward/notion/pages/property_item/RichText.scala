package com.github.fscoward.notion.pages.property_item

import com.github.fscoward.notion.pages.property.PropertyType

case class RichText(
    `object`: String,
    `type`: PropertyType = PropertyType.rich_text,
    rich_text: com.github.fscoward.notion.pages.property.Property
) extends PagePropertyItem
