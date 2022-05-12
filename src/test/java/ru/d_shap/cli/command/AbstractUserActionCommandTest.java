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
package ru.d_shap.cli.command;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.junit.Test;

import ru.d_shap.assertions.Assertions;
import ru.d_shap.assertions.util.DataHelper;
import ru.d_shap.cli.BaseCliTest;
import ru.d_shap.cli.CliIOException;
import ru.d_shap.cli.Command;
import ru.d_shap.cli.CommandRunner;
import ru.d_shap.cli.data.ValueHolder;
import ru.d_shap.cli.data.ValueLoaderImpl;

/**
 * Tests for {@link AbstractUserActionCommand}.
 *
 * @author Dmitry Shapovalov
 */
public final class AbstractUserActionCommandTest extends BaseCliTest {

    /**
     * Test class constructor.
     */
    public AbstractUserActionCommandTest() {
        super();
    }

    /**
     * {@link AbstractUserActionCommand} class test.
     */
    @Test
    public void executeTest() {
        ByteArrayOutputStream os1 = createOutputStream();
        InputStream is1 = createInputStream("Строка 1", "", "Строка 2");
        CommandRunner commandRunner1 = new CommandRunner(os1, is1);
        Command command1 = new AbstractUserActionCommandImpl("Prompt 1", null);
        commandRunner1.execute(command1);
        Assertions.assertThat(getLines(os1)).containsExactlyInOrder("Prompt 1", "Input: Строка 1", "");

        ByteArrayOutputStream os2 = createOutputStream();
        InputStream is2 = createInputStream("Строка 1", "", "Строка 2");
        CommandRunner commandRunner2 = new CommandRunner(os2, is2);
        Command command2 = new AbstractUserActionCommandImpl(null, "Prompt 1", null);
        commandRunner2.execute(command2);
        Assertions.assertThat(getLines(os2)).containsExactlyInOrder("Prompt 1", "Input: Строка 1", "");

        ByteArrayOutputStream os3 = createOutputStream();
        InputStream is3 = createInputStream("Строка 1", "", "Строка 2");
        CommandRunner commandRunner3 = new CommandRunner(os3, is3);
        Command command33 = new AbstractUserActionCommandImpl("Prompt 3", null);
        Command command32 = new AbstractUserActionCommandImpl("Prompt 2", command33);
        Command command31 = new AbstractUserActionCommandImpl("Prompt 1", command32);
        commandRunner3.execute(command31);
        Assertions.assertThat(getLines(os3)).containsExactlyInOrder("Prompt 1", "Input: Строка 1", "", "Prompt 2", "Default input: ", "", "Prompt 3", "Input: Строка 2", "");

        ByteArrayOutputStream os4 = createOutputStream();
        InputStream is4 = createInputStream("Строка 1", "", "Строка 2");
        CommandRunner commandRunner4 = new CommandRunner(os4, is4);
        Command command43 = new AbstractUserActionCommandImpl(null, "Prompt 3", null);
        Command command42 = new AbstractUserActionCommandImpl(null, "Prompt 2", command43);
        Command command41 = new AbstractUserActionCommandImpl(null, "Prompt 1", command42);
        commandRunner4.execute(command41);
        Assertions.assertThat(getLines(os4)).containsExactlyInOrder("Prompt 1", "Input: Строка 1", "", "Prompt 2", "Default input: ", "", "Prompt 3", "Input: Строка 2", "");

        ByteArrayOutputStream os5 = createOutputStream();
        InputStream is5 = createInputStream("Строка 1", "", "Строка 2");
        CommandRunner commandRunner5 = new CommandRunner(os5, is5);
        Command parentCommand5 = new AbstractExecutionCommandImpl("message", null);
        Command command5 = new AbstractUserActionCommandImpl(parentCommand5, "Prompt 1", null);
        commandRunner5.execute(command5);
        Assertions.assertThat(getLines(os5)).containsExactlyInOrder("Prompt 1", "Input: Строка 1", "", "message");

        ByteArrayOutputStream os6 = createOutputStream();
        InputStream is6 = createInputStream("Строка 1", "", "Строка 2");
        CommandRunner commandRunner6 = new CommandRunner(os6, is6);
        Command parentCommand6 = new AbstractExecutionCommandImpl("message", null);
        Command command63 = new AbstractUserActionCommandImpl(parentCommand6, "Prompt 3", null);
        Command command62 = new AbstractUserActionCommandImpl(parentCommand6, "Prompt 2", command63);
        Command command61 = new AbstractUserActionCommandImpl(parentCommand6, "Prompt 1", command62);
        commandRunner6.execute(command61);
        Assertions.assertThat(getLines(os6)).containsExactlyInOrder("Prompt 1", "Input: Строка 1", "", "Prompt 2", "Default input: ", "", "Prompt 3", "Input: Строка 2", "", "message");
    }

    /**
     * {@link AbstractUserActionCommand} class test.
     */
    @Test
    public void executeFailTest() {
        try {
            ByteArrayOutputStream os1 = createOutputStream();
            InputStream is1 = DataHelper.createInputStreamBuilder().setReadException("read error").buildInputStream();
            CommandRunner commandRunner1 = new CommandRunner(os1, is1);
            Command command1 = new AbstractUserActionCommandImpl("Prompt", null);
            commandRunner1.execute(command1);
            Assertions.fail("AbstractUserActionCommand test fail");
        } catch (CliIOException ex) {
            Assertions.assertThat(ex).hasMessage("read error");
        }

        OutputStream os2 = DataHelper.createOutputStreamBuilder().setWriteException("write error").buildOutputStream();
        InputStream is2 = createInputStream();
        CommandRunner commandRunner2 = new CommandRunner(os2, is2);
        Command command2 = new AbstractUserActionCommandImpl("Prompt", null);
        commandRunner2.execute(command2);
        Assertions.assertThat(commandRunner2.getWriter().checkError()).isTrue();
    }

    /**
     * {@link AbstractUserActionCommand} class test.
     */
    @Test
    public void nullReadLineTest() {
        ByteArrayOutputStream os = createOutputStream();
        InputStream is = createInputStream();
        CommandRunner commandRunner = new CommandRunner(os, is);
        Command command = new AbstractUserActionCommandImpl("Prompt", null);
        command.execute(commandRunner.getWriter(), new NullReadLineBufferedReader());
        Assertions.assertThat(getLines(os)).containsExactlyInOrder("Prompt", "Default input: ", "");
    }

    /**
     * {@link AbstractUserActionCommand} class test.
     */
    @Test
    public void isDefaultInputTest() {
        AbstractUserActionCommand command = new AbstractUserActionCommandImpl("Prompt", null);
        Assertions.assertThat(command.isDefaultInput(null)).isFalse();
        Assertions.assertThat(command.isDefaultInput("")).isTrue();
        Assertions.assertThat(command.isDefaultInput(" ")).isFalse();
        Assertions.assertThat(command.isDefaultInput("Input")).isFalse();
    }

    /**
     * {@link AbstractUserActionCommand} class test.
     */
    @Test
    public void processWrongInputValueHolderTest() {
        AbstractUserActionCommand command = new AbstractUserActionCommandImpl("Prompt", null);

        ByteArrayOutputStream os11 = createOutputStream();
        InputStream is11 = createInputStream();
        CommandRunner commandRunner11 = new CommandRunner(os11, is11);
        Command result11 = command.processWrongInput((ValueHolder<String>) null, null, commandRunner11.getWriter());
        commandRunner11.getWriter().flush();
        Assertions.assertThat(result11).isSameAs(command);
        Assertions.assertThat(getLines(os11)).containsExactlyInOrder();

        ByteArrayOutputStream os12 = createOutputStream();
        InputStream is12 = createInputStream();
        CommandRunner commandRunner12 = new CommandRunner(os12, is12);
        Command result12 = command.processWrongInput((ValueHolder<String>) null, "", commandRunner12.getWriter());
        commandRunner12.getWriter().flush();
        Assertions.assertThat(result12).isSameAs(command);
        Assertions.assertThat(getLines(os12)).containsExactlyInOrder();

        ByteArrayOutputStream os13 = createOutputStream();
        InputStream is13 = createInputStream();
        CommandRunner commandRunner13 = new CommandRunner(os13, is13);
        Command result13 = command.processWrongInput((ValueHolder<String>) null, " ", commandRunner13.getWriter());
        commandRunner13.getWriter().flush();
        Assertions.assertThat(result13).isSameAs(command);
        Assertions.assertThat(getLines(os13)).containsExactlyInOrder();

        ByteArrayOutputStream os14 = createOutputStream();
        InputStream is14 = createInputStream();
        CommandRunner commandRunner14 = new CommandRunner(os14, is14);
        Command result14 = command.processWrongInput((ValueHolder<String>) null, "value", commandRunner14.getWriter());
        commandRunner14.getWriter().flush();
        Assertions.assertThat(result14).isSameAs(command);
        Assertions.assertThat(getLines(os14)).containsExactlyInOrder();

        ValueHolder<String> wrongMessage = new ValueHolder<>(new ValueLoaderImpl<>("message", true));

        ByteArrayOutputStream os21 = createOutputStream();
        InputStream is21 = createInputStream();
        CommandRunner commandRunner21 = new CommandRunner(os21, is21);
        Command result21 = command.processWrongInput(wrongMessage, null, commandRunner21.getWriter());
        commandRunner21.getWriter().flush();
        Assertions.assertThat(result21).isSameAs(command);
        Assertions.assertThat(getLines(os21)).containsExactlyInOrder("message");

        ByteArrayOutputStream os22 = createOutputStream();
        InputStream is22 = createInputStream();
        CommandRunner commandRunner22 = new CommandRunner(os22, is22);
        Command result22 = command.processWrongInput(wrongMessage, "", commandRunner22.getWriter());
        commandRunner22.getWriter().flush();
        Assertions.assertThat(result22).isSameAs(command);
        Assertions.assertThat(getLines(os22)).containsExactlyInOrder("message");

        ByteArrayOutputStream os23 = createOutputStream();
        InputStream is23 = createInputStream();
        CommandRunner commandRunner23 = new CommandRunner(os23, is23);
        Command result23 = command.processWrongInput(wrongMessage, " ", commandRunner23.getWriter());
        commandRunner23.getWriter().flush();
        Assertions.assertThat(result23).isSameAs(command);
        Assertions.assertThat(getLines(os23)).containsExactlyInOrder("message");

        ByteArrayOutputStream os24 = createOutputStream();
        InputStream is24 = createInputStream();
        CommandRunner commandRunner24 = new CommandRunner(os24, is24);
        Command result24 = command.processWrongInput(wrongMessage, "value", commandRunner24.getWriter());
        commandRunner24.getWriter().flush();
        Assertions.assertThat(result24).isSameAs(command);
        Assertions.assertThat(getLines(os24)).containsExactlyInOrder("message");

        ValueHolder<String> wrongMessageTemplate = new ValueHolder<>(new ValueLoaderImpl<>("message: %s", true));

        ByteArrayOutputStream os31 = createOutputStream();
        InputStream is31 = createInputStream();
        CommandRunner commandRunner31 = new CommandRunner(os31, is31);
        Command result31 = command.processWrongInput(wrongMessageTemplate, null, commandRunner31.getWriter());
        commandRunner31.getWriter().flush();
        Assertions.assertThat(result31).isSameAs(command);
        Assertions.assertThat(getLines(os31)).containsExactlyInOrder("message: null");

        ByteArrayOutputStream os32 = createOutputStream();
        InputStream is32 = createInputStream();
        CommandRunner commandRunner32 = new CommandRunner(os32, is32);
        Command result32 = command.processWrongInput(wrongMessageTemplate, "", commandRunner32.getWriter());
        commandRunner32.getWriter().flush();
        Assertions.assertThat(result32).isSameAs(command);
        Assertions.assertThat(getLines(os32)).containsExactlyInOrder("message: ");

        ByteArrayOutputStream os33 = createOutputStream();
        InputStream is33 = createInputStream();
        CommandRunner commandRunner33 = new CommandRunner(os33, is33);
        Command result33 = command.processWrongInput(wrongMessageTemplate, " ", commandRunner33.getWriter());
        commandRunner33.getWriter().flush();
        Assertions.assertThat(result33).isSameAs(command);
        Assertions.assertThat(getLines(os33)).containsExactlyInOrder("message:  ");

        ByteArrayOutputStream os34 = createOutputStream();
        InputStream is34 = createInputStream();
        CommandRunner commandRunner34 = new CommandRunner(os34, is34);
        Command result34 = command.processWrongInput(wrongMessageTemplate, "value", commandRunner34.getWriter());
        commandRunner34.getWriter().flush();
        Assertions.assertThat(result34).isSameAs(command);
        Assertions.assertThat(getLines(os34)).containsExactlyInOrder("message: value");
    }

    /**
     * {@link AbstractUserActionCommand} class test.
     */
    @Test
    public void processWrongInputStringTest() {
        AbstractUserActionCommand command = new AbstractUserActionCommandImpl("Prompt", null);

        ByteArrayOutputStream os11 = createOutputStream();
        InputStream is11 = createInputStream();
        CommandRunner commandRunner11 = new CommandRunner(os11, is11);
        Command result11 = command.processWrongInput((String) null, null, commandRunner11.getWriter());
        commandRunner11.getWriter().flush();
        Assertions.assertThat(result11).isSameAs(command);
        Assertions.assertThat(getLines(os11)).containsExactlyInOrder();

        ByteArrayOutputStream os12 = createOutputStream();
        InputStream is12 = createInputStream();
        CommandRunner commandRunner12 = new CommandRunner(os12, is12);
        Command result12 = command.processWrongInput((String) null, "", commandRunner12.getWriter());
        commandRunner12.getWriter().flush();
        Assertions.assertThat(result12).isSameAs(command);
        Assertions.assertThat(getLines(os12)).containsExactlyInOrder();

        ByteArrayOutputStream os13 = createOutputStream();
        InputStream is13 = createInputStream();
        CommandRunner commandRunner13 = new CommandRunner(os13, is13);
        Command result13 = command.processWrongInput((String) null, " ", commandRunner13.getWriter());
        commandRunner13.getWriter().flush();
        Assertions.assertThat(result13).isSameAs(command);
        Assertions.assertThat(getLines(os13)).containsExactlyInOrder();

        ByteArrayOutputStream os14 = createOutputStream();
        InputStream is14 = createInputStream();
        CommandRunner commandRunner14 = new CommandRunner(os14, is14);
        Command result14 = command.processWrongInput((String) null, "value", commandRunner14.getWriter());
        commandRunner14.getWriter().flush();
        Assertions.assertThat(result14).isSameAs(command);
        Assertions.assertThat(getLines(os14)).containsExactlyInOrder();

        String wrongMessage = "message";

        ByteArrayOutputStream os21 = createOutputStream();
        InputStream is21 = createInputStream();
        CommandRunner commandRunner21 = new CommandRunner(os21, is21);
        Command result21 = command.processWrongInput(wrongMessage, null, commandRunner21.getWriter());
        commandRunner21.getWriter().flush();
        Assertions.assertThat(result21).isSameAs(command);
        Assertions.assertThat(getLines(os21)).containsExactlyInOrder("message");

        ByteArrayOutputStream os22 = createOutputStream();
        InputStream is22 = createInputStream();
        CommandRunner commandRunner22 = new CommandRunner(os22, is22);
        Command result22 = command.processWrongInput(wrongMessage, "", commandRunner22.getWriter());
        commandRunner22.getWriter().flush();
        Assertions.assertThat(result22).isSameAs(command);
        Assertions.assertThat(getLines(os22)).containsExactlyInOrder("message");

        ByteArrayOutputStream os23 = createOutputStream();
        InputStream is23 = createInputStream();
        CommandRunner commandRunner23 = new CommandRunner(os23, is23);
        Command result23 = command.processWrongInput(wrongMessage, " ", commandRunner23.getWriter());
        commandRunner23.getWriter().flush();
        Assertions.assertThat(result23).isSameAs(command);
        Assertions.assertThat(getLines(os23)).containsExactlyInOrder("message");

        ByteArrayOutputStream os24 = createOutputStream();
        InputStream is24 = createInputStream();
        CommandRunner commandRunner24 = new CommandRunner(os24, is24);
        Command result24 = command.processWrongInput(wrongMessage, "value", commandRunner24.getWriter());
        commandRunner24.getWriter().flush();
        Assertions.assertThat(result24).isSameAs(command);
        Assertions.assertThat(getLines(os24)).containsExactlyInOrder("message");

        String wrongMessageTemplate = "message: %s";

        ByteArrayOutputStream os31 = createOutputStream();
        InputStream is31 = createInputStream();
        CommandRunner commandRunner31 = new CommandRunner(os31, is31);
        Command result31 = command.processWrongInput(wrongMessageTemplate, null, commandRunner31.getWriter());
        commandRunner31.getWriter().flush();
        Assertions.assertThat(result31).isSameAs(command);
        Assertions.assertThat(getLines(os31)).containsExactlyInOrder("message: null");

        ByteArrayOutputStream os32 = createOutputStream();
        InputStream is32 = createInputStream();
        CommandRunner commandRunner32 = new CommandRunner(os32, is32);
        Command result32 = command.processWrongInput(wrongMessageTemplate, "", commandRunner32.getWriter());
        commandRunner32.getWriter().flush();
        Assertions.assertThat(result32).isSameAs(command);
        Assertions.assertThat(getLines(os32)).containsExactlyInOrder("message: ");

        ByteArrayOutputStream os33 = createOutputStream();
        InputStream is33 = createInputStream();
        CommandRunner commandRunner33 = new CommandRunner(os33, is33);
        Command result33 = command.processWrongInput(wrongMessageTemplate, " ", commandRunner33.getWriter());
        commandRunner33.getWriter().flush();
        Assertions.assertThat(result33).isSameAs(command);
        Assertions.assertThat(getLines(os33)).containsExactlyInOrder("message:  ");

        ByteArrayOutputStream os34 = createOutputStream();
        InputStream is34 = createInputStream();
        CommandRunner commandRunner34 = new CommandRunner(os34, is34);
        Command result34 = command.processWrongInput(wrongMessageTemplate, "value", commandRunner34.getWriter());
        commandRunner34.getWriter().flush();
        Assertions.assertThat(result34).isSameAs(command);
        Assertions.assertThat(getLines(os34)).containsExactlyInOrder("message: value");
    }

}
