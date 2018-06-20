import sbt._

object Dependencies {

  val vaadinVersion = "10.0.0.rc5" // vaadinVersion auch in reference.conf nachziehen
  val slf4jVersion = "1.7.25"
  val akkaVersion = "2.5.13"

  val vaadinOrg = "com.vaadin"
  val slf4jOrg = "org.slf4j"
  val akkaOrg = "com.typesafe.akka"

  val vaadin = vaadinOrg % "vaadin-core" % vaadinVersion
  val slf4jSimple = slf4jOrg % "slf4j-simple" % slf4jVersion
  val akkaActor = akkaOrg %% "akka-actor" % akkaVersion

  val codeDeps = Seq(
    vaadin,
    slf4jSimple,
    akkaActor
  )

  // Vaadin 10 Flow has problems with default jetty in plugin :-(
  // https://mvnrepository.com/artifact/org.eclipse.jetty/jetty-runner
  val jettyLib = "org.eclipse.jetty" % "jetty-runner" % "9.3.21.v20170918" intransitive()

}
