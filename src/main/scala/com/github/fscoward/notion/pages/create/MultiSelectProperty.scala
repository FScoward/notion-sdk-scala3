package com.github.fscoward.notion.pages.create

case class MultiSelectProperty(multi_select: Seq[MultiSelectPropertyValue])
    extends Property
private case class MultiSelectPropertyValue(name: String)
