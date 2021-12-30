package com.github.fscoward.notion.text

import com.github.fscoward.notion.property.Property

case class TextProperty(
    id: String,
    `type`: String = "text",
    text: Seq[TextPropertyValue]
) extends Property
