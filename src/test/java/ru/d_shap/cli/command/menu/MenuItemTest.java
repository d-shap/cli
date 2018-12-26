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
package ru.d_shap.cli.command.menu;

import org.junit.Test;

import ru.d_shap.assertions.Assertions;
import ru.d_shap.cli.BaseCliTest;
import ru.d_shap.cli.Command;
import ru.d_shap.cli.command.AbstractCommandImpl;
import ru.d_shap.cli.data.Lines;

/**
 * Tests for {@link MenuItem}.
 *
 * @author Dmitry Shapovalov
 */
public final class MenuItemTest extends BaseCliTest {

    /**
     * Test class constructor.
     */
    public MenuItemTest() {
        super();
    }

    /**
     * {@link MenuItem} class test.
     */
    @Test
    public void getSymbolTest() {
        Assertions.assertThat(new MenuItem(null, (String) null).getSymbol()).isNull();
        Assertions.assertThat(new MenuItem("", (String) null).getSymbol()).isEqualTo("");
        Assertions.assertThat(new MenuItem(" ", (String) null).getSymbol()).isEqualTo(" ");
        Assertions.assertThat(new MenuItem("s", (String) null).getSymbol()).isEqualTo("s");
        Assertions.assertThat(new MenuItem("symbol", (String) null).getSymbol()).isEqualTo("symbol");

        Assertions.assertThat(new MenuItem(null, (Lines) null).getSymbol()).isNull();
        Assertions.assertThat(new MenuItem("", (Lines) null).getSymbol()).isEqualTo("");
        Assertions.assertThat(new MenuItem(" ", (Lines) null).getSymbol()).isEqualTo(" ");
        Assertions.assertThat(new MenuItem("s", (Lines) null).getSymbol()).isEqualTo("s");
        Assertions.assertThat(new MenuItem("symbol", (Lines) null).getSymbol()).isEqualTo("symbol");

        Assertions.assertThat(new MenuItem(null, (String) null, null).getSymbol()).isNull();
        Assertions.assertThat(new MenuItem("", (String) null, null).getSymbol()).isEqualTo("");
        Assertions.assertThat(new MenuItem(" ", (String) null, null).getSymbol()).isEqualTo(" ");
        Assertions.assertThat(new MenuItem("s", (String) null, null).getSymbol()).isEqualTo("s");
        Assertions.assertThat(new MenuItem("symbol", (String) null, null).getSymbol()).isEqualTo("symbol");

        Assertions.assertThat(new MenuItem(null, (Lines) null, null).getSymbol()).isNull();
        Assertions.assertThat(new MenuItem("", (Lines) null, null).getSymbol()).isEqualTo("");
        Assertions.assertThat(new MenuItem(" ", (Lines) null, null).getSymbol()).isEqualTo(" ");
        Assertions.assertThat(new MenuItem("s", (Lines) null, null).getSymbol()).isEqualTo("s");
        Assertions.assertThat(new MenuItem("symbol", (Lines) null, null).getSymbol()).isEqualTo("symbol");
    }

    /**
     * {@link MenuItem} class test.
     */
    @Test
    public void getLabelTest() {
        Assertions.assertThat(new MenuItem(null, (String) null).getLabel().getLines()).containsExactlyInOrder("");
        Assertions.assertThat(new MenuItem(null, "").getLabel().getLines()).containsExactlyInOrder("");
        Assertions.assertThat(new MenuItem(null, " ").getLabel().getLines()).containsExactlyInOrder(" ");
        Assertions.assertThat(new MenuItem(null, "label").getLabel().getLines()).containsExactlyInOrder("label");

        Assertions.assertThat(new MenuItem(null, (Lines) null).getLabel().getLines()).containsExactlyInOrder("");
        Assertions.assertThat(new MenuItem(null, new Lines()).getLabel().getLines()).containsExactlyInOrder("");
        Assertions.assertThat(new MenuItem(null, new Lines("")).getLabel().getLines()).containsExactlyInOrder("");
        Assertions.assertThat(new MenuItem(null, new Lines("line")).getLabel().getLines()).containsExactlyInOrder("line");
        Assertions.assertThat(new MenuItem(null, new Lines("line1", "line2")).getLabel().getLines()).containsExactlyInOrder("line1", "line2");

        Assertions.assertThat(new MenuItem(null, (String) null, null).getLabel().getLines()).containsExactlyInOrder("");
        Assertions.assertThat(new MenuItem(null, "", null).getLabel().getLines()).containsExactlyInOrder("");
        Assertions.assertThat(new MenuItem(null, " ", null).getLabel().getLines()).containsExactlyInOrder(" ");
        Assertions.assertThat(new MenuItem(null, "label", null).getLabel().getLines()).containsExactlyInOrder("label");

        Assertions.assertThat(new MenuItem(null, (Lines) null, null).getLabel().getLines()).containsExactlyInOrder("");
        Assertions.assertThat(new MenuItem(null, new Lines(), null).getLabel().getLines()).containsExactlyInOrder("");
        Assertions.assertThat(new MenuItem(null, new Lines(""), null).getLabel().getLines()).containsExactlyInOrder("");
        Assertions.assertThat(new MenuItem(null, new Lines("line"), null).getLabel().getLines()).containsExactlyInOrder("line");
        Assertions.assertThat(new MenuItem(null, new Lines("line1", "line2"), null).getLabel().getLines()).containsExactlyInOrder("line1", "line2");
    }

    /**
     * {@link MenuItem} class test.
     */
    @Test
    public void printTest() {

    }

    /**
     * {@link MenuItem} class test.
     */
    @Test
    public void isSelectedTest() {
        Assertions.assertThat(new MenuItem(null, (String) null).isSelected(null)).isFalse();
        Assertions.assertThat(new MenuItem(null, (String) null).isSelected("")).isFalse();
        Assertions.assertThat(new MenuItem(null, (String) null).isSelected(" ")).isFalse();
        Assertions.assertThat(new MenuItem(null, (String) null).isSelected("s")).isFalse();
        Assertions.assertThat(new MenuItem(null, (String) null).isSelected("S")).isFalse();
        Assertions.assertThat(new MenuItem(null, (String) null).isSelected("symbol")).isFalse();
        Assertions.assertThat(new MenuItem(null, (String) null).isSelected("sYmBOl")).isFalse();

        Assertions.assertThat(new MenuItem("", (String) null).isSelected(null)).isFalse();
        Assertions.assertThat(new MenuItem("", (String) null).isSelected("")).isTrue();
        Assertions.assertThat(new MenuItem("", (String) null).isSelected(" ")).isFalse();
        Assertions.assertThat(new MenuItem("", (String) null).isSelected("s")).isFalse();
        Assertions.assertThat(new MenuItem("", (String) null).isSelected("S")).isFalse();
        Assertions.assertThat(new MenuItem("", (String) null).isSelected("symbol")).isFalse();
        Assertions.assertThat(new MenuItem("", (String) null).isSelected("sYmBOl")).isFalse();

        Assertions.assertThat(new MenuItem(" ", (String) null).isSelected(null)).isFalse();
        Assertions.assertThat(new MenuItem(" ", (String) null).isSelected("")).isFalse();
        Assertions.assertThat(new MenuItem(" ", (String) null).isSelected(" ")).isTrue();
        Assertions.assertThat(new MenuItem(" ", (String) null).isSelected("s")).isFalse();
        Assertions.assertThat(new MenuItem(" ", (String) null).isSelected("S")).isFalse();
        Assertions.assertThat(new MenuItem(" ", (String) null).isSelected("symbol")).isFalse();
        Assertions.assertThat(new MenuItem(" ", (String) null).isSelected("sYmBOl")).isFalse();

        Assertions.assertThat(new MenuItem("s", (String) null).isSelected(null)).isFalse();
        Assertions.assertThat(new MenuItem("s", (String) null).isSelected("")).isFalse();
        Assertions.assertThat(new MenuItem("s", (String) null).isSelected(" ")).isFalse();
        Assertions.assertThat(new MenuItem("s", (String) null).isSelected("s")).isTrue();
        Assertions.assertThat(new MenuItem("s", (String) null).isSelected("S")).isTrue();
        Assertions.assertThat(new MenuItem("s", (String) null).isSelected("symbol")).isFalse();
        Assertions.assertThat(new MenuItem("s", (String) null).isSelected("sYmBOl")).isFalse();

        Assertions.assertThat(new MenuItem("symbol", (String) null).isSelected(null)).isFalse();
        Assertions.assertThat(new MenuItem("symbol", (String) null).isSelected("")).isFalse();
        Assertions.assertThat(new MenuItem("symbol", (String) null).isSelected(" ")).isFalse();
        Assertions.assertThat(new MenuItem("symbol", (String) null).isSelected("s")).isFalse();
        Assertions.assertThat(new MenuItem("symbol", (String) null).isSelected("S")).isFalse();
        Assertions.assertThat(new MenuItem("symbol", (String) null).isSelected("symbol")).isTrue();
        Assertions.assertThat(new MenuItem("symbol", (String) null).isSelected("sYmBOl")).isTrue();
    }

    /**
     * {@link MenuItem} class test.
     */
    @Test
    public void getCommandTest() {
        Assertions.assertThat(new MenuItem(null, (String) null).getCommand()).isNull();

        Assertions.assertThat(new MenuItem(null, (Lines) null).getCommand()).isNull();

        Command command = new AbstractCommandImpl("message");

        Assertions.assertThat(new MenuItem(null, (String) null, null).getCommand()).isNull();
        Assertions.assertThat(new MenuItem(null, (String) null, command).getCommand()).isSameAs(command);

        Assertions.assertThat(new MenuItem(null, (Lines) null, null).getCommand()).isNull();
        Assertions.assertThat(new MenuItem(null, (Lines) null, command).getCommand()).isSameAs(command);
    }

}
