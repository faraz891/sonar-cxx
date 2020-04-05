/*
 * Sonar C++ Plugin (Community)
 * Copyright (C) 2010-2020 SonarOpenCommunity
 * http://github.com/SonarOpenCommunity/sonar-cxx
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.sonar.cxx.checks;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.sonar.api.batch.fs.internal.TestInputFileBuilder;
import org.sonar.api.batch.sensor.internal.SensorContextTester;
import org.sonar.cxx.CxxLanguage;

/**
 *
 * @author jocs
 */
public class CxxFileTesterHelper {

  public static CxxFileTester CreateCxxFileTester(String fileName, String basePath) throws UnsupportedEncodingException,
                                                                                           IOException {
    var tester = new CxxFileTester();
    tester.context = SensorContextTester.create(new File(basePath));

    tester.context.fileSystem().add(TestInputFileBuilder.create("", fileName).build());
    tester.cxxFile = tester.context.fileSystem().inputFile(tester.context.fileSystem().predicates().hasPath(
      fileName));

    return tester;
  }

  public static CxxFileTester CreateCxxFileTester(String fileName, String basePath, Charset charset) throws
    UnsupportedEncodingException, IOException {
    var tester = new CxxFileTester();
    tester.context = SensorContextTester.create(new File(basePath));

    tester.context.fileSystem().add(TestInputFileBuilder.create("", fileName).setCharset(charset).build());
    tester.cxxFile = tester.context.fileSystem().inputFile(tester.context.fileSystem().predicates().hasPath(
      fileName));

    return tester;
  }

  public static CxxLanguage mockCxxLanguage() {
    CxxLanguage language = Mockito.mock(CxxLanguage.class);
    when(language.getKey()).thenReturn("c++");
    when(language.getName()).thenReturn("CXX");
    when(language.getFileSuffixes())
      .thenReturn(new String[]{".cpp", ".hpp", ".h", ".cxx", ".c", ".cc", ".hxx", ".hh"});

    return language;
  }

}
