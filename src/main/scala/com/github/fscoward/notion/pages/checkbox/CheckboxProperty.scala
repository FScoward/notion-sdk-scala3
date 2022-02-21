package com.github.fscoward.notion.pages.checkbox

import com.github.fscoward.notion.pages.property.{Property, PropertyType}

case class CheckboxProperty(
    id: String,
    `type`: PropertyType = PropertyType.checkbox,
    checkbox: Boolean
) extends Property
