package com.github.fscoward.notion.databases.create

case class TitleProperty(
    `type`: String = "text",
    text: TitlePropertyValue
)

case class TitlePropertyValue(content: String, link: Option[String])
