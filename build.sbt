import com.decodified.scalassh.SSH

enablePlugins(GitVersioning, JavaServerAppPackaging, SystemdPlugin)

name := "longshore-info-site"

maintainer := "Jason Longshore <longshorej@gmail.com>"

requiredStartFacilities in Debian := Some("network.target")

scalaVersion := "2.12.1"

organization := "info.longshore"

git.baseVersion := "1.0"

libraryDependencies ++= Vector(
  "com.lihaoyi"            %% "scalatags"      % "0.6.2",
  "com.typesafe.akka"      %% "akka-http-core" % "10.0.0",
  "com.typesafe.akka"      %% "akka-http"      % "10.0.0",
  "com.yahoo.platform.yui"  % "yuicompressor"  % "2.4.8",
  "org.apache.commons"      % "commons-lang3"  % "3.4",
  "org.slf4j"               % "slf4j-simple"   % "1.7.21"
)

mainClass := Some("longshore.info.site.Main")

scalacOptions ++= Vector(
  "-deprecation",
  "-encoding",
  "UTF-8",
  "-feature",
  "-Xfatal-warnings",
  "-Xlint",
  "-Yno-adapted-args",
  "-Yrangepos",
  "-Ywarn-dead-code",
  "-Ywarn-numeric-widen",
  "-Ywarn-value-discard",
  "-Xfuture",
  "-Ywarn-unused-import",
  "-language:existentials",
  "-language:higherKinds",
  "-language:implicitConversions",
  "-unchecked"
)

lazy val bootstrap = TaskKey[Unit]("bootstrap", "Bootstraps the server")

lazy val deploy = TaskKey[Unit]("deploy", "Deploys this site")

def runProcess(args: String*) = {
  val code = Process(args) !

  assert(code == 0, s"${args.mkString(" ")} failed with: $code")
}

bootstrap := runProcess("fab", "-k", "-f", "script/fabfile.py", "bootstrap")

deploy <<= Def.taskDyn {
  for {
    _ <-  clean   in Compile
    a <- (compile in Compile).taskValue
    d <- (packageBin in Debian).taskValue
  } yield runProcess("fab", "-k", "-f", "script/fabfile.py", s"deploy:${d.getAbsolutePath}")
}
