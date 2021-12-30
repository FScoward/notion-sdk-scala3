package com.github.fscoward.notion.text

import com.github.fscoward.notion.{NotionAnnotation, TextObj}

case class TextPropertyValue(
    `type`: String, // TODO: Enum
    text: TextObj,
    annotations: NotionAnnotation,
    plain_text: String,
    href: Option[String]
)
