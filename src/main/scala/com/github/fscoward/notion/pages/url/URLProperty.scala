package com.github.fscoward.notion.pages.url

import com.github.fscoward.notion.pages.property.Property

case class URLProperty(
    id: String,
    `type`: String = "url",
    url: String
) extends Property
