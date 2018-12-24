///////////////////////////////////////////////////////////////////////////////////////////////////
// CLI tools provide facilities for the command line interface development.
// Copyright (C) 2018 Dmitry Shapovalov.
//
// This file is part of CLI tools.
//
// CLI tools is free software: you can redistribute it and/or modify
// it under the terms of the GNU Lesser General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// CLI tools is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
// GNU Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public License
// along with this program. If not, see <http://www.gnu.org/licenses/>.
///////////////////////////////////////////////////////////////////////////////////////////////////
package ru.d_shap.cli.data;

import java.util.Arrays;

import org.junit.Test;

import ru.d_shap.assertions.Assertions;
import ru.d_shap.cli.BaseCliTest;

/**
 * Tests for {@link Lines}.
 *
 * @author Dmitry Shapovalov
 */
public final class LinesTest extends BaseCliTest {

    /**
     * Test class constructor.
     */
    public LinesTest() {
        super();
    }

    /**
     * {@link Lines} class test.
     */
    @Test
    public void addLineTest() {
        Lines lines1 = new Lines();
        Assertions.assertThat(lines1.getLines()).containsExactlyInOrder("");
        lines1.addLine(null);
        Assertions.assertThat(lines1.getLines()).containsExactlyInOrder("");
        lines1.addLine("");
        Assertions.assertThat(lines1.getLines()).containsExactlyInOrder("");
        lines1.addLine("line");
        Assertions.assertThat(lines1.getLines()).containsExactlyInOrder("", "line");

        Lines lines2 = new Lines("line1");
        Assertions.assertThat(lines2.getLines()).containsExactlyInOrder("line1");
        lines2.addLine(null);
        Assertions.assertThat(lines2.getLines()).containsExactlyInOrder("line1");
        lines2.addLine("");
        Assertions.assertThat(lines2.getLines()).containsExactlyInOrder("line1", "");
        lines2.addLine("line2");
        Assertions.assertThat(lines2.getLines()).containsExactlyInOrder("line1", "", "line2");

        Lines lines3 = new Lines("line1", "line2");
        Assertions.assertThat(lines3.getLines()).containsExactlyInOrder("line1", "line2");
        lines3.addLine(null);
        Assertions.assertThat(lines3.getLines()).containsExactlyInOrder("line1", "line2");
        lines3.addLine("");
        Assertions.assertThat(lines3.getLines()).containsExactlyInOrder("line1", "line2", "");
        lines3.addLine("line3");
        Assertions.assertThat(lines3.getLines()).containsExactlyInOrder("line1", "line2", "", "line3");

        Lines lines4 = new Lines(Arrays.asList("line1", "line2"));
        Assertions.assertThat(lines4.getLines()).containsExactlyInOrder("line1", "line2");
        lines4.addLine(null);
        Assertions.assertThat(lines4.getLines()).containsExactlyInOrder("line1", "line2");
        lines4.addLine("");
        Assertions.assertThat(lines4.getLines()).containsExactlyInOrder("line1", "line2", "");
        lines4.addLine("line3");
        Assertions.assertThat(lines4.getLines()).containsExactlyInOrder("line1", "line2", "", "line3");
    }

    /**
     * {@link Lines} class test.
     */
    @Test
    public void addLinesArrayTest() {

    }

    /**
     * {@link Lines} class test.
     */
    @Test
    public void addLinesCollectionTest() {

    }

    /**
     * {@link Lines} class test.
     */
    @Test
    public void getLinesTest() {

    }

}
