package com.github.fscoward.notion.databases.query

import com.github.fscoward.notion.databases.query.filterCondition.CheckboxFilterCondition
import io.circe.Encoder
import io.circe.*
import io.circe.generic.semiauto.*

case class SingleFilterQuery(filter: CheckboxFilterCondition)
