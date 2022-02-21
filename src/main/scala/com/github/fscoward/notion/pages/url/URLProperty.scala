package com.github.fscoward.notion.pages.url

import com.github.fscoward.notion.pages.property.{Property, PropertyType}

case class URLProperty(
    id: String,
    `type`: PropertyType = PropertyType.url,
    url: String
) extends Property
