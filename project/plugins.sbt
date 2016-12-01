addSbtPlugin("com.typesafe.sbt" % "sbt-git"             % "0.8.5")

addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "1.2.0-M7")

addSbtPlugin("io.spray"         % "sbt-revolver"        % "0.8.0")

libraryDependencies ++= Vector(
  "com.veact" %% "scala-ssh"    % "0.8.0",
  "org.slf4j"  % "slf4j-simple" % "1.7.21"
)