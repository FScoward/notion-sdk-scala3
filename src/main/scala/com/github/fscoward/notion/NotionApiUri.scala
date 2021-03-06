package com.github.fscoward.notion

object NotionApiUri {
  private val v1 = "https://api.notion.com/v1"
  object User {
    val user: String => String = (userId: String) => s"$v1/users/$userId"
    val listAllUsers = s"$v1/users"
  }
  object Page {
    val page: String => String = (pageId: String) => s"$v1/pages/$pageId"
    val pages = s"$v1/pages"
    def retrieveAPagePropertyItem(pageId: String, propertyId: String) =
      s"$v1/pages/$pageId/properties/$propertyId"
  }

  object Database {
    val listAlliDatabases = s"$v1/databases"
    val create = s"$v1/databases"
    val update: String => String = (databaseId: String) =>
      s"$v1/databases/$databaseId"
  }

  object Block {
    // https://api.notion.com/v1/blocks/{block_id}
    def retrieveABlock(blockId: String) = s"$v1/blocks/$blockId"
  }
}
