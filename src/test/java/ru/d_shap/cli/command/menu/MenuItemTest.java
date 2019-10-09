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

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import org.junit.Test;

import ru.d_shap.assertions.Assertions;
import ru.d_shap.cli.BaseCliTest;
import ru.d_shap.cli.Command;
import ru.d_shap.cli.CommandRunner;
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
        Assertions.assertThat(new MenuItem(null, new Lines("line 1", "line 2")).getLabel().getLines()).containsExactlyInOrder("line 1", "line 2");

        Assertions.assertThat(new MenuItem(null, (String) null, null).getLabel().getLines()).containsExactlyInOrder("");
        Assertions.assertThat(new MenuItem(null, "", null).getLabel().getLines()).containsExactlyInOrder("");
        Assertions.assertThat(new MenuItem(null, " ", null).getLabel().getLines()).containsExactlyInOrder(" ");
        Assertions.assertThat(new MenuItem(null, "label", null).getLabel().getLines()).containsExactlyInOrder("label");

        Assertions.assertThat(new MenuItem(null, (Lines) null, null).getLabel().getLines()).containsExactlyInOrder("");
        Assertions.assertThat(new MenuItem(null, new Lines(), null).getLabel().getLines()).containsExactlyInOrder("");
        Assertions.assertThat(new MenuItem(null, new Lines(""), null).getLabel().getLines()).containsExactlyInOrder("");
        Assertions.assertThat(new MenuItem(null, new Lines("line"), null).getLabel().getLines()).containsExactlyInOrder("line");
        Assertions.assertThat(new MenuItem(null, new Lines("line 1", "line 2"), null).getLabel().getLines()).containsExactlyInOrder("line 1", "line 2");
    }

    /**
     * {@link MenuItem} class test.
     */
    @Test
    public void printTest() {
        ByteArrayOutputStream os011 = createOutputStream();
        InputStream is011 = createInputStream();
        CommandRunner commandRunner011 = new CommandRunner(os011, is011);
        MenuItem menuItem011 = new MenuItem(null, (String) null);
        menuItem011.print(commandRunner011.getWriter(), 1, false);
        commandRunner011.getWriter().flush();
        Assertions.assertThat(getLines(os011)).containsExactlyInOrder(" : ");

        ByteArrayOutputStream os012 = createOutputStream();
        InputStream is012 = createInputStream();
        CommandRunner commandRunner012 = new CommandRunner(os012, is012);
        MenuItem menuItem012 = new MenuItem(null, (String) null);
        menuItem012.print(commandRunner012.getWriter(), 1, true);
        commandRunner012.getWriter().flush();
        Assertions.assertThat(getLines(os012)).containsExactlyInOrder("*: ");

        ByteArrayOutputStream os013 = createOutputStream();
        InputStream is013 = createInputStream();
        CommandRunner commandRunner013 = new CommandRunner(os013, is013);
        MenuItem menuItem013 = new MenuItem(null, (String) null);
        menuItem013.print(commandRunner013.getWriter(), 7, false);
        commandRunner013.getWriter().flush();
        Assertions.assertThat(getLines(os013)).containsExactlyInOrder("       : ");

        ByteArrayOutputStream os014 = createOutputStream();
        InputStream is014 = createInputStream();
        CommandRunner commandRunner014 = new CommandRunner(os014, is014);
        MenuItem menuItem014 = new MenuItem(null, (String) null);
        menuItem014.print(commandRunner014.getWriter(), 7, true);
        commandRunner014.getWriter().flush();
        Assertions.assertThat(getLines(os014)).containsExactlyInOrder("      *: ");

        ByteArrayOutputStream os021 = createOutputStream();
        InputStream is021 = createInputStream();
        CommandRunner commandRunner021 = new CommandRunner(os021, is021);
        MenuItem menuItem021 = new MenuItem("", (String) null);
        menuItem021.print(commandRunner021.getWriter(), 1, false);
        commandRunner021.getWriter().flush();
        Assertions.assertThat(getLines(os021)).containsExactlyInOrder(" : ");

        ByteArrayOutputStream os022 = createOutputStream();
        InputStream is022 = createInputStream();
        CommandRunner commandRunner022 = new CommandRunner(os022, is022);
        MenuItem menuItem022 = new MenuItem("", (String) null);
        menuItem022.print(commandRunner022.getWriter(), 1, true);
        commandRunner022.getWriter().flush();
        Assertions.assertThat(getLines(os022)).containsExactlyInOrder("*: ");

        ByteArrayOutputStream os023 = createOutputStream();
        InputStream is023 = createInputStream();
        CommandRunner commandRunner023 = new CommandRunner(os023, is023);
        MenuItem menuItem023 = new MenuItem("", (String) null);
        menuItem023.print(commandRunner023.getWriter(), 7, false);
        commandRunner023.getWriter().flush();
        Assertions.assertThat(getLines(os023)).containsExactlyInOrder("       : ");

        ByteArrayOutputStream os024 = createOutputStream();
        InputStream is024 = createInputStream();
        CommandRunner commandRunner024 = new CommandRunner(os024, is024);
        MenuItem menuItem024 = new MenuItem("", (String) null);
        menuItem024.print(commandRunner024.getWriter(), 7, true);
        commandRunner024.getWriter().flush();
        Assertions.assertThat(getLines(os024)).containsExactlyInOrder("      *: ");

        ByteArrayOutputStream os031 = createOutputStream();
        InputStream is031 = createInputStream();
        CommandRunner commandRunner031 = new CommandRunner(os031, is031);
        MenuItem menuItem031 = new MenuItem("s", (String) null);
        menuItem031.print(commandRunner031.getWriter(), 1, false);
        commandRunner031.getWriter().flush();
        Assertions.assertThat(getLines(os031)).containsExactlyInOrder("s: ");

        ByteArrayOutputStream os032 = createOutputStream();
        InputStream is032 = createInputStream();
        CommandRunner commandRunner032 = new CommandRunner(os032, is032);
        MenuItem menuItem032 = new MenuItem("s", (String) null);
        menuItem032.print(commandRunner032.getWriter(), 1, true);
        commandRunner032.getWriter().flush();
        Assertions.assertThat(getLines(os032)).containsExactlyInOrder("*s: ");

        ByteArrayOutputStream os033 = createOutputStream();
        InputStream is033 = createInputStream();
        CommandRunner commandRunner033 = new CommandRunner(os033, is033);
        MenuItem menuItem033 = new MenuItem("s", (String) null);
        menuItem033.print(commandRunner033.getWriter(), 7, false);
        commandRunner033.getWriter().flush();
        Assertions.assertThat(getLines(os033)).containsExactlyInOrder("      s: ");

        ByteArrayOutputStream os034 = createOutputStream();
        InputStream is034 = createInputStream();
        CommandRunner commandRunner034 = new CommandRunner(os034, is034);
        MenuItem menuItem034 = new MenuItem("s", (String) null);
        menuItem034.print(commandRunner034.getWriter(), 7, true);
        commandRunner034.getWriter().flush();
        Assertions.assertThat(getLines(os034)).containsExactlyInOrder("     *s: ");

        ByteArrayOutputStream os041 = createOutputStream();
        InputStream is041 = createInputStream();
        CommandRunner commandRunner041 = new CommandRunner(os041, is041);
        MenuItem menuItem041 = new MenuItem("symbol", (String) null);
        menuItem041.print(commandRunner041.getWriter(), 1, false);
        commandRunner041.getWriter().flush();
        Assertions.assertThat(getLines(os041)).containsExactlyInOrder("symbol: ");

        ByteArrayOutputStream os042 = createOutputStream();
        InputStream is042 = createInputStream();
        CommandRunner commandRunner042 = new CommandRunner(os042, is042);
        MenuItem menuItem042 = new MenuItem("symbol", (String) null);
        menuItem042.print(commandRunner042.getWriter(), 1, true);
        commandRunner042.getWriter().flush();
        Assertions.assertThat(getLines(os042)).containsExactlyInOrder("*symbol: ");

        ByteArrayOutputStream os043 = createOutputStream();
        InputStream is043 = createInputStream();
        CommandRunner commandRunner043 = new CommandRunner(os043, is043);
        MenuItem menuItem043 = new MenuItem("symbol", (String) null);
        menuItem043.print(commandRunner043.getWriter(), 7, false);
        commandRunner043.getWriter().flush();
        Assertions.assertThat(getLines(os043)).containsExactlyInOrder(" symbol: ");

        ByteArrayOutputStream os044 = createOutputStream();
        InputStream is044 = createInputStream();
        CommandRunner commandRunner044 = new CommandRunner(os044, is044);
        MenuItem menuItem044 = new MenuItem("symbol", (String) null);
        menuItem044.print(commandRunner044.getWriter(), 7, true);
        commandRunner044.getWriter().flush();
        Assertions.assertThat(getLines(os044)).containsExactlyInOrder("*symbol: ");

        ByteArrayOutputStream os051 = createOutputStream();
        InputStream is051 = createInputStream();
        CommandRunner commandRunner051 = new CommandRunner(os051, is051);
        MenuItem menuItem051 = new MenuItem("s", "");
        menuItem051.print(commandRunner051.getWriter(), 1, false);
        commandRunner051.getWriter().flush();
        Assertions.assertThat(getLines(os051)).containsExactlyInOrder("s: ");

        ByteArrayOutputStream os052 = createOutputStream();
        InputStream is052 = createInputStream();
        CommandRunner commandRunner052 = new CommandRunner(os052, is052);
        MenuItem menuItem052 = new MenuItem("s", "");
        menuItem052.print(commandRunner052.getWriter(), 1, true);
        commandRunner052.getWriter().flush();
        Assertions.assertThat(getLines(os052)).containsExactlyInOrder("*s: ");

        ByteArrayOutputStream os053 = createOutputStream();
        InputStream is053 = createInputStream();
        CommandRunner commandRunner053 = new CommandRunner(os053, is053);
        MenuItem menuItem053 = new MenuItem("s", "");
        menuItem053.print(commandRunner053.getWriter(), 7, false);
        commandRunner053.getWriter().flush();
        Assertions.assertThat(getLines(os053)).containsExactlyInOrder("      s: ");

        ByteArrayOutputStream os054 = createOutputStream();
        InputStream is054 = createInputStream();
        CommandRunner commandRunner054 = new CommandRunner(os054, is054);
        MenuItem menuItem054 = new MenuItem("s", "");
        menuItem054.print(commandRunner054.getWriter(), 7, true);
        commandRunner054.getWriter().flush();
        Assertions.assertThat(getLines(os054)).containsExactlyInOrder("     *s: ");

        ByteArrayOutputStream os061 = createOutputStream();
        InputStream is061 = createInputStream();
        CommandRunner commandRunner061 = new CommandRunner(os061, is061);
        MenuItem menuItem061 = new MenuItem("s", "label");
        menuItem061.print(commandRunner061.getWriter(), 1, false);
        commandRunner061.getWriter().flush();
        Assertions.assertThat(getLines(os061)).containsExactlyInOrder("s: label");

        ByteArrayOutputStream os062 = createOutputStream();
        InputStream is062 = createInputStream();
        CommandRunner commandRunner062 = new CommandRunner(os062, is062);
        MenuItem menuItem062 = new MenuItem("s", "label");
        menuItem062.print(commandRunner062.getWriter(), 1, true);
        commandRunner062.getWriter().flush();
        Assertions.assertThat(getLines(os062)).containsExactlyInOrder("*s: label");

        ByteArrayOutputStream os063 = createOutputStream();
        InputStream is063 = createInputStream();
        CommandRunner commandRunner063 = new CommandRunner(os063, is063);
        MenuItem menuItem063 = new MenuItem("s", "label");
        menuItem063.print(commandRunner063.getWriter(), 7, false);
        commandRunner063.getWriter().flush();
        Assertions.assertThat(getLines(os063)).containsExactlyInOrder("      s: label");

        ByteArrayOutputStream os064 = createOutputStream();
        InputStream is064 = createInputStream();
        CommandRunner commandRunner064 = new CommandRunner(os064, is064);
        MenuItem menuItem064 = new MenuItem("s", "label");
        menuItem064.print(commandRunner064.getWriter(), 7, true);
        commandRunner064.getWriter().flush();
        Assertions.assertThat(getLines(os064)).containsExactlyInOrder("     *s: label");

        ByteArrayOutputStream os071 = createOutputStream();
        InputStream is071 = createInputStream();
        CommandRunner commandRunner071 = new CommandRunner(os071, is071);
        MenuItem menuItem071 = new MenuItem("s", (Lines) null);
        menuItem071.print(commandRunner071.getWriter(), 1, false);
        commandRunner071.getWriter().flush();
        Assertions.assertThat(getLines(os071)).containsExactlyInOrder("s: ");

        ByteArrayOutputStream os072 = createOutputStream();
        InputStream is072 = createInputStream();
        CommandRunner commandRunner072 = new CommandRunner(os072, is072);
        MenuItem menuItem072 = new MenuItem("s", (Lines) null);
        menuItem072.print(commandRunner072.getWriter(), 1, true);
        commandRunner072.getWriter().flush();
        Assertions.assertThat(getLines(os072)).containsExactlyInOrder("*s: ");

        ByteArrayOutputStream os073 = createOutputStream();
        InputStream is073 = createInputStream();
        CommandRunner commandRunner073 = new CommandRunner(os073, is073);
        MenuItem menuItem073 = new MenuItem("s", (Lines) null);
        menuItem073.print(commandRunner073.getWriter(), 7, false);
        commandRunner073.getWriter().flush();
        Assertions.assertThat(getLines(os073)).containsExactlyInOrder("      s: ");

        ByteArrayOutputStream os074 = createOutputStream();
        InputStream is074 = createInputStream();
        CommandRunner commandRunner074 = new CommandRunner(os074, is074);
        MenuItem menuItem074 = new MenuItem("s", (Lines) null);
        menuItem074.print(commandRunner074.getWriter(), 7, true);
        commandRunner074.getWriter().flush();
        Assertions.assertThat(getLines(os074)).containsExactlyInOrder("     *s: ");

        ByteArrayOutputStream os081 = createOutputStream();
        InputStream is081 = createInputStream();
        CommandRunner commandRunner081 = new CommandRunner(os081, is081);
        MenuItem menuItem081 = new MenuItem("s", new Lines());
        menuItem081.print(commandRunner081.getWriter(), 1, false);
        commandRunner081.getWriter().flush();
        Assertions.assertThat(getLines(os081)).containsExactlyInOrder("s: ");

        ByteArrayOutputStream os082 = createOutputStream();
        InputStream is082 = createInputStream();
        CommandRunner commandRunner082 = new CommandRunner(os082, is082);
        MenuItem menuItem082 = new MenuItem("s", new Lines());
        menuItem082.print(commandRunner082.getWriter(), 1, true);
        commandRunner082.getWriter().flush();
        Assertions.assertThat(getLines(os082)).containsExactlyInOrder("*s: ");

        ByteArrayOutputStream os083 = createOutputStream();
        InputStream is083 = createInputStream();
        CommandRunner commandRunner083 = new CommandRunner(os083, is083);
        MenuItem menuItem083 = new MenuItem("s", new Lines());
        menuItem083.print(commandRunner083.getWriter(), 7, false);
        commandRunner083.getWriter().flush();
        Assertions.assertThat(getLines(os083)).containsExactlyInOrder("      s: ");

        ByteArrayOutputStream os084 = createOutputStream();
        InputStream is084 = createInputStream();
        CommandRunner commandRunner084 = new CommandRunner(os084, is084);
        MenuItem menuItem084 = new MenuItem("s", new Lines());
        menuItem084.print(commandRunner084.getWriter(), 7, true);
        commandRunner084.getWriter().flush();
        Assertions.assertThat(getLines(os084)).containsExactlyInOrder("     *s: ");

        ByteArrayOutputStream os091 = createOutputStream();
        InputStream is091 = createInputStream();
        CommandRunner commandRunner091 = new CommandRunner(os091, is091);
        MenuItem menuItem091 = new MenuItem("s", new Lines(""));
        menuItem091.print(commandRunner091.getWriter(), 1, false);
        commandRunner091.getWriter().flush();
        Assertions.assertThat(getLines(os091)).containsExactlyInOrder("s: ");

        ByteArrayOutputStream os092 = createOutputStream();
        InputStream is092 = createInputStream();
        CommandRunner commandRunner092 = new CommandRunner(os092, is092);
        MenuItem menuItem092 = new MenuItem("s", new Lines(""));
        menuItem092.print(commandRunner092.getWriter(), 1, true);
        commandRunner092.getWriter().flush();
        Assertions.assertThat(getLines(os092)).containsExactlyInOrder("*s: ");

        ByteArrayOutputStream os093 = createOutputStream();
        InputStream is093 = createInputStream();
        CommandRunner commandRunner093 = new CommandRunner(os093, is093);
        MenuItem menuItem093 = new MenuItem("s", new Lines(""));
        menuItem093.print(commandRunner093.getWriter(), 7, false);
        commandRunner093.getWriter().flush();
        Assertions.assertThat(getLines(os093)).containsExactlyInOrder("      s: ");

        ByteArrayOutputStream os094 = createOutputStream();
        InputStream is094 = createInputStream();
        CommandRunner commandRunner094 = new CommandRunner(os094, is094);
        MenuItem menuItem094 = new MenuItem("s", new Lines(""));
        menuItem094.print(commandRunner094.getWriter(), 7, true);
        commandRunner094.getWriter().flush();
        Assertions.assertThat(getLines(os094)).containsExactlyInOrder("     *s: ");

        ByteArrayOutputStream os101 = createOutputStream();
        InputStream is101 = createInputStream();
        CommandRunner commandRunner101 = new CommandRunner(os101, is101);
        MenuItem menuItem101 = new MenuItem("s", new Lines("line"));
        menuItem101.print(commandRunner101.getWriter(), 1, false);
        commandRunner101.getWriter().flush();
        Assertions.assertThat(getLines(os101)).containsExactlyInOrder("s: line");

        ByteArrayOutputStream os102 = createOutputStream();
        InputStream is102 = createInputStream();
        CommandRunner commandRunner102 = new CommandRunner(os102, is102);
        MenuItem menuItem102 = new MenuItem("s", new Lines("line"));
        menuItem102.print(commandRunner102.getWriter(), 1, true);
        commandRunner102.getWriter().flush();
        Assertions.assertThat(getLines(os102)).containsExactlyInOrder("*s: line");

        ByteArrayOutputStream os103 = createOutputStream();
        InputStream is103 = createInputStream();
        CommandRunner commandRunner103 = new CommandRunner(os103, is103);
        MenuItem menuItem103 = new MenuItem("s", new Lines("line"));
        menuItem103.print(commandRunner103.getWriter(), 7, false);
        commandRunner103.getWriter().flush();
        Assertions.assertThat(getLines(os103)).containsExactlyInOrder("      s: line");

        ByteArrayOutputStream os104 = createOutputStream();
        InputStream is104 = createInputStream();
        CommandRunner commandRunner104 = new CommandRunner(os104, is104);
        MenuItem menuItem104 = new MenuItem("s", new Lines("line"));
        menuItem104.print(commandRunner104.getWriter(), 7, true);
        commandRunner104.getWriter().flush();
        Assertions.assertThat(getLines(os104)).containsExactlyInOrder("     *s: line");

        ByteArrayOutputStream os111 = createOutputStream();
        InputStream is111 = createInputStream();
        CommandRunner commandRunner111 = new CommandRunner(os111, is111);
        MenuItem menuItem111 = new MenuItem("s", new Lines("line 1", "line 2"));
        menuItem111.print(commandRunner111.getWriter(), 1, false);
        commandRunner111.getWriter().flush();
        Assertions.assertThat(getLines(os111)).containsExactlyInOrder("s: line 1", "   line 2");

        ByteArrayOutputStream os112 = createOutputStream();
        InputStream is112 = createInputStream();
        CommandRunner commandRunner112 = new CommandRunner(os112, is112);
        MenuItem menuItem112 = new MenuItem("s", new Lines("line 1", "line 2"));
        menuItem112.print(commandRunner112.getWriter(), 1, true);
        commandRunner112.getWriter().flush();
        Assertions.assertThat(getLines(os112)).containsExactlyInOrder("*s: line 1", "   line 2");

        ByteArrayOutputStream os113 = createOutputStream();
        InputStream is113 = createInputStream();
        CommandRunner commandRunner113 = new CommandRunner(os113, is113);
        MenuItem menuItem113 = new MenuItem("s", new Lines("line 1", "line 2"));
        menuItem113.print(commandRunner113.getWriter(), 7, false);
        commandRunner113.getWriter().flush();
        Assertions.assertThat(getLines(os113)).containsExactlyInOrder("      s: line 1", "         line 2");

        ByteArrayOutputStream os114 = createOutputStream();
        InputStream is114 = createInputStream();
        CommandRunner commandRunner114 = new CommandRunner(os114, is114);
        MenuItem menuItem114 = new MenuItem("s", new Lines("line 1", "line 2"));
        menuItem114.print(commandRunner114.getWriter(), 7, true);
        commandRunner114.getWriter().flush();
        Assertions.assertThat(getLines(os114)).containsExactlyInOrder("     *s: line 1", "         line 2");
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

        Command command = new AbstractCommandImpl("message", null);

        Assertions.assertThat(new MenuItem(null, (String) null, null).getCommand()).isNull();
        Assertions.assertThat(new MenuItem(null, (String) null, command).getCommand()).isSameAs(command);

        Assertions.assertThat(new MenuItem(null, (Lines) null, null).getCommand()).isNull();
        Assertions.assertThat(new MenuItem(null, (Lines) null, command).getCommand()).isSameAs(command);
    }

}
