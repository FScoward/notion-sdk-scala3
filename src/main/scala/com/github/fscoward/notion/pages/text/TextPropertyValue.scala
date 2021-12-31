package com.github.fscoward.notion.pages.text

import com.github.fscoward.notion.pages.annotation.NotionAnnotation
import com.github.fscoward.notion.pages.property.TextObj

case class TextPropertyValue(
    `type`: String, // TODO: Enum
    text: TextObj,
    annotations: NotionAnnotation,
    plain_text: String,
    href: Option[String]
)
