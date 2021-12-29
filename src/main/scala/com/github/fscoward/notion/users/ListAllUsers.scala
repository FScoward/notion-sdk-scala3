package com.github.fscoward.notion.users

case class ListAllUsers(
    `object`: String,
    results: Seq[User],
    next_cursor: Option[String] = None,
    has_more: Boolean
)
