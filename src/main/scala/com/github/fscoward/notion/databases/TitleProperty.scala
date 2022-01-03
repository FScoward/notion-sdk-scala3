package com.github.fscoward.notion.databases

case class TitleProperty(
    id: String,
    `type`: String = "title",
    title: Unit = ()
) extends Property
