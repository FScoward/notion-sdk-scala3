package com.github.fscoward.notion.databases.read

case class URLProperty(
    id: String,
    `type`: String = "url",
    url: Unit = ()
) extends Property
