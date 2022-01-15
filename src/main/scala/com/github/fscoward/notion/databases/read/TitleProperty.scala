package com.github.fscoward.notion.databases.read

case class TitleProperty(
    id: String,
    `type`: String = "title",
    title: Unit = ()
) extends Property
