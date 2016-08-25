import sbt._
import sbt.Keys._
import com.typesafe.sbt.SbtScalariform._
import com.typesafe.sbteclipse.core.EclipsePlugin._
import scalariform.formatter.preferences._
import scoverage.ScoverageSbtPlugin
import sbtbuildinfo.BuildInfoKeys._

object $name;format="Camel"$Build extends Build {

  object Dependencies {
    val catsVersion = "0.7.0"

    val cats = "org.typelevel" %% "cats" % catsVersion withSources () withJavadoc ()

    val scalatest = "org.scalatest" %% "scalatest" % "3.0.0" % "test" withSources () withJavadoc ()

    val scalacheck = "org.scalacheck" %% "scalacheck" % "1.13.1" % "test" withSources () withJavadoc ()

    val monocleVersion = "1.2.2"

    val monocle = Seq(
      "com.github.julien-truffaut" %% "monocle-core"    % monocleVersion,
      "com.github.julien-truffaut" %% "monocle-macro"   % monocleVersion,
      "com.github.julien-truffaut" %% "monocle-generic" % monocleVersion,
      "com.github.julien-truffaut" %% "monocle-state"   % monocleVersion
    )

  }

  override def settings = super.settings :+ ( EclipseKeys.skipParents in ThisBuild := false )

  lazy val $name;format="camel"$ScalariformSettings = scalariformSettings ++ Seq(
    ScalariformKeys.preferences := defaultPreferences
      .setPreference( AlignSingleLineCaseStatements, true )
      .setPreference( SpaceBeforeColon, true )
      .setPreference( SpaceInsideParentheses, true )    
  )

  lazy val sharedSettings =
    Seq(
      organization := "$organisation$",
      scalaVersion := "$scalaVersion$")

  lazy val $name;format="camel"$Settings = 
    Defaults.coreDefaultSettings ++
    SbtBuildInfo.buildSettings("$package$") ++
    SbtEclipse.buildSettings ++
    $name;format="camel"$ScalariformSettings ++
    sharedSettings ++
    Seq(
      libraryDependencies ++= Seq(
          Dependencies.scalatest,
          Dependencies.scalacheck,
          Dependencies.cats ) ++ 
          Dependencies.monocle,
      scalacOptions ++= Seq( "-feature", "-deprecation" ),
      unmanagedSourceDirectories in Compile := (scalaSource in Compile).value :: Nil,
      unmanagedSourceDirectories in Test := (scalaSource in Test).value :: Nil
    )

  lazy val $name;format="camel"$ = Project(
    id = "$name;format="norm"$",
    base = file( "." ),
    settings = Defaults.coreDefaultSettings ++
      sharedSettings ++
      Seq( name := "$name;format="norm"$" )
  ).aggregate(
    $module;format="camel"$
  )

  lazy val $module;format="camel"$ = Project(
    id = "$module;format="norm"$",
    base = file( "$module;format="norm"$" ),
    settings = $name;format="camel"$Settings ++
      Seq(
        name := "$module;format="norm"$",
        mainClass := Some("$package$.Main"),
        initialCommands := """|import $package$._
                              |import scalaz._,Scalaz._""".stripMargin,
        buildInfoObject := "$module;format="Camel"$BuildInfo"
      )
  )
}
