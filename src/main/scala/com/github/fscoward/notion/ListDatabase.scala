package com.github.fscoward.notion

case class ListDatabase(results: Seq[Database])

case class Database(
    `object`: String,
    id: String,
    created_time: String,
    last_edited_time: String,
    title: String,
    properties: Map[String, Property]
)
