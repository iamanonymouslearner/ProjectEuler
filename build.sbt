name := "ProjectEuler"

version := "0.1"


scalaVersion := "2.12.8"

coverageEnabled := true

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % "test->*" excludeAll (ExclusionRule(organization="org.junit", name="junit"))
testOptions in Test += Tests.Argument(TestFrameworks.ScalaTest, "-h", "target/test-reports")