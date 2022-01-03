package com.github.fscoward.notion.databases

import io.circe.Decoder
import io.circe.generic.auto.*

private val `type` = "checkbox"
//private val `type` = "select"
case class CheckboxProperty(
    id: String,
    `type`: String,
    checkbox: Unit = ()
) extends Property

val checkboxPropertyDecoder: Decoder[CheckboxProperty] =
  Decoder[CheckboxProperty].ensure(
    _.`type` == `type`,
    s"type is not ${`type`}"
  )
