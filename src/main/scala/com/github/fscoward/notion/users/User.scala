package com.github.fscoward.notion.users

case class User(
    `object`: String,
    id: String,
    name: String,
    avatar_url: Option[String],
    `type`: String,
    person: Option[Person] = None,
    bot: Option[Bot] = None
)
