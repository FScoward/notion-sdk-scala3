package com.github.fscoward.notion.pages.relation

import com.github.fscoward.notion.pages.property.{Property, PropertyType}

case class RelationProperty(
    id: String,
    `type`: PropertyType = PropertyType.relation,
    relation: Seq[RelationPropertyValue]
) extends Property
case class RelationPropertyValue(id: String)
