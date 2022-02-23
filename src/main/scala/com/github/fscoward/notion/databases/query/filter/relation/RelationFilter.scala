package com.github.fscoward.notion.databases.query.filter.relation

import com.github.fscoward.notion.databases.query.filter.Filter

case class RelationFilter(
    property: String,
    people: RelationFilterCondition
) extends Filter
