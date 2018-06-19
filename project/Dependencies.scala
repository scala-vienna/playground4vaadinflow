import sbt._

object Dependencies {

  val vaadinVersion = "10.0.0.rc5" // vaadinVersion auch in reference.conf nachziehen
  val servletapiVersion = "3.1.0"
  val slf4jVersion = "1.7.25"

  val vaadinOrg = "com.vaadin"
  val slf4jOrg = "org.slf4j"

  val servletApi = "javax.servlet" % "javax.servlet-api" % servletapiVersion % "provided"
  val slf4jSimple = slf4jOrg % "slf4j-simple" % slf4jVersion
  val vaadinCore = vaadinOrg % "vaadin-core" % vaadinVersion

  val codeDeps = Seq(
    servletApi,
    slf4jSimple,
    vaadinCore
  )

  // Vaadin 10 Flow has problems with default jetty in plugin :-(
  // https://mvnrepository.com/artifact/org.eclipse.jetty/jetty-runner
  val jettyLib = "org.eclipse.jetty" % "jetty-runner" % "9.3.21.v20170918" intransitive()

}
