package com.github.fscoward.notion.pages.property_item

case class RichText(
    `object`: String,
    `type`: String,
    rich_text: com.github.fscoward.notion.pages.property.Property
) extends PagePropertyItem
