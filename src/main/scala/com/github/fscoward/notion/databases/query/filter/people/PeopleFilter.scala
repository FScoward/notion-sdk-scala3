package com.github.fscoward.notion.databases.query.filter.people

import com.github.fscoward.notion.databases.query.filter.Filter

case class PeopleFilter(
    property: String,
    people: PeopleFilterCondition
) extends Filter
