name := "ProjectEuler"

version := "0.1"


scalaVersion := "2.12.8"

coverageEnabled := true

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % "test->*" excludeAll (ExclusionRule(organization="org.junit", name="junit"))
libraryDependencies += "org.apache.poi" % "poi-ooxml" % "3.17"
libraryDependencies += "com.google.collections" % "google-collections" % "1.0-rc2"
libraryDependencies += "com.itextpdf" % "itextpdf" % "5.0.6"
testOptions in Test += Tests.Argument(TestFrameworks.ScalaTest, "-h", "target/test-reports")