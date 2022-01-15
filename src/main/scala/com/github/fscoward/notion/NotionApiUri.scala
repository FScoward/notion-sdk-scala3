package com.github.fscoward.notion

object NotionApiUri {
  private val v1 = "https://api.notion.com/v1"
  object User {
    val user = (userId: String) => s"$v1/users/$userId"
    val listAllUsers = s"$v1/users"
  }
  object Page {
    val page = (pageId: String) => s"$v1/pages/$pageId"
    val pages = s"$v1/pages"
  }

  object Database {
    val listAlliDatabases = s"$v1/databases"
    val create = s"$v1/databases"
  }
}
