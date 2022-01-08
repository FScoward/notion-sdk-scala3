package com.github.fscoward.notion.pages.create.block

trait HeadingObject extends BlockObject {
  val text: Seq[TextObject]
}

case class Heading2Object(text: Seq[TextObject]) extends HeadingObject {
  val `type` = "heading_2"
}
