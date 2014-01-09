name := "TOST"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  "com.typesafe.play" %% "play-slick" % "0.5.0.8",
  "org.jumpmind.symmetric.jdbc" % "mariadb-java-client" % "1.1.1"
//"mysql" % "mysql-connector-java" % "5.1.24"
)     

play.Project.playScalaSettings
