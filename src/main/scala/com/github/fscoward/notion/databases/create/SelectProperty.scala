package com.github.fscoward.notion.databases.create

case class SelectProperty(options: Seq[SelectPropertyValue]) extends Property {
  val `type`: String = "select"
}

case class SelectPropertyValue(name: String, color: String)
