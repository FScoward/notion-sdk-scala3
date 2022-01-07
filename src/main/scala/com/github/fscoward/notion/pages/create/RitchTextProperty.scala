package com.github.fscoward.notion.pages.create

case class RitchTextProperty(text: TextPropertyValue) extends Property {
  val id = "rich_text"
}
