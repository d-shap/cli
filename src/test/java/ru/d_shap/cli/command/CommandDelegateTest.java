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

import org.junit.Test;

import ru.d_shap.assertions.Assertions;
import ru.d_shap.cli.BaseCliTest;
import ru.d_shap.cli.Command;
import ru.d_shap.cli.CommandRunner;
import ru.d_shap.cli.data.Context;
import ru.d_shap.cli.data.ValueHolder;
import ru.d_shap.cli.data.ValueLoadException;
import ru.d_shap.cli.data.ValueLoaderImpl;

/**
 * Tests for {@link CommandDelegate}.
 *
 * @author Dmitry Shapovalov
 */
public final class CommandDelegateTest extends BaseCliTest {

    /**
     * Test class constructor.
     */
    public CommandDelegateTest() {
        super();
    }

    /**
     * {@link CommandDelegate} class test.
     */
    @Test
    public void getCommandTest() {
        CommandDelegate commandDelegate1 = new CommandDelegate();
        Assertions.assertThat(commandDelegate1.getCommand()).isNull();

        CommandDelegate commandDelegate2 = new CommandDelegate(null);
        Assertions.assertThat(commandDelegate2.getCommand()).isNull();

        AbstractCommand abstractCommand3 = new AbstractCommandImpl(null, null);
        CommandDelegate commandDelegate3 = new CommandDelegate(abstractCommand3);
        Assertions.assertThat(commandDelegate3.getCommand()).isSameAs(abstractCommand3);
    }

    /**
     * {@link CommandDelegate} class test.
     */
    @Test
    public void setCommandTest() {
        CommandDelegate commandDelegate1 = new CommandDelegate();
        commandDelegate1.setCommand(null);
        Assertions.assertThat(commandDelegate1.getCommand()).isNull();

        CommandDelegate commandDelegate2 = new CommandDelegate();
        AbstractCommand abstractCommand21 = new AbstractCommandImpl(null, null);
        commandDelegate2.setCommand(abstractCommand21);
        Assertions.assertThat(commandDelegate2.getCommand()).isSameAs(abstractCommand21);
        AbstractCommand abstractCommand22 = new AbstractCommandImpl(null, null);
        commandDelegate2.setCommand(abstractCommand22);
        Assertions.assertThat(commandDelegate2.getCommand()).isSameAs(abstractCommand22);
    }

    /**
     * {@link CommandDelegate} class test.
     */
    @Test
    public void hasParentCommandTest() {
        CommandDelegate commandDelegate1 = new CommandDelegate();
        Assertions.assertThat(commandDelegate1.hasParentCommand()).isFalse();

        CommandDelegate commandDelegate2 = new CommandDelegate(null);
        Assertions.assertThat(commandDelegate2.hasParentCommand()).isFalse();

        AbstractCommand abstractCommand3 = new AbstractCommandImpl(null, null);
        CommandDelegate commandDelegate3 = new CommandDelegate(abstractCommand3);
        Assertions.assertThat(commandDelegate3.hasParentCommand()).isFalse();

        AbstractCommand abstractCommand4 = new AbstractCommandImpl(null, null, null);
        CommandDelegate commandDelegate4 = new CommandDelegate(abstractCommand4);
        Assertions.assertThat(commandDelegate4.hasParentCommand()).isFalse();

        AbstractCommand abstractCommand51 = new AbstractCommandImpl(null, null, null);
        AbstractCommand abstractCommand52 = new AbstractCommandImpl(abstractCommand51, null, null);
        CommandDelegate commandDelegate5 = new CommandDelegate(abstractCommand52);
        Assertions.assertThat(commandDelegate5.hasParentCommand()).isTrue();
    }

    /**
     * {@link CommandDelegate} class test.
     */
    @Test
    public void hasParentCommandWithClassTest() {
        CommandDelegate commandDelegate1 = new CommandDelegate();
        Assertions.assertThat(commandDelegate1.hasParentCommand(Command.class)).isFalse();
        Assertions.assertThat(commandDelegate1.hasParentCommand(AbstractCommand.class)).isFalse();
        Assertions.assertThat(commandDelegate1.hasParentCommand(AbstractCommandImpl.class)).isFalse();
        Assertions.assertThat(commandDelegate1.hasParentCommand(AbstractExecutionCommand.class)).isFalse();
        Assertions.assertThat(commandDelegate1.hasParentCommand(AbstractUserActionCommand.class)).isFalse();

        CommandDelegate commandDelegate2 = new CommandDelegate(null);
        Assertions.assertThat(commandDelegate2.hasParentCommand(Command.class)).isFalse();
        Assertions.assertThat(commandDelegate2.hasParentCommand(AbstractCommand.class)).isFalse();
        Assertions.assertThat(commandDelegate2.hasParentCommand(AbstractCommandImpl.class)).isFalse();
        Assertions.assertThat(commandDelegate2.hasParentCommand(AbstractExecutionCommand.class)).isFalse();
        Assertions.assertThat(commandDelegate2.hasParentCommand(AbstractUserActionCommand.class)).isFalse();

        AbstractCommand abstractCommand3 = new AbstractCommandImpl(null, null);
        CommandDelegate commandDelegate3 = new CommandDelegate(abstractCommand3);
        Assertions.assertThat(commandDelegate3.hasParentCommand(Command.class)).isFalse();
        Assertions.assertThat(commandDelegate3.hasParentCommand(AbstractCommand.class)).isFalse();
        Assertions.assertThat(commandDelegate3.hasParentCommand(AbstractCommandImpl.class)).isFalse();
        Assertions.assertThat(commandDelegate3.hasParentCommand(AbstractExecutionCommand.class)).isFalse();
        Assertions.assertThat(commandDelegate3.hasParentCommand(AbstractUserActionCommand.class)).isFalse();

        AbstractCommand abstractCommand4 = new AbstractCommandImpl(null, null, null);
        CommandDelegate commandDelegate4 = new CommandDelegate(abstractCommand4);
        Assertions.assertThat(commandDelegate4.hasParentCommand(Command.class)).isFalse();
        Assertions.assertThat(commandDelegate4.hasParentCommand(AbstractCommand.class)).isFalse();
        Assertions.assertThat(commandDelegate4.hasParentCommand(AbstractCommandImpl.class)).isFalse();
        Assertions.assertThat(commandDelegate4.hasParentCommand(AbstractExecutionCommand.class)).isFalse();
        Assertions.assertThat(commandDelegate4.hasParentCommand(AbstractUserActionCommand.class)).isFalse();

        AbstractCommand abstractCommand51 = new AbstractCommandImpl(null, null, null);
        AbstractCommand abstractCommand52 = new AbstractCommandImpl(abstractCommand51, null, null);
        CommandDelegate commandDelegate5 = new CommandDelegate(abstractCommand52);
        Assertions.assertThat(commandDelegate5.hasParentCommand(Command.class)).isTrue();
        Assertions.assertThat(commandDelegate5.hasParentCommand(AbstractCommand.class)).isTrue();
        Assertions.assertThat(commandDelegate5.hasParentCommand(AbstractCommandImpl.class)).isTrue();
        Assertions.assertThat(commandDelegate5.hasParentCommand(AbstractExecutionCommand.class)).isFalse();
        Assertions.assertThat(commandDelegate5.hasParentCommand(AbstractUserActionCommand.class)).isFalse();

        AbstractCommand abstractCommand61 = new AbstractExecutionCommandImpl(null, null);
        AbstractCommand abstractCommand62 = new AbstractCommandImpl(abstractCommand61, null, null);
        AbstractCommand abstractCommand63 = new AbstractCommandImpl(abstractCommand62, null, null);
        CommandDelegate commandDelegate6 = new CommandDelegate(abstractCommand63);
        Assertions.assertThat(commandDelegate6.hasParentCommand(Command.class)).isTrue();
        Assertions.assertThat(commandDelegate6.hasParentCommand(AbstractCommand.class)).isTrue();
        Assertions.assertThat(commandDelegate6.hasParentCommand(AbstractCommandImpl.class)).isTrue();
        Assertions.assertThat(commandDelegate6.hasParentCommand(AbstractExecutionCommand.class)).isTrue();
        Assertions.assertThat(commandDelegate6.hasParentCommand(AbstractUserActionCommand.class)).isFalse();
    }

    /**
     * {@link CommandDelegate} class test.
     */
    @Test
    public void getParentCommandTest() {
        CommandDelegate commandDelegate1 = new CommandDelegate();
        Assertions.assertThat(commandDelegate1.getParentCommand()).isNull();

        CommandDelegate commandDelegate2 = new CommandDelegate(null);
        Assertions.assertThat(commandDelegate2.getParentCommand()).isNull();

        AbstractCommand abstractCommand3 = new AbstractCommandImpl(null, null);
        CommandDelegate commandDelegate3 = new CommandDelegate(abstractCommand3);
        Assertions.assertThat(commandDelegate3.getParentCommand()).isNull();

        AbstractCommand abstractCommand4 = new AbstractCommandImpl(null, null, null);
        CommandDelegate commandDelegate4 = new CommandDelegate(abstractCommand4);
        Assertions.assertThat(commandDelegate4.getParentCommand()).isNull();

        AbstractCommand abstractCommand51 = new AbstractCommandImpl(null, null, null);
        AbstractCommand abstractCommand52 = new AbstractCommandImpl(abstractCommand51, null, null);
        CommandDelegate commandDelegate5 = new CommandDelegate(abstractCommand52);
        Assertions.assertThat(commandDelegate5.getParentCommand()).isSameAs(abstractCommand51);
    }

    /**
     * {@link CommandDelegate} class test.
     */
    @Test
    public void getParentCommandWithClassTest() {
        CommandDelegate commandDelegate1 = new CommandDelegate();
        Assertions.assertThat(commandDelegate1.getParentCommand(Command.class)).isNull();
        Assertions.assertThat(commandDelegate1.getParentCommand(AbstractCommand.class)).isNull();
        Assertions.assertThat(commandDelegate1.getParentCommand(AbstractCommandImpl.class)).isNull();
        Assertions.assertThat(commandDelegate1.getParentCommand(AbstractExecutionCommand.class)).isNull();
        Assertions.assertThat(commandDelegate1.getParentCommand(AbstractUserActionCommand.class)).isNull();

        CommandDelegate commandDelegate2 = new CommandDelegate(null);
        Assertions.assertThat(commandDelegate2.getParentCommand(Command.class)).isNull();
        Assertions.assertThat(commandDelegate2.getParentCommand(AbstractCommand.class)).isNull();
        Assertions.assertThat(commandDelegate2.getParentCommand(AbstractCommandImpl.class)).isNull();
        Assertions.assertThat(commandDelegate2.getParentCommand(AbstractExecutionCommand.class)).isNull();
        Assertions.assertThat(commandDelegate2.getParentCommand(AbstractUserActionCommand.class)).isNull();

        AbstractCommand abstractCommand3 = new AbstractCommandImpl(null, null);
        CommandDelegate commandDelegate3 = new CommandDelegate(abstractCommand3);
        Assertions.assertThat(commandDelegate3.getParentCommand(Command.class)).isNull();
        Assertions.assertThat(commandDelegate3.getParentCommand(AbstractCommand.class)).isNull();
        Assertions.assertThat(commandDelegate3.getParentCommand(AbstractCommandImpl.class)).isNull();
        Assertions.assertThat(commandDelegate3.getParentCommand(AbstractExecutionCommand.class)).isNull();
        Assertions.assertThat(commandDelegate3.getParentCommand(AbstractUserActionCommand.class)).isNull();

        AbstractCommand abstractCommand4 = new AbstractCommandImpl(null, null, null);
        CommandDelegate commandDelegate4 = new CommandDelegate(abstractCommand4);
        Assertions.assertThat(commandDelegate4.getParentCommand(Command.class)).isNull();
        Assertions.assertThat(commandDelegate4.getParentCommand(AbstractCommand.class)).isNull();
        Assertions.assertThat(commandDelegate4.getParentCommand(AbstractCommandImpl.class)).isNull();
        Assertions.assertThat(commandDelegate4.getParentCommand(AbstractExecutionCommand.class)).isNull();
        Assertions.assertThat(commandDelegate4.getParentCommand(AbstractUserActionCommand.class)).isNull();

        AbstractCommand abstractCommand51 = new AbstractCommandImpl(null, null, null);
        AbstractCommand abstractCommand52 = new AbstractCommandImpl(abstractCommand51, null, null);
        CommandDelegate commandDelegate5 = new CommandDelegate(abstractCommand52);
        Assertions.assertThat(commandDelegate5.getParentCommand(Command.class)).isSameAs(abstractCommand51);
        Assertions.assertThat(commandDelegate5.getParentCommand(AbstractCommand.class)).isSameAs(abstractCommand51);
        Assertions.assertThat(commandDelegate5.getParentCommand(AbstractCommandImpl.class)).isSameAs(abstractCommand51);
        Assertions.assertThat(commandDelegate5.getParentCommand(AbstractExecutionCommand.class)).isNull();
        Assertions.assertThat(commandDelegate5.getParentCommand(AbstractUserActionCommand.class)).isNull();

        AbstractCommand abstractCommand61 = new AbstractExecutionCommandImpl(null, null);
        AbstractCommand abstractCommand62 = new AbstractCommandImpl(abstractCommand61, null, null);
        AbstractCommand abstractCommand63 = new AbstractCommandImpl(abstractCommand62, null, null);
        CommandDelegate commandDelegate6 = new CommandDelegate(abstractCommand63);
        Assertions.assertThat(commandDelegate6.getParentCommand(Command.class)).isSameAs(abstractCommand62);
        Assertions.assertThat(commandDelegate6.getParentCommand(AbstractCommand.class)).isSameAs(abstractCommand62);
        Assertions.assertThat(commandDelegate6.getParentCommand(AbstractCommandImpl.class)).isSameAs(abstractCommand62);
        Assertions.assertThat(commandDelegate6.getParentCommand(AbstractExecutionCommand.class)).isSameAs(abstractCommand61);
        Assertions.assertThat(commandDelegate6.getParentCommand(AbstractUserActionCommand.class)).isNull();
    }

    /**
     * {@link CommandDelegate} class test.
     */
    @Test
    public void getContextTest() {
        CommandDelegate commandDelegate1 = new CommandDelegate();
        Assertions.assertThat(commandDelegate1.getContext()).isNull();

        CommandDelegate commandDelegate2 = new CommandDelegate(null);
        Assertions.assertThat(commandDelegate2.getContext()).isNull();

        AbstractCommand abstractCommand3 = new AbstractCommandImpl(null, null);
        CommandDelegate commandDelegate3 = new CommandDelegate(abstractCommand3);
        Assertions.assertThat(commandDelegate3.getContext()).isNull();

        AbstractCommand abstractCommand4 = new AbstractCommandImpl(null, null, null);
        CommandDelegate commandDelegate4 = new CommandDelegate(abstractCommand4);
        Assertions.assertThat(commandDelegate4.getContext()).isNull();

        AbstractCommand abstractCommand51 = new AbstractCommandImpl(null, null, null);
        AbstractCommand abstractCommand52 = new AbstractCommandImpl(abstractCommand51, null, null);
        CommandDelegate commandDelegate5 = new CommandDelegate(abstractCommand52);
        Assertions.assertThat(commandDelegate5.getContext()).isNull();
    }

    /**
     * {@link CommandDelegate} class test.
     */
    @Test
    public void setContextTest() {
        CommandDelegate commandDelegate1 = new CommandDelegate();
        Context context1 = new Context();
        commandDelegate1.setContext(context1);
        Assertions.assertThat(commandDelegate1.getContext()).isNull();

        CommandDelegate commandDelegate2 = new CommandDelegate(null);
        Context context2 = new Context();
        commandDelegate2.setContext(context2);
        Assertions.assertThat(commandDelegate2.getContext()).isNull();

        AbstractCommand abstractCommand3 = new AbstractCommandImpl(null, null);
        CommandDelegate commandDelegate3 = new CommandDelegate(abstractCommand3);
        Context context3 = new Context();
        commandDelegate3.setContext(context3);
        Assertions.assertThat(commandDelegate3.getContext()).isSameAs(context3);

        AbstractCommand abstractCommand4 = new AbstractCommandImpl(null, null, null);
        CommandDelegate commandDelegate4 = new CommandDelegate(abstractCommand4);
        Context context4 = new Context();
        commandDelegate4.setContext(context4);
        Assertions.assertThat(commandDelegate4.getContext()).isSameAs(context4);

        AbstractCommand abstractCommand51 = new AbstractCommandImpl(null, null, null);
        Context context5 = new Context();
        abstractCommand51.setContext(context5);
        AbstractCommand abstractCommand52 = new AbstractCommandImpl(abstractCommand51, null, null);
        CommandDelegate commandDelegate5 = new CommandDelegate(abstractCommand52);
        Assertions.assertThat(commandDelegate5.getContext()).isNull();

        AbstractCommand abstractCommand61 = new AbstractCommandImpl(null, null, null);
        AbstractCommand abstractCommand62 = new AbstractCommandImpl(abstractCommand61, null, null);
        CommandDelegate commandDelegate6 = new CommandDelegate(abstractCommand62);
        Context context6 = new Context();
        commandDelegate6.setContext(context6);
        Assertions.assertThat(commandDelegate6.getContext()).isSameAs(context6);
    }

    /**
     * {@link CommandDelegate} class test.
     */
    @Test
    public void getCommandRunnerTest() {
        CommandDelegate commandDelegate1 = new CommandDelegate();
        Assertions.assertThat(commandDelegate1.getCommandRunner()).isNull();

        CommandDelegate commandDelegate2 = new CommandDelegate(null);
        Assertions.assertThat(commandDelegate2.getCommandRunner()).isNull();

        AbstractCommand abstractCommand3 = new AbstractCommandImpl(null, null);
        CommandDelegate commandDelegate3 = new CommandDelegate(abstractCommand3);
        Assertions.assertThat(commandDelegate3.getCommandRunner()).isNull();

        AbstractCommand abstractCommand4 = new AbstractCommandImpl(null, null, null);
        CommandDelegate commandDelegate4 = new CommandDelegate(abstractCommand4);
        Assertions.assertThat(commandDelegate4.getCommandRunner()).isNull();

        AbstractCommand abstractCommand51 = new AbstractCommandImpl(null, null, null);
        AbstractCommand abstractCommand52 = new AbstractCommandImpl(abstractCommand51, null, null);
        CommandDelegate commandDelegate5 = new CommandDelegate(abstractCommand52);
        Assertions.assertThat(commandDelegate5.getCommandRunner()).isNull();
    }

    /**
     * {@link CommandDelegate} class test.
     */
    @Test
    public void setCommandRunnerTest() {
        CommandDelegate commandDelegate1 = new CommandDelegate();
        CommandRunner commandRunner1 = new CommandRunner(System.out, System.in);
        commandDelegate1.setCommandRunner(commandRunner1);
        Assertions.assertThat(commandDelegate1.getCommandRunner()).isNull();

        CommandDelegate commandDelegate2 = new CommandDelegate(null);
        CommandRunner commandRunner2 = new CommandRunner(System.out, System.in);
        commandDelegate2.setCommandRunner(commandRunner2);
        Assertions.assertThat(commandDelegate2.getCommandRunner()).isNull();

        AbstractCommand abstractCommand3 = new AbstractCommandImpl(null, null);
        CommandDelegate commandDelegate3 = new CommandDelegate(abstractCommand3);
        CommandRunner commandRunner3 = new CommandRunner(System.out, System.in);
        commandDelegate3.setCommandRunner(commandRunner3);
        Assertions.assertThat(commandDelegate3.getCommandRunner()).isSameAs(commandRunner3);

        AbstractCommand abstractCommand4 = new AbstractCommandImpl(null, null, null);
        CommandDelegate commandDelegate4 = new CommandDelegate(abstractCommand4);
        CommandRunner commandRunner4 = new CommandRunner(System.out, System.in);
        commandDelegate4.setCommandRunner(commandRunner4);
        Assertions.assertThat(commandDelegate4.getCommandRunner()).isSameAs(commandRunner4);

        AbstractCommand abstractCommand51 = new AbstractCommandImpl(null, null, null);
        CommandRunner commandRunner5 = new CommandRunner(System.out, System.in);
        abstractCommand51.setCommandRunner(commandRunner5);
        AbstractCommand abstractCommand52 = new AbstractCommandImpl(abstractCommand51, null, null);
        CommandDelegate commandDelegate5 = new CommandDelegate(abstractCommand52);
        Assertions.assertThat(commandDelegate5.getCommandRunner()).isNull();

        AbstractCommand abstractCommand61 = new AbstractCommandImpl(null, null, null);
        AbstractCommand abstractCommand62 = new AbstractCommandImpl(abstractCommand61, null, null);
        CommandDelegate commandDelegate6 = new CommandDelegate(abstractCommand62);
        CommandRunner commandRunner6 = new CommandRunner(System.out, System.in);
        commandDelegate6.setCommandRunner(commandRunner6);
        Assertions.assertThat(commandDelegate6.getCommandRunner()).isSameAs(commandRunner6);
    }

    /**
     * {@link CommandDelegate} class test.
     */
    @Test
    public void executeTest() {
        ByteArrayOutputStream os01 = createOutputStream();
        InputStream is01 = createInputStream("Строка 1", "Строка 2");
        CommandRunner commandRunner01 = new CommandRunner(os01, is01);
        CommandDelegate commandDelegate01 = new CommandDelegate();
        commandRunner01.execute(commandDelegate01);
        Assertions.assertThat(getLines(os01)).containsExactlyInOrder();

        ByteArrayOutputStream os02 = createOutputStream();
        InputStream is02 = createInputStream("Строка 1", "Строка 2");
        CommandRunner commandRunner02 = new CommandRunner(os02, is02);
        CommandDelegate commandDelegate02 = new CommandDelegate(null);
        commandRunner02.execute(commandDelegate02);
        Assertions.assertThat(getLines(os02)).containsExactlyInOrder();

        ByteArrayOutputStream os03 = createOutputStream();
        InputStream is03 = createInputStream("Строка 1", "Строка 2");
        CommandRunner commandRunner03 = new CommandRunner(os03, is03);
        Command command03 = new AbstractCommandImpl("Prompt 1", null);
        CommandDelegate commandDelegate03 = new CommandDelegate(command03);
        commandRunner03.execute(commandDelegate03);
        Assertions.assertThat(getLines(os03)).containsExactlyInOrder("Prompt 1", "Строка 1");

        ByteArrayOutputStream os04 = createOutputStream();
        InputStream is04 = createInputStream("Строка 1", "Строка 2");
        CommandRunner commandRunner04 = new CommandRunner(os04, is04);
        Command command043 = new AbstractCommandImpl("Prompt 3", null);
        Command command042 = new AbstractCommandImpl("Prompt 2", command043);
        Command command041 = new AbstractCommandImpl("Prompt 1", command042);
        CommandDelegate commandDelegate04 = new CommandDelegate(command041);
        commandRunner04.execute(commandDelegate04);
        Assertions.assertThat(getLines(os04)).containsExactlyInOrder("Prompt 1", "Строка 1", "Prompt 2", "Строка 2", "Prompt 3");

        ByteArrayOutputStream os05 = createOutputStream();
        InputStream is05 = createInputStream("Строка 1", "Строка 2");
        CommandRunner commandRunner05 = new CommandRunner(os05, is05);
        Command command05 = new AbstractCommandImpl(null, "Prompt 1", null);
        CommandDelegate commandDelegate05 = new CommandDelegate(command05);
        commandRunner05.execute(commandDelegate05);
        Assertions.assertThat(getLines(os05)).containsExactlyInOrder("Prompt 1", "Строка 1");

        ByteArrayOutputStream os06 = createOutputStream();
        InputStream is06 = createInputStream("Строка 1", "Строка 2");
        CommandRunner commandRunner06 = new CommandRunner(os06, is06);
        Command command063 = new AbstractCommandImpl(null, "Prompt 3", null);
        Command command062 = new AbstractCommandImpl(null, "Prompt 2", command063);
        Command command061 = new AbstractCommandImpl(null, "Prompt 1", command062);
        CommandDelegate commandDelegate06 = new CommandDelegate(command061);
        commandRunner06.execute(commandDelegate06);
        Assertions.assertThat(getLines(os06)).containsExactlyInOrder("Prompt 1", "Строка 1", "Prompt 2", "Строка 2", "Prompt 3");

        ByteArrayOutputStream os07 = createOutputStream();
        InputStream is07 = createInputStream("Строка 1", "Строка 2");
        CommandRunner commandRunner07 = new CommandRunner(os07, is07);
        Command parentCommand07 = new AbstractExecutionCommandImpl("message", null);
        Command command07 = new AbstractCommandImpl(parentCommand07, "Prompt 1", null);
        CommandDelegate commandDelegate07 = new CommandDelegate(command07);
        commandRunner07.execute(commandDelegate07);
        Assertions.assertThat(getLines(os07)).containsExactlyInOrder("Prompt 1", "Строка 1", "message");

        ByteArrayOutputStream os08 = createOutputStream();
        InputStream is08 = createInputStream("Строка 1", "Строка 2");
        CommandRunner commandRunner08 = new CommandRunner(os08, is08);
        Command parentCommand08 = new AbstractExecutionCommandImpl("message", null);
        Command command083 = new AbstractCommandImpl(parentCommand08, "Prompt 3", null);
        Command command082 = new AbstractCommandImpl(parentCommand08, "Prompt 2", command083);
        Command command081 = new AbstractCommandImpl(parentCommand08, "Prompt 1", command082);
        CommandDelegate commandDelegate08 = new CommandDelegate(command081);
        commandRunner08.execute(commandDelegate08);
        Assertions.assertThat(getLines(os08)).containsExactlyInOrder("Prompt 1", "Строка 1", "Prompt 2", "Строка 2", "Prompt 3", "message");
    }

    /**
     * {@link CommandDelegate} class test.
     */
    @Test
    public void resetTest() {
        AbstractCommand abstractCommand = new AbstractCommandImpl(null, null, null);

        CommandDelegate commandDelegate1 = new CommandDelegate();
        ValueLoaderImpl<String> valueLoader1 = new ValueLoaderImpl<>("value", true);
        ValueHolder<String> valueHolder1 = abstractCommand.createValueHolder(valueLoader1);
        Assertions.assertThat(valueHolder1.getValue()).isEqualTo("value");
        Assertions.assertThat(valueHolder1.getValue()).isEqualTo("value");
        commandDelegate1.reset();
        Assertions.assertThat(valueHolder1.getValue()).isEqualTo("value");
        Assertions.assertThat(valueHolder1.getValue()).isEqualTo("value");
        commandDelegate1.setCommand(abstractCommand);
        commandDelegate1.reset();
        try {
            valueHolder1.getValue();
            Assertions.fail("AbstractCommand test fail");
        } catch (ValueLoadException ex) {
            Assertions.assertThat(ex).hasMessage("Not first call!");
        }

        CommandDelegate commandDelegate2 = new CommandDelegate();
        ValueLoaderImpl<String> valueLoader2 = new ValueLoaderImpl<>("value", false);
        ValueHolder<String> valueHolder2 = abstractCommand.createValueHolder(valueLoader2);
        Assertions.assertThat(valueHolder2.getValue()).isEqualTo("value");
        Assertions.assertThat(valueHolder2.getValue()).isEqualTo("value");
        commandDelegate2.reset();
        Assertions.assertThat(valueHolder2.getValue()).isEqualTo("value");
        Assertions.assertThat(valueHolder2.getValue()).isEqualTo("value");
        commandDelegate2.setCommand(abstractCommand);
        commandDelegate2.reset();
        Assertions.assertThat(valueHolder2.getValue()).isEqualTo("value");
        Assertions.assertThat(valueHolder2.getValue()).isEqualTo("value");
    }

}
