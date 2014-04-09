name := "$name;format="norm"$-build"

resolvers += Classpaths.sbtPluginReleases

addSbtPlugin("com.sksamuel.scoverage" %% "sbt-scoverage" % "0.95.9")

addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse-plugin" % "2.4.0")

addSbtPlugin("com.github.mpeltonen" % "sbt-idea" % "1.6.0")

addSbtPlugin("com.typesafe.sbt" % "sbt-scalariform" % "1.3.0")

addSbtPlugin("com.eed3si9n" % "sbt-buildinfo" % "0.3.1")
