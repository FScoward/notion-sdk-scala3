package com.github.fscoward.notion.checkbox

import com.github.fscoward.notion.property.Property

case class CheckboxProperty(
    id: String,
    `type`: String = "checkbox",
    checkbox: Boolean
) extends Property
