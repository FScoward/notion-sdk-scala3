package com.github.fscoward.notion.databases.query.filter.file

import com.github.fscoward.notion.databases.query.filter.Filter

case class FileFilter(
    property: String,
    files: FileFilterCondition
) extends Filter
