package com.github.fscoward.notion.pages.create

case class TitleProperty(text: TextPropertyValue) extends Property {
  val id = "title"
}
