package com.github.fscoward.notion.pages.create.block

trait HeadingBlock extends BlockObject {
  val text: Seq[TextObject]
}

case class Heading2Object(text: Seq[TextObject]) extends HeadingBlock {
  val `type` = "heading_2"
}
