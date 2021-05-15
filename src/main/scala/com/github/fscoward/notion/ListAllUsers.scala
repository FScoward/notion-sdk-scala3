package com.github.fscoward.notion

case class ListAllUsers(
    `object`: String,
    results: Seq[Content],
    next_cursor: Option[String] = None,
    has_more: Boolean
)

case class Content(
    `object`: String,
    id: String,
    name: String,
    avatar_url: Option[String],
    `type`: String,
    person: Option[Person] = None,
    bot: Option[Bot] = None
)
case class Person(email: String)
case class Bot()
