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
import java.util.Collection;
import java.util.List;

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
        lines1.addLine("line3");
        Assertions.assertThat(lines1.getLines()).containsExactlyInOrder("", "line3");

        Lines lines2 = new Lines("line1");
        Assertions.assertThat(lines2.getLines()).containsExactlyInOrder("line1");
        lines2.addLine(null);
        Assertions.assertThat(lines2.getLines()).containsExactlyInOrder("line1");
        lines2.addLine("");
        Assertions.assertThat(lines2.getLines()).containsExactlyInOrder("line1", "");
        lines2.addLine("line3");
        Assertions.assertThat(lines2.getLines()).containsExactlyInOrder("line1", "", "line3");

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

        Lines lines51 = new Lines((Lines) null);
        Assertions.assertThat(lines51.getLines()).containsExactlyInOrder("");
        lines51.addLine(null);
        Assertions.assertThat(lines51.getLines()).containsExactlyInOrder("");
        lines51.addLine("");
        Assertions.assertThat(lines51.getLines()).containsExactlyInOrder("");
        lines51.addLine("line3");
        Assertions.assertThat(lines51.getLines()).containsExactlyInOrder("", "line3");

        Lines lines52 = new Lines(new Lines());
        Assertions.assertThat(lines52.getLines()).containsExactlyInOrder("");
        lines52.addLine(null);
        Assertions.assertThat(lines52.getLines()).containsExactlyInOrder("");
        lines52.addLine("");
        Assertions.assertThat(lines52.getLines()).containsExactlyInOrder("");
        lines52.addLine("line3");
        Assertions.assertThat(lines52.getLines()).containsExactlyInOrder("", "line3");

        Lines lines53 = new Lines(new Lines("base"));
        Assertions.assertThat(lines53.getLines()).containsExactlyInOrder("base");
        lines53.addLine(null);
        Assertions.assertThat(lines53.getLines()).containsExactlyInOrder("base");
        lines53.addLine("");
        Assertions.assertThat(lines53.getLines()).containsExactlyInOrder("base", "");
        lines53.addLine("line3");
        Assertions.assertThat(lines53.getLines()).containsExactlyInOrder("base", "", "line3");

        Lines lines61 = new Lines(null, "line1");
        Assertions.assertThat(lines61.getLines()).containsExactlyInOrder("line1");
        lines61.addLine(null);
        Assertions.assertThat(lines61.getLines()).containsExactlyInOrder("line1");
        lines61.addLine("");
        Assertions.assertThat(lines61.getLines()).containsExactlyInOrder("line1", "");
        lines61.addLine("line3");
        Assertions.assertThat(lines61.getLines()).containsExactlyInOrder("line1", "", "line3");

        Lines lines62 = new Lines(new Lines(), "line1");
        Assertions.assertThat(lines62.getLines()).containsExactlyInOrder("line1");
        lines62.addLine(null);
        Assertions.assertThat(lines62.getLines()).containsExactlyInOrder("line1");
        lines62.addLine("");
        Assertions.assertThat(lines62.getLines()).containsExactlyInOrder("line1", "");
        lines62.addLine("line3");
        Assertions.assertThat(lines62.getLines()).containsExactlyInOrder("line1", "", "line3");

        Lines lines63 = new Lines(new Lines("base"), "line1");
        Assertions.assertThat(lines63.getLines()).containsExactlyInOrder("base", "line1");
        lines63.addLine(null);
        Assertions.assertThat(lines63.getLines()).containsExactlyInOrder("base", "line1");
        lines63.addLine("");
        Assertions.assertThat(lines63.getLines()).containsExactlyInOrder("base", "line1", "");
        lines63.addLine("line3");
        Assertions.assertThat(lines63.getLines()).containsExactlyInOrder("base", "line1", "", "line3");

        Lines lines71 = new Lines((Lines) null, "line1", "line2");
        Assertions.assertThat(lines71.getLines()).containsExactlyInOrder("line1", "line2");
        lines71.addLine(null);
        Assertions.assertThat(lines71.getLines()).containsExactlyInOrder("line1", "line2");
        lines71.addLine("");
        Assertions.assertThat(lines71.getLines()).containsExactlyInOrder("line1", "line2", "");
        lines71.addLine("line3");
        Assertions.assertThat(lines71.getLines()).containsExactlyInOrder("line1", "line2", "", "line3");

        Lines lines72 = new Lines(new Lines(), "line1", "line2");
        Assertions.assertThat(lines72.getLines()).containsExactlyInOrder("line1", "line2");
        lines72.addLine(null);
        Assertions.assertThat(lines72.getLines()).containsExactlyInOrder("line1", "line2");
        lines72.addLine("");
        Assertions.assertThat(lines72.getLines()).containsExactlyInOrder("line1", "line2", "");
        lines72.addLine("line3");
        Assertions.assertThat(lines72.getLines()).containsExactlyInOrder("line1", "line2", "", "line3");

        Lines lines73 = new Lines(new Lines("base"), "line1", "line2");
        Assertions.assertThat(lines73.getLines()).containsExactlyInOrder("base", "line1", "line2");
        lines73.addLine(null);
        Assertions.assertThat(lines73.getLines()).containsExactlyInOrder("base", "line1", "line2");
        lines73.addLine("");
        Assertions.assertThat(lines73.getLines()).containsExactlyInOrder("base", "line1", "line2", "");
        lines73.addLine("line3");
        Assertions.assertThat(lines73.getLines()).containsExactlyInOrder("base", "line1", "line2", "", "line3");

        Lines lines81 = new Lines(null, Arrays.asList("line1", "line2"));
        Assertions.assertThat(lines81.getLines()).containsExactlyInOrder("line1", "line2");
        lines81.addLine(null);
        Assertions.assertThat(lines81.getLines()).containsExactlyInOrder("line1", "line2");
        lines81.addLine("");
        Assertions.assertThat(lines81.getLines()).containsExactlyInOrder("line1", "line2", "");
        lines81.addLine("line3");
        Assertions.assertThat(lines81.getLines()).containsExactlyInOrder("line1", "line2", "", "line3");

        Lines lines82 = new Lines(new Lines(), Arrays.asList("line1", "line2"));
        Assertions.assertThat(lines82.getLines()).containsExactlyInOrder("line1", "line2");
        lines82.addLine(null);
        Assertions.assertThat(lines82.getLines()).containsExactlyInOrder("line1", "line2");
        lines82.addLine("");
        Assertions.assertThat(lines82.getLines()).containsExactlyInOrder("line1", "line2", "");
        lines82.addLine("line3");
        Assertions.assertThat(lines82.getLines()).containsExactlyInOrder("line1", "line2", "", "line3");

        Lines lines83 = new Lines(new Lines("base"), Arrays.asList("line1", "line2"));
        Assertions.assertThat(lines83.getLines()).containsExactlyInOrder("base", "line1", "line2");
        lines83.addLine(null);
        Assertions.assertThat(lines83.getLines()).containsExactlyInOrder("base", "line1", "line2");
        lines83.addLine("");
        Assertions.assertThat(lines83.getLines()).containsExactlyInOrder("base", "line1", "line2", "");
        lines83.addLine("line3");
        Assertions.assertThat(lines83.getLines()).containsExactlyInOrder("base", "line1", "line2", "", "line3");
    }

    /**
     * {@link Lines} class test.
     */
    @Test
    public void addLinesArrayTest() {
        Lines lines1 = new Lines();
        Assertions.assertThat(lines1.getLines()).containsExactlyInOrder("");
        lines1.addLines((String[]) null);
        Assertions.assertThat(lines1.getLines()).containsExactlyInOrder("");
        lines1.addLines(null, "", "line3");
        Assertions.assertThat(lines1.getLines()).containsExactlyInOrder("", "line3");

        Lines lines2 = new Lines("line1");
        Assertions.assertThat(lines2.getLines()).containsExactlyInOrder("line1");
        lines2.addLines((String[]) null);
        Assertions.assertThat(lines2.getLines()).containsExactlyInOrder("line1");
        lines2.addLines(null, "", "line3");
        Assertions.assertThat(lines2.getLines()).containsExactlyInOrder("line1", "", "line3");

        Lines lines3 = new Lines("line1", "line2");
        Assertions.assertThat(lines3.getLines()).containsExactlyInOrder("line1", "line2");
        lines3.addLines((String[]) null);
        Assertions.assertThat(lines3.getLines()).containsExactlyInOrder("line1", "line2");
        lines3.addLines(null, "", "line3");
        Assertions.assertThat(lines3.getLines()).containsExactlyInOrder("line1", "line2", "", "line3");

        Lines lines4 = new Lines(Arrays.asList("line1", "line2"));
        Assertions.assertThat(lines4.getLines()).containsExactlyInOrder("line1", "line2");
        lines4.addLines((String[]) null);
        Assertions.assertThat(lines4.getLines()).containsExactlyInOrder("line1", "line2");
        lines4.addLines(null, "", "line3");
        Assertions.assertThat(lines4.getLines()).containsExactlyInOrder("line1", "line2", "", "line3");

        Lines lines51 = new Lines((Lines) null);
        Assertions.assertThat(lines51.getLines()).containsExactlyInOrder("");
        lines51.addLines((String[]) null);
        Assertions.assertThat(lines51.getLines()).containsExactlyInOrder("");
        lines51.addLines(null, "", "line3");
        Assertions.assertThat(lines51.getLines()).containsExactlyInOrder("", "line3");

        Lines lines52 = new Lines(new Lines());
        Assertions.assertThat(lines52.getLines()).containsExactlyInOrder("");
        lines52.addLines((String[]) null);
        Assertions.assertThat(lines52.getLines()).containsExactlyInOrder("");
        lines52.addLines(null, "", "line3");
        Assertions.assertThat(lines52.getLines()).containsExactlyInOrder("", "line3");

        Lines lines53 = new Lines(new Lines("base"));
        Assertions.assertThat(lines53.getLines()).containsExactlyInOrder("base");
        lines53.addLines((String[]) null);
        Assertions.assertThat(lines53.getLines()).containsExactlyInOrder("base");
        lines53.addLines(null, "", "line3");
        Assertions.assertThat(lines53.getLines()).containsExactlyInOrder("base", "", "line3");

        Lines lines61 = new Lines(null, "line1");
        Assertions.assertThat(lines61.getLines()).containsExactlyInOrder("line1");
        lines61.addLines((String[]) null);
        Assertions.assertThat(lines61.getLines()).containsExactlyInOrder("line1");
        lines61.addLines(null, "", "line3");
        Assertions.assertThat(lines61.getLines()).containsExactlyInOrder("line1", "", "line3");

        Lines lines62 = new Lines(new Lines(), "line1");
        Assertions.assertThat(lines62.getLines()).containsExactlyInOrder("line1");
        lines62.addLines((String[]) null);
        Assertions.assertThat(lines62.getLines()).containsExactlyInOrder("line1");
        lines62.addLines(null, "", "line3");
        Assertions.assertThat(lines62.getLines()).containsExactlyInOrder("line1", "", "line3");

        Lines lines63 = new Lines(new Lines("base"), "line1");
        Assertions.assertThat(lines63.getLines()).containsExactlyInOrder("base", "line1");
        lines63.addLines((String[]) null);
        Assertions.assertThat(lines63.getLines()).containsExactlyInOrder("base", "line1");
        lines63.addLines(null, "", "line3");
        Assertions.assertThat(lines63.getLines()).containsExactlyInOrder("base", "line1", "", "line3");

        Lines lines71 = new Lines((Lines) null, "line1", "line2");
        Assertions.assertThat(lines71.getLines()).containsExactlyInOrder("line1", "line2");
        lines71.addLines((String[]) null);
        Assertions.assertThat(lines71.getLines()).containsExactlyInOrder("line1", "line2");
        lines71.addLines(null, "", "line3");
        Assertions.assertThat(lines71.getLines()).containsExactlyInOrder("line1", "line2", "", "line3");

        Lines lines72 = new Lines(new Lines(), "line1", "line2");
        Assertions.assertThat(lines72.getLines()).containsExactlyInOrder("line1", "line2");
        lines72.addLines((String[]) null);
        Assertions.assertThat(lines72.getLines()).containsExactlyInOrder("line1", "line2");
        lines72.addLines(null, "", "line3");
        Assertions.assertThat(lines72.getLines()).containsExactlyInOrder("line1", "line2", "", "line3");

        Lines lines73 = new Lines(new Lines("base"), "line1", "line2");
        Assertions.assertThat(lines73.getLines()).containsExactlyInOrder("base", "line1", "line2");
        lines73.addLines((String[]) null);
        Assertions.assertThat(lines73.getLines()).containsExactlyInOrder("base", "line1", "line2");
        lines73.addLines(null, "", "line3");
        Assertions.assertThat(lines73.getLines()).containsExactlyInOrder("base", "line1", "line2", "", "line3");

        Lines lines81 = new Lines(null, Arrays.asList("line1", "line2"));
        Assertions.assertThat(lines81.getLines()).containsExactlyInOrder("line1", "line2");
        lines81.addLines((String[]) null);
        Assertions.assertThat(lines81.getLines()).containsExactlyInOrder("line1", "line2");
        lines81.addLines(null, "", "line3");
        Assertions.assertThat(lines81.getLines()).containsExactlyInOrder("line1", "line2", "", "line3");

        Lines lines82 = new Lines(new Lines(), Arrays.asList("line1", "line2"));
        Assertions.assertThat(lines82.getLines()).containsExactlyInOrder("line1", "line2");
        lines82.addLines((String[]) null);
        Assertions.assertThat(lines82.getLines()).containsExactlyInOrder("line1", "line2");
        lines82.addLines(null, "", "line3");
        Assertions.assertThat(lines82.getLines()).containsExactlyInOrder("line1", "line2", "", "line3");

        Lines lines83 = new Lines(new Lines("base"), Arrays.asList("line1", "line2"));
        Assertions.assertThat(lines83.getLines()).containsExactlyInOrder("base", "line1", "line2");
        lines83.addLines((String[]) null);
        Assertions.assertThat(lines83.getLines()).containsExactlyInOrder("base", "line1", "line2");
        lines83.addLines(null, "", "line3");
        Assertions.assertThat(lines83.getLines()).containsExactlyInOrder("base", "line1", "line2", "", "line3");
    }

    /**
     * {@link Lines} class test.
     */
    @Test
    public void addLinesCollectionTest() {
        Lines lines1 = new Lines();
        Assertions.assertThat(lines1.getLines()).containsExactlyInOrder("");
        lines1.addLines((Collection<String>) null);
        Assertions.assertThat(lines1.getLines()).containsExactlyInOrder("");
        lines1.addLines(Arrays.asList(null, "", "line3"));
        Assertions.assertThat(lines1.getLines()).containsExactlyInOrder("", "line3");

        Lines lines2 = new Lines("line1");
        Assertions.assertThat(lines2.getLines()).containsExactlyInOrder("line1");
        lines2.addLines((Collection<String>) null);
        Assertions.assertThat(lines2.getLines()).containsExactlyInOrder("line1");
        lines2.addLines(Arrays.asList(null, "", "line3"));
        Assertions.assertThat(lines2.getLines()).containsExactlyInOrder("line1", "", "line3");

        Lines lines3 = new Lines("line1", "line2");
        Assertions.assertThat(lines3.getLines()).containsExactlyInOrder("line1", "line2");
        lines3.addLines((Collection<String>) null);
        Assertions.assertThat(lines3.getLines()).containsExactlyInOrder("line1", "line2");
        lines3.addLines(Arrays.asList(null, "", "line3"));
        Assertions.assertThat(lines3.getLines()).containsExactlyInOrder("line1", "line2", "", "line3");

        Lines lines4 = new Lines(Arrays.asList("line1", "line2"));
        Assertions.assertThat(lines4.getLines()).containsExactlyInOrder("line1", "line2");
        lines4.addLines((Collection<String>) null);
        Assertions.assertThat(lines4.getLines()).containsExactlyInOrder("line1", "line2");
        lines4.addLines(Arrays.asList(null, "", "line3"));
        Assertions.assertThat(lines4.getLines()).containsExactlyInOrder("line1", "line2", "", "line3");

        Lines lines51 = new Lines((Lines) null);
        Assertions.assertThat(lines51.getLines()).containsExactlyInOrder("");
        lines51.addLines((Collection<String>) null);
        Assertions.assertThat(lines51.getLines()).containsExactlyInOrder("");
        lines51.addLines(Arrays.asList(null, "", "line3"));
        Assertions.assertThat(lines51.getLines()).containsExactlyInOrder("", "line3");

        Lines lines52 = new Lines(new Lines());
        Assertions.assertThat(lines52.getLines()).containsExactlyInOrder("");
        lines52.addLines((Collection<String>) null);
        Assertions.assertThat(lines52.getLines()).containsExactlyInOrder("");
        lines52.addLines(Arrays.asList(null, "", "line3"));
        Assertions.assertThat(lines52.getLines()).containsExactlyInOrder("", "line3");

        Lines lines53 = new Lines(new Lines("base"));
        Assertions.assertThat(lines53.getLines()).containsExactlyInOrder("base");
        lines53.addLines((Collection<String>) null);
        Assertions.assertThat(lines53.getLines()).containsExactlyInOrder("base");
        lines53.addLines(Arrays.asList(null, "", "line3"));
        Assertions.assertThat(lines53.getLines()).containsExactlyInOrder("base", "", "line3");

        Lines lines61 = new Lines(null, "line1");
        Assertions.assertThat(lines61.getLines()).containsExactlyInOrder("line1");
        lines61.addLines((Collection<String>) null);
        Assertions.assertThat(lines61.getLines()).containsExactlyInOrder("line1");
        lines61.addLines(Arrays.asList(null, "", "line3"));
        Assertions.assertThat(lines61.getLines()).containsExactlyInOrder("line1", "", "line3");

        Lines lines62 = new Lines(new Lines(), "line1");
        Assertions.assertThat(lines62.getLines()).containsExactlyInOrder("line1");
        lines62.addLines((Collection<String>) null);
        Assertions.assertThat(lines62.getLines()).containsExactlyInOrder("line1");
        lines62.addLines(Arrays.asList(null, "", "line3"));
        Assertions.assertThat(lines62.getLines()).containsExactlyInOrder("line1", "", "line3");

        Lines lines63 = new Lines(new Lines("base"), "line1");
        Assertions.assertThat(lines63.getLines()).containsExactlyInOrder("base", "line1");
        lines63.addLines((Collection<String>) null);
        Assertions.assertThat(lines63.getLines()).containsExactlyInOrder("base", "line1");
        lines63.addLines(Arrays.asList(null, "", "line3"));
        Assertions.assertThat(lines63.getLines()).containsExactlyInOrder("base", "line1", "", "line3");

        Lines lines71 = new Lines((Lines) null, "line1", "line2");
        Assertions.assertThat(lines71.getLines()).containsExactlyInOrder("line1", "line2");
        lines71.addLines((Collection<String>) null);
        Assertions.assertThat(lines71.getLines()).containsExactlyInOrder("line1", "line2");
        lines71.addLines(Arrays.asList(null, "", "line3"));
        Assertions.assertThat(lines71.getLines()).containsExactlyInOrder("line1", "line2", "", "line3");

        Lines lines72 = new Lines(new Lines(), "line1", "line2");
        Assertions.assertThat(lines72.getLines()).containsExactlyInOrder("line1", "line2");
        lines72.addLines((Collection<String>) null);
        Assertions.assertThat(lines72.getLines()).containsExactlyInOrder("line1", "line2");
        lines72.addLines(Arrays.asList(null, "", "line3"));
        Assertions.assertThat(lines72.getLines()).containsExactlyInOrder("line1", "line2", "", "line3");

        Lines lines73 = new Lines(new Lines("base"), "line1", "line2");
        Assertions.assertThat(lines73.getLines()).containsExactlyInOrder("base", "line1", "line2");
        lines73.addLines((Collection<String>) null);
        Assertions.assertThat(lines73.getLines()).containsExactlyInOrder("base", "line1", "line2");
        lines73.addLines(Arrays.asList(null, "", "line3"));
        Assertions.assertThat(lines73.getLines()).containsExactlyInOrder("base", "line1", "line2", "", "line3");

        Lines lines81 = new Lines(null, Arrays.asList("line1", "line2"));
        Assertions.assertThat(lines81.getLines()).containsExactlyInOrder("line1", "line2");
        lines81.addLines((Collection<String>) null);
        Assertions.assertThat(lines81.getLines()).containsExactlyInOrder("line1", "line2");
        lines81.addLines(Arrays.asList(null, "", "line3"));
        Assertions.assertThat(lines81.getLines()).containsExactlyInOrder("line1", "line2", "", "line3");

        Lines lines82 = new Lines(new Lines(), Arrays.asList("line1", "line2"));
        Assertions.assertThat(lines82.getLines()).containsExactlyInOrder("line1", "line2");
        lines82.addLines((Collection<String>) null);
        Assertions.assertThat(lines82.getLines()).containsExactlyInOrder("line1", "line2");
        lines82.addLines(Arrays.asList(null, "", "line3"));
        Assertions.assertThat(lines82.getLines()).containsExactlyInOrder("line1", "line2", "", "line3");

        Lines lines83 = new Lines(new Lines("base"), Arrays.asList("line1", "line2"));
        Assertions.assertThat(lines83.getLines()).containsExactlyInOrder("base", "line1", "line2");
        lines83.addLines((Collection<String>) null);
        Assertions.assertThat(lines83.getLines()).containsExactlyInOrder("base", "line1", "line2");
        lines83.addLines(Arrays.asList(null, "", "line3"));
        Assertions.assertThat(lines83.getLines()).containsExactlyInOrder("base", "line1", "line2", "", "line3");
    }

    /**
     * {@link Lines} class test.
     */
    @Test
    public void getLinesTest() {
        Lines lines1 = new Lines();
        Assertions.assertThat(lines1.getLines()).containsExactlyInOrder("");

        Lines lines2 = new Lines((String) null);
        Assertions.assertThat(lines2.getLines()).containsExactlyInOrder("");

        Lines lines3 = new Lines((String[]) null);
        Assertions.assertThat(lines3.getLines()).containsExactlyInOrder("");

        Lines lines4 = new Lines((Collection<String>) null);
        Assertions.assertThat(lines4.getLines()).containsExactlyInOrder("");

        Lines lines5 = new Lines();
        lines5.addLine("line1");
        lines5.addLines("line2", "line3", "line4");
        lines5.addLines(Arrays.asList("line5", "line6"));
        List<String> lines5List = lines5.getLines();
        Assertions.assertThat(lines5.getLines()).containsExactlyInOrder("line1", "line2", "line3", "line4", "line5", "line6");
        Assertions.assertThat(lines5List).containsExactlyInOrder("line1", "line2", "line3", "line4", "line5", "line6");

        lines5.addLine("line7");
        Assertions.assertThat(lines5.getLines()).containsExactlyInOrder("line1", "line2", "line3", "line4", "line5", "line6", "line7");
        Assertions.assertThat(lines5List).containsExactlyInOrder("line1", "line2", "line3", "line4", "line5", "line6");

        lines5List.add("line8");
        lines5List.add("line9");
        Assertions.assertThat(lines5.getLines()).containsExactlyInOrder("line1", "line2", "line3", "line4", "line5", "line6", "line7");
        Assertions.assertThat(lines5List).containsExactlyInOrder("line1", "line2", "line3", "line4", "line5", "line6", "line8", "line9");
    }

    /**
     * {@link Lines} class test.
     */
    @Test
    public void toStringTest() {
        Lines lines1 = new Lines();
        Assertions.assertThat(lines1.getLines()).hasToString("[]");

        Lines lines2 = new Lines((String) null);
        Assertions.assertThat(lines2.getLines()).hasToString("[]");

        Lines lines3 = new Lines((String[]) null);
        Assertions.assertThat(lines3.getLines()).hasToString("[]");

        Lines lines4 = new Lines((Collection<String>) null);
        Assertions.assertThat(lines4.getLines()).hasToString("[]");

        Lines lines5 = new Lines();
        lines5.addLine("line1");
        Assertions.assertThat(lines5.getLines()).hasToString("[line1]");

        lines5.addLines("line2", "line3", "line4");
        Assertions.assertThat(lines5.getLines()).hasToString("[line1, line2, line3, line4]");

        lines5.addLines(Arrays.asList("line5", "line6"));
        Assertions.assertThat(lines5.getLines()).hasToString("[line1, line2, line3, line4, line5, line6]");

        lines5.addLine("line7");
        Assertions.assertThat(lines5.getLines()).hasToString("[line1, line2, line3, line4, line5, line6, line7]");
    }

}
