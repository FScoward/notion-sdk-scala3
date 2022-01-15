package com.github.fscoward.notion.databases

import com.github.fscoward.notion.databases.read.Property
import com.github.fscoward.notion.pages.property.TitlePropertyValue

case class DatabaseObject(
    `object`: String = "database",
    id: String,
    created_time: String,
    last_edited_time: String,
    title: Seq[TitlePropertyValue],
    properties: Map[String, Property] = Map.empty[String, Property]
)
