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
 * Tests for {@link AbstractCommand}.
 *
 * @author Dmitry Shapovalov
 */
public final class AbstractCommandTest extends BaseCliTest {

    /**
     * Test class constructor.
     */
    public AbstractCommandTest() {
        super();
    }

    /**
     * {@link AbstractCommand} class test.
     */
    @Test
    public void hasParentCommandTest() {
        AbstractCommand abstractCommand1 = new AbstractCommandImpl(null);
        Assertions.assertThat(abstractCommand1.hasParentCommand()).isFalse();

        AbstractCommand abstractCommand2 = new AbstractCommandImpl(null, null);
        Assertions.assertThat(abstractCommand2.hasParentCommand()).isFalse();

        AbstractCommand abstractCommand31 = new AbstractCommandImpl(null, null);
        AbstractCommand abstractCommand32 = new AbstractCommandImpl(null, abstractCommand31);
        Assertions.assertThat(abstractCommand32.hasParentCommand()).isTrue();
    }

    /**
     * {@link AbstractCommand} class test.
     */
    @Test
    public void hasParentCommandWithClassTest() {
        AbstractCommand abstractCommand1 = new AbstractCommandImpl(null);
        Assertions.assertThat(abstractCommand1.hasParentCommand(Command.class)).isFalse();
        Assertions.assertThat(abstractCommand1.hasParentCommand(AbstractCommand.class)).isFalse();
        Assertions.assertThat(abstractCommand1.hasParentCommand(AbstractCommandImpl.class)).isFalse();
        Assertions.assertThat(abstractCommand1.hasParentCommand(AbstractExecutionCommand.class)).isFalse();
        Assertions.assertThat(abstractCommand1.hasParentCommand(AbstractUserActionCommand.class)).isFalse();

        AbstractCommand abstractCommand2 = new AbstractCommandImpl(null, null);
        Assertions.assertThat(abstractCommand2.hasParentCommand(Command.class)).isFalse();
        Assertions.assertThat(abstractCommand2.hasParentCommand(AbstractCommand.class)).isFalse();
        Assertions.assertThat(abstractCommand2.hasParentCommand(AbstractCommandImpl.class)).isFalse();
        Assertions.assertThat(abstractCommand2.hasParentCommand(AbstractExecutionCommand.class)).isFalse();
        Assertions.assertThat(abstractCommand2.hasParentCommand(AbstractUserActionCommand.class)).isFalse();

        AbstractCommand abstractCommand31 = new AbstractCommandImpl(null, null);
        AbstractCommand abstractCommand32 = new AbstractCommandImpl(null, abstractCommand31);
        Assertions.assertThat(abstractCommand32.hasParentCommand(Command.class)).isTrue();
        Assertions.assertThat(abstractCommand32.hasParentCommand(AbstractCommand.class)).isTrue();
        Assertions.assertThat(abstractCommand32.hasParentCommand(AbstractCommandImpl.class)).isTrue();
        Assertions.assertThat(abstractCommand32.hasParentCommand(AbstractExecutionCommand.class)).isFalse();
        Assertions.assertThat(abstractCommand32.hasParentCommand(AbstractUserActionCommand.class)).isFalse();

        AbstractCommand abstractCommand41 = new AbstractExecutionCommandImpl(null);
        AbstractCommand abstractCommand42 = new AbstractCommandImpl(null, abstractCommand41);
        AbstractCommand abstractCommand43 = new AbstractCommandImpl(null, abstractCommand42);
        Assertions.assertThat(abstractCommand43.hasParentCommand(Command.class)).isTrue();
        Assertions.assertThat(abstractCommand43.hasParentCommand(AbstractCommand.class)).isTrue();
        Assertions.assertThat(abstractCommand43.hasParentCommand(AbstractCommandImpl.class)).isTrue();
        Assertions.assertThat(abstractCommand43.hasParentCommand(AbstractExecutionCommand.class)).isTrue();
        Assertions.assertThat(abstractCommand43.hasParentCommand(AbstractUserActionCommand.class)).isFalse();
    }

    /**
     * {@link AbstractCommand} class test.
     */
    @Test
    public void getParentCommandTest() {
        AbstractCommand abstractCommand1 = new AbstractCommandImpl(null);
        Assertions.assertThat(abstractCommand1.getParentCommand()).isNull();

        AbstractCommand abstractCommand2 = new AbstractCommandImpl(null, null);
        Assertions.assertThat(abstractCommand2.getParentCommand()).isNull();

        AbstractCommand abstractCommand31 = new AbstractCommandImpl(null, null);
        AbstractCommand abstractCommand32 = new AbstractCommandImpl(null, abstractCommand31);
        Assertions.assertThat(abstractCommand32.getParentCommand()).isSameAs(abstractCommand31);
    }

    /**
     * {@link AbstractCommand} class test.
     */
    @Test
    public void getParentCommandWithClassTest() {
        AbstractCommand abstractCommand1 = new AbstractCommandImpl(null);
        Assertions.assertThat(abstractCommand1.getParentCommand(Command.class)).isNull();
        Assertions.assertThat(abstractCommand1.getParentCommand(AbstractCommand.class)).isNull();
        Assertions.assertThat(abstractCommand1.getParentCommand(AbstractCommandImpl.class)).isNull();
        Assertions.assertThat(abstractCommand1.getParentCommand(AbstractExecutionCommand.class)).isNull();
        Assertions.assertThat(abstractCommand1.getParentCommand(AbstractUserActionCommand.class)).isNull();

        AbstractCommand abstractCommand2 = new AbstractCommandImpl(null, null);
        Assertions.assertThat(abstractCommand2.getParentCommand(Command.class)).isNull();
        Assertions.assertThat(abstractCommand2.getParentCommand(AbstractCommand.class)).isNull();
        Assertions.assertThat(abstractCommand2.getParentCommand(AbstractCommandImpl.class)).isNull();
        Assertions.assertThat(abstractCommand2.getParentCommand(AbstractExecutionCommand.class)).isNull();
        Assertions.assertThat(abstractCommand2.getParentCommand(AbstractUserActionCommand.class)).isNull();

        AbstractCommand abstractCommand31 = new AbstractCommandImpl(null, null);
        AbstractCommand abstractCommand32 = new AbstractCommandImpl(null, abstractCommand31);
        Assertions.assertThat(abstractCommand32.getParentCommand(Command.class)).isSameAs(abstractCommand31);
        Assertions.assertThat(abstractCommand32.getParentCommand(AbstractCommand.class)).isSameAs(abstractCommand31);
        Assertions.assertThat(abstractCommand32.getParentCommand(AbstractCommandImpl.class)).isSameAs(abstractCommand31);
        Assertions.assertThat(abstractCommand32.getParentCommand(AbstractExecutionCommand.class)).isNull();
        Assertions.assertThat(abstractCommand32.getParentCommand(AbstractUserActionCommand.class)).isNull();

        AbstractCommand abstractCommand41 = new AbstractExecutionCommandImpl(null);
        AbstractCommand abstractCommand42 = new AbstractCommandImpl(null, abstractCommand41);
        AbstractCommand abstractCommand43 = new AbstractCommandImpl(null, abstractCommand42);
        Assertions.assertThat(abstractCommand43.getParentCommand(Command.class)).isSameAs(abstractCommand42);
        Assertions.assertThat(abstractCommand43.getParentCommand(AbstractCommand.class)).isSameAs(abstractCommand42);
        Assertions.assertThat(abstractCommand43.getParentCommand(AbstractCommandImpl.class)).isSameAs(abstractCommand42);
        Assertions.assertThat(abstractCommand43.getParentCommand(AbstractExecutionCommand.class)).isSameAs(abstractCommand41);
        Assertions.assertThat(abstractCommand43.getParentCommand(AbstractUserActionCommand.class)).isNull();
    }

    /**
     * {@link AbstractCommand} class test.
     */
    @Test
    public void getContextTest() {
        AbstractCommand abstractCommand1 = new AbstractCommandImpl(null);
        Assertions.assertThat(abstractCommand1.getContext()).isNull();

        AbstractCommand abstractCommand2 = new AbstractCommandImpl(null, null);
        Assertions.assertThat(abstractCommand2.getContext()).isNull();

        AbstractCommand abstractCommand31 = new AbstractCommandImpl(null, null);
        AbstractCommand abstractCommand32 = new AbstractCommandImpl(null, abstractCommand31);
        Assertions.assertThat(abstractCommand32.getContext()).isNull();
    }

    /**
     * {@link AbstractCommand} class test.
     */
    @Test
    public void setContextTest() {
        AbstractCommand abstractCommand1 = new AbstractCommandImpl(null);
        Context context1 = new Context();
        abstractCommand1.setContext(context1);
        Assertions.assertThat(abstractCommand1.getContext()).isSameAs(context1);

        AbstractCommand abstractCommand2 = new AbstractCommandImpl(null, null);
        Context context2 = new Context();
        abstractCommand2.setContext(context2);
        Assertions.assertThat(abstractCommand2.getContext()).isSameAs(context2);

        AbstractCommand abstractCommand31 = new AbstractCommandImpl(null, null);
        Context context3 = new Context();
        abstractCommand31.setContext(context3);
        AbstractCommand abstractCommand32 = new AbstractCommandImpl(null, abstractCommand31);
        Assertions.assertThat(abstractCommand32.getContext()).isNull();

        AbstractCommand abstractCommand41 = new AbstractCommandImpl(null, null);
        AbstractCommand abstractCommand42 = new AbstractCommandImpl(null, abstractCommand41);
        Context context4 = new Context();
        abstractCommand42.setContext(context4);
        Assertions.assertThat(abstractCommand42.getContext()).isSameAs(context4);
    }

    /**
     * {@link AbstractCommand} class test.
     */
    @Test
    public void getCommandRunnerTest() {
        AbstractCommand abstractCommand1 = new AbstractCommandImpl(null);
        Assertions.assertThat(abstractCommand1.getCommandRunner()).isNull();

        AbstractCommand abstractCommand2 = new AbstractCommandImpl(null, null);
        Assertions.assertThat(abstractCommand2.getCommandRunner()).isNull();

        AbstractCommand abstractCommand31 = new AbstractCommandImpl(null, null);
        AbstractCommand abstractCommand32 = new AbstractCommandImpl(null, abstractCommand31);
        Assertions.assertThat(abstractCommand32.getCommandRunner()).isNull();
    }

    /**
     * {@link AbstractCommand} class test.
     */
    @Test
    public void setCommandRunnerTest() {
        AbstractCommand abstractCommand1 = new AbstractCommandImpl(null);
        CommandRunner commandRunner1 = new CommandRunner(System.out, System.in);
        abstractCommand1.setCommandRunner(commandRunner1);
        Assertions.assertThat(abstractCommand1.getCommandRunner()).isSameAs(commandRunner1);

        AbstractCommand abstractCommand2 = new AbstractCommandImpl(null, null);
        CommandRunner commandRunner2 = new CommandRunner(System.out, System.in);
        abstractCommand2.setCommandRunner(commandRunner2);
        Assertions.assertThat(abstractCommand2.getCommandRunner()).isSameAs(commandRunner2);

        AbstractCommand abstractCommand31 = new AbstractCommandImpl(null, null);
        CommandRunner commandRunner3 = new CommandRunner(System.out, System.in);
        abstractCommand31.setCommandRunner(commandRunner3);
        AbstractCommand abstractCommand32 = new AbstractCommandImpl(null, abstractCommand31);
        Assertions.assertThat(abstractCommand32.getCommandRunner()).isNull();

        AbstractCommand abstractCommand41 = new AbstractCommandImpl(null, null);
        AbstractCommand abstractCommand42 = new AbstractCommandImpl(null, abstractCommand41);
        CommandRunner commandRunner4 = new CommandRunner(System.out, System.in);
        abstractCommand42.setCommandRunner(commandRunner4);
        Assertions.assertThat(abstractCommand42.getCommandRunner()).isSameAs(commandRunner4);
    }

    /**
     * {@link AbstractCommand} class test.
     */
    @Test
    public void createValueHolderTest() {
        AbstractCommand abstractCommand = new AbstractCommandImpl(null, null);

        ValueLoaderImpl<String> valueLoader1 = new ValueLoaderImpl<>("value", true);
        ValueHolder<String> valueHolder1 = abstractCommand.createValueHolder(valueLoader1);
        Assertions.assertThat(valueHolder1.getValue()).isEqualTo("value");
        Assertions.assertThat(valueHolder1.getValue()).isEqualTo("value");

        ValueLoaderImpl<Integer> valueLoader2 = new ValueLoaderImpl<>(10, true);
        ValueHolder<Integer> valueHolder2 = abstractCommand.createValueHolder(valueLoader2);
        Assertions.assertThat(valueHolder2.getValue()).isEqualTo(10);
        Assertions.assertThat(valueHolder2.getValue()).isEqualTo(10);
    }

    /**
     * {@link AbstractCommand} class test.
     */
    @Test
    public void resetTest() {
        AbstractCommand abstractCommand = new AbstractCommandImpl(null, null);

        ValueLoaderImpl<String> valueLoader1 = new ValueLoaderImpl<>("value", true);
        ValueHolder<String> valueHolder1 = abstractCommand.createValueHolder(valueLoader1);
        Assertions.assertThat(valueHolder1.getValue()).isEqualTo("value");
        Assertions.assertThat(valueHolder1.getValue()).isEqualTo("value");
        abstractCommand.reset();
        try {
            valueHolder1.getValue();
            Assertions.fail("AbstractCommand test fail");
        } catch (ValueLoadException ex) {
            Assertions.assertThat(ex).hasMessage("Not first call!");
        }

        ValueLoaderImpl<String> valueLoader2 = new ValueLoaderImpl<>("value", false);
        ValueHolder<String> valueHolder2 = abstractCommand.createValueHolder(valueLoader2);
        Assertions.assertThat(valueHolder2.getValue()).isEqualTo("value");
        Assertions.assertThat(valueHolder2.getValue()).isEqualTo("value");
        abstractCommand.reset();
        Assertions.assertThat(valueHolder2.getValue()).isEqualTo("value");
        Assertions.assertThat(valueHolder2.getValue()).isEqualTo("value");
    }

}
