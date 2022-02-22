package com.github.fscoward.notion.databases.query

import io.circe.{Encoder, Json}

case class CheckboxFilterCondition(
    propertyName: String,
    property: CheckboxPropertyType,
    value: Boolean
) extends Query {
  val queryType: String = "checkbox"
}

implicit val checkboxEncoder: Encoder[CheckboxFilterCondition] =
  (checkboxQuery: CheckboxFilterCondition) => {
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
