package com.github.fscoward.notion.databases.query

import com.github.fscoward.notion.databases.query.filter.checkbox.CheckboxFilter
import io.circe.Encoder
import io.circe.*
import io.circe.generic.semiauto.*

case class SingleFilterQuery(filter: CheckboxFilter)
