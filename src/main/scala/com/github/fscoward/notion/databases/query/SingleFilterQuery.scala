package com.github.fscoward.notion.databases.query

import com.github.fscoward.notion.databases.query.filter.Filter
import com.github.fscoward.notion.databases.query.filter.checkbox.CheckboxFilter
import com.github.fscoward.notion.databases.query.filter.date.DateFilter
import com.github.fscoward.notion.databases.query.filter.file.FileFilter
import com.github.fscoward.notion.databases.query.filter.number.NumberFilter
import com.github.fscoward.notion.databases.query.filter.select.SelectFilter
import com.github.fscoward.notion.databases.query.filter.text.TextFilter
import com.github.fscoward.notion.databases.query.filter.multiSelect.MultiSelectFilter
import com.github.fscoward.notion.databases.query.filter.people.PeopleFilter
import io.circe.*
import io.circe.generic.auto.*
import io.circe.parser.*
import io.circe.syntax.*

case class SingleFilterQuery(filter: Filter)

import com.github.fscoward.notion.databases.query.filter.checkbox.conditionEncoder
import com.github.fscoward.notion.databases.query.filter.text.conditionEncoder
import com.github.fscoward.notion.databases.query.filter.number.conditionEncoder
import com.github.fscoward.notion.databases.query.filter.select.conditionEncoder
import com.github.fscoward.notion.databases.query.filter.date.conditionEncoder
import com.github.fscoward.notion.databases.query.filter.people.conditionEncoder
import com.github.fscoward.notion.databases.query.filter.file.conditionEncoder
// TODO: relation
// TODO: formula

implicit val filterEncoder: Encoder[Filter] = Encoder.instance {
  case textFilter: TextFilter               => textFilter.asJson
  case numberFilter: NumberFilter           => numberFilter.asJson
  case checkboxFilter: CheckboxFilter       => checkboxFilter.asJson
  case selectFilter: SelectFilter           => selectFilter.asJson
  case multiSelectFilter: MultiSelectFilter => multiSelectFilter.asJson
  case dateFilter: DateFilter               => dateFilter.asJson
  case peopleFilter: PeopleFilter           => peopleFilter.asJson
  case fileFilter: FileFilter               => fileFilter.asJson
  // TODO: relation
  // TODO: formula
}
