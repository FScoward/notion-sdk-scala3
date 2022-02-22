package com.github.fscoward.notion.databases.query.filter.people

import java.util.UUID

trait PeopleFilterCondition

case class ContainsCondition(contains: String) extends PeopleFilterCondition {
  require(UUID.fromString(contains).version() == 4)
}
case class DoesNotContainsCondition(does_not_contain: String)
    extends PeopleFilterCondition {
  require(UUID.fromString(does_not_contain).version() == 4)
}
