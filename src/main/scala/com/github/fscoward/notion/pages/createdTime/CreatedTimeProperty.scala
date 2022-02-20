package com.github.fscoward.notion.pages.createdTime

import com.github.fscoward.notion.pages.property.{Property, PropertyType}

case class CreatedTimeProperty(
    id: String,
    `type`: PropertyType = PropertyType.created_time,
    created_time: String
) extends Property
