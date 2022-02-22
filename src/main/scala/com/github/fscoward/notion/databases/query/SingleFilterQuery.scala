package com.github.fscoward.notion.databases.query

import com.github.fscoward.notion.databases.query.filter.Filter
import com.github.fscoward.notion.databases.query.filter.checkbox.CheckboxFilter
import com.github.fscoward.notion.databases.query.filter.number.NumberFilter
import com.github.fscoward.notion.databases.query.filter.text.TextFilter
import io.circe.*
import io.circe.generic.auto.*
import io.circe.parser.*
import io.circe.syntax.*

case class SingleFilterQuery(filter: Filter)

import com.github.fscoward.notion.databases.query.filter.checkbox.conditionEncoder
import com.github.fscoward.notion.databases.query.filter.text.conditionEncoder
import com.github.fscoward.notion.databases.query.filter.number.conditionEncoder
implicit val filterEncoder: Encoder[Filter] = Encoder.instance {
  case textFilter: TextFilter         => textFilter.asJson
  case numberFilter: NumberFilter     => numberFilter.asJson
  case checkboxFilter: CheckboxFilter => checkboxFilter.asJson
}
