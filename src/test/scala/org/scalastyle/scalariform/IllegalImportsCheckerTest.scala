// Copyright (C) 2011-2012 the original author or authors.
// See the LICENCE.txt file distributed with this work for additional
// information regarding copyright ownership.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package org.scalastyle.scalariform

import org.scalastyle.file.CheckerTest
import org.scalatest.junit.AssertionsForJUnit
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.scalastyle.Checker
import org.scalastyle.StyleError
import java.util.Set
import org.junit.Before
import org.junit.Test

// scalastyle:off magic.number

class IllegalImportsCheckerTest extends AssertionsForJUnit with CheckerTest {
  val key = "illegal.imports"
  val classUnderTest = classOf[IllegalImportsChecker]

  @Test def testZero() = {
    val source = """
package foobar

import java.util._

object Foobar {
  val foo = 1
}
""";

    assertErrors(List(), source)
  }

  @Test def testOne() = {
    val source = """|package foobar
                      |
                      |import java.util._
                      |import sun.com.foobar;
                      |import sun._
                      |
                      |object Foobar {
                      |}
""".stripMargin;

    assertErrors(List(columnError(4, 0), columnError(5, 0)), source)
  }
}
