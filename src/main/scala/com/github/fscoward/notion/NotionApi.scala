package com.github.fscoward.notion

object NotionApi {
  private val v1 = "https://api.notion.com/v1"
  val user = (userId: String) => s"$v1/users/$userId"
  val listAllUsers = s"$v1/users"
}
