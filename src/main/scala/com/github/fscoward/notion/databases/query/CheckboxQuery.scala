package com.github.fscoward.notion.databases.query

import io.circe.{Encoder, Json}

case class CheckboxQuery(
    propertyName: String,
    property: CheckboxPropertyType,
    value: Boolean
) {
  val queryType: String = "checkbox"
}

implicit val checkboxEncoder: Encoder[CheckboxQuery] =
  (checkboxQuery: CheckboxQuery) => {
    Json.obj(
      ("property", Json.fromString(checkboxQuery.propertyName)),
      (
        checkboxQuery.queryType,
        Json.obj(
          (
            checkboxQuery.property.toString,
            Json.fromBoolean(checkboxQuery.value)
          )
        )
      )
    )
  }

enum CheckboxPropertyType:
  case equals extends CheckboxPropertyType
  case does_not_equal extends CheckboxPropertyType
end CheckboxPropertyType
