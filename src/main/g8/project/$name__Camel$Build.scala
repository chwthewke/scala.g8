import sbt._
import sbt.Keys._
import com.typesafe.sbt.SbtScalariform._
import com.typesafe.sbteclipse.core.EclipsePlugin._
import scalariform.formatter.preferences._
import sbtbuildinfo.Plugin._

object $name;format="Camel"$Build extends Build {

  object Dependencies {
    val scalazVersion = "7.1.0-M6"

    val scalaz = "org.scalaz" %% "scalaz-core" % scalazVersion withSources () withJavadoc ()

    val scalatest = "org.scalatest" % "scalatest_2.10" % "2.1.2" % "test" withSources () withJavadoc ()

    val scalacheck = Seq(
      "org.scalacheck" %% "scalacheck" % "1.11.3" % "test" withSources () withJavadoc (),
      "org.scalaz" %% "scalaz-scalacheck-binding" % scalazVersion % "test" withSources () withJavadoc ()      
    )

  }

  lazy val $name;format="camel"$BuildInfoSettings = buildInfoSettings ++ Seq(
    sourceGenerators in Compile <+= buildInfo,
    buildInfoKeys := Seq(version),
    buildInfoPackage := "net.chwthewke.backups"
  )

  lazy val $name;format="camel"$ = Project(
    id = "$name;format="norm"$",
    base = file( "." ),
    settings = Project.defaultSettings ++
      ScoverageSbtPlugin.instrumentSettings ++
      scalariformSettings ++
      $name;format="camel"$BuildInfoSettings ++
      Seq(
        name := "$name;format="norm"$",
        organization := "net.chwthewke",
        scalaVersion := "$scalaVersion$",
        libraryDependencies ++= Seq(
          Dependencies.scalatest,
          Dependencies.scalaz ) ++ 
          Dependencies.scalacheck,
        scalacOptions ++= Seq( "-feature", "-deprecation" ),
        unmanagedSourceDirectories in Compile := (scalaSource in Compile).value :: Nil,
        unmanagedSourceDirectories in Test := (scalaSource in Test).value :: Nil,
        mainClass := Some("$package$.Main"),
        ScalariformKeys.preferences := defaultPreferences
          .setPreference( AlignSingleLineCaseStatements, true )
          .setPreference( SpaceBeforeColon, true )
          .setPreference( SpaceInsideParentheses, true ),
        EclipseKeys.projectFlavor := EclipseProjectFlavor.Scala,
        EclipseKeys.createSrc := EclipseCreateSrc.Default + EclipseCreateSrc.Resource,
        initialCommands := "import $package$"
      )
  )
}
