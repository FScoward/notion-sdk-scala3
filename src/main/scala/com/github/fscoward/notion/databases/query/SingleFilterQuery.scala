package com.github.fscoward.notion.databases.query

import io.circe.Encoder
import io.circe._, io.circe.generic.semiauto._

case class SingleFilterQuery(filter: CheckboxFilterCondition)
