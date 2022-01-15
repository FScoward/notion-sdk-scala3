package com.github.fscoward.notion.databases.read

import com.github.fscoward.notion.databases.Property

case class URLProperty(
    id: String,
    `type`: String = "url",
    url: Unit = ()
) extends Property
