package com.github.fscoward.notion.databases.create

case class Title(
    `type`: String = "text",
    text: TitleValue
)

case class TitleValue(content: String, link: Option[String])
