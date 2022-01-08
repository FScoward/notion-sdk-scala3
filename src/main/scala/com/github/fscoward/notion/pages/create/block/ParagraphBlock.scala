package com.github.fscoward.notion.pages.create.block

case class ParagraphBlock(text: Seq[TextObject]) extends BlockObject {
  val `type`: String = "paragraph"
}
