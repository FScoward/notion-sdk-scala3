package com.github.fscoward.notion.pages.createdTime

import com.github.fscoward.notion.pages.property.Property

case class CreatedTimeProperty(
    id: String,
    `type`: String = "created_time",
    created_time: String
) extends Property
