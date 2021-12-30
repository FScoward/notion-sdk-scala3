package com.github.fscoward.notion.url

import com.github.fscoward.notion.property.Property

case class URLProperty(
    id: String,
    `type`: String = "url",
    url: String
) extends Property
