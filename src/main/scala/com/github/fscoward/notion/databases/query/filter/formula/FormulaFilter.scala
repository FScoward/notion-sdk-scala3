package com.github.fscoward.notion.databases.query.filter.formula

import io.circe.*
import io.circe.generic.auto.*
import io.circe.parser.*
import io.circe.syntax.*

import com.github.fscoward.notion.databases.query.filter.Filter
import com.github.fscoward.notion.databases.query.filter.text._
import com.github.fscoward.notion.databases.query.filter.checkbox._
import com.github.fscoward.notion.databases.query.filter.number._
import com.github.fscoward.notion.databases.query.filter.date._

trait FormulaFilter extends Filter {
  val property: String
}

case class FormulaTextFilter(
    property: String,
    text: TextFilterCondition
) extends FormulaFilter

case class FormulaCheckboxFilter(
    property: String,
    checkbox: CheckboxFilterCondition
) extends FormulaFilter

case class FormulaNumberFilter(
    property: String,
    number: NumberFilterCondition
) extends FormulaFilter

case class FormulaDateFilter(
    property: String,
    date: DateFilterCondition
) extends FormulaFilter

implicit val filterEncoder: Encoder[FormulaFilter] = Encoder.instance {
  case formulaTextFilter: FormulaTextFilter => formulaTextFilter.asJson
  case formulaCheckboxFilter: FormulaCheckboxFilter =>
    formulaCheckboxFilter.asJson
  case formulaNumberFilter: FormulaNumberFilter => formulaNumberFilter.asJson
  case formulaDateFilter: FormulaDateFilter     => formulaDateFilter.asJson
}
