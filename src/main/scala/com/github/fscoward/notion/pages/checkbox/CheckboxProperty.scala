package com.github.fscoward.notion.pages.checkbox

import com.github.fscoward.notion.pages.property.Property

case class CheckboxProperty(
    id: String,
    `type`: String = "checkbox",
    checkbox: Boolean
) extends Property
