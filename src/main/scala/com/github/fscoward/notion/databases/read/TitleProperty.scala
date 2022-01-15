package com.github.fscoward.notion.databases.read

import com.github.fscoward.notion.databases.Property

case class TitleProperty(
    id: String,
    `type`: String = "title",
    title: Unit = ()
) extends Property
