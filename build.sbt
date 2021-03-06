val scala3Version = "3.1.1"

lazy val root = project
  .in(file("."))
  .settings(
    name := "notion-sdk-scala3",
    version := "0.1.0",
    scalaVersion := scala3Version,
    libraryDependencies ++= Seq(
      "com.softwaremill.sttp.client3" %% "core" % "3.5.1",
      "com.github.sbt" % "junit-interface" % "0.13.3" % "test",
      "io.circe" %% "circe-core" % "0.14.1",
      "com.softwaremill.sttp.client3" %% "circe" % "3.5.1",
      "io.circe" %% "circe-generic" % "0.14.1",
      "org.scalameta" %% "munit" % "1.0.0-M3" % Test,
      "com.softwaremill.sttp.client3" %% "okhttp-backend" % "3.5.1"
    )
  )
