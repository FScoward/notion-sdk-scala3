package com.github.fscoward.notion.pages.relation

import com.github.fscoward.notion.pages.property.Property

case class RelationProperty(
    id: String,
    `type`: String = "relation",
    relation: Seq[RelationPropertyValue]
) extends Property
case class RelationPropertyValue(id: String)
