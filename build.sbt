name := BuildConfig.name

organization := BuildConfig.organisation

version := BuildConfig.version

scalaVersion := ScalaConfig.version
scalacOptions ++= ScalaConfig.compilerOptions

lazy val root = (project in file("."))
  .settings(inThisBuild(Seq(
    libraryDependencies := Dependencies.codeDeps
  )))
  .aggregate(test)

lazy val test = (project in file("test"))
  .enablePlugins(JettyPlugin)
  .settings(
    containerLibs in Jetty := Seq(Dependencies.jettyLib)
  )
