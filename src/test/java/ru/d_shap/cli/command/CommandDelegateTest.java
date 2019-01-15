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
        // TODO
    }

    /**
     * {@link CommandDelegate} class test.
     */
    @Test
    public void setCommandTest() {
        // TODO
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

        AbstractCommand abstractCommand3 = new AbstractCommandImpl(null);
        CommandDelegate commandDelegate3 = new CommandDelegate(abstractCommand3);
        Assertions.assertThat(commandDelegate3.hasParentCommand()).isFalse();

        AbstractCommand abstractCommand4 = new AbstractCommandImpl(null, null);
        CommandDelegate commandDelegate4 = new CommandDelegate(abstractCommand4);
        Assertions.assertThat(commandDelegate4.hasParentCommand()).isFalse();

        AbstractCommand abstractCommand51 = new AbstractCommandImpl(null, null);
        AbstractCommand abstractCommand52 = new AbstractCommandImpl(null, abstractCommand51);
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

        AbstractCommand abstractCommand3 = new AbstractCommandImpl(null);
        CommandDelegate commandDelegate3 = new CommandDelegate(abstractCommand3);
        Assertions.assertThat(commandDelegate3.hasParentCommand(Command.class)).isFalse();
        Assertions.assertThat(commandDelegate3.hasParentCommand(AbstractCommand.class)).isFalse();
        Assertions.assertThat(commandDelegate3.hasParentCommand(AbstractCommandImpl.class)).isFalse();
        Assertions.assertThat(commandDelegate3.hasParentCommand(AbstractExecutionCommand.class)).isFalse();
        Assertions.assertThat(commandDelegate3.hasParentCommand(AbstractUserActionCommand.class)).isFalse();

        AbstractCommand abstractCommand4 = new AbstractCommandImpl(null, null);
        CommandDelegate commandDelegate4 = new CommandDelegate(abstractCommand4);
        Assertions.assertThat(commandDelegate4.hasParentCommand(Command.class)).isFalse();
        Assertions.assertThat(commandDelegate4.hasParentCommand(AbstractCommand.class)).isFalse();
        Assertions.assertThat(commandDelegate4.hasParentCommand(AbstractCommandImpl.class)).isFalse();
        Assertions.assertThat(commandDelegate4.hasParentCommand(AbstractExecutionCommand.class)).isFalse();
        Assertions.assertThat(commandDelegate4.hasParentCommand(AbstractUserActionCommand.class)).isFalse();

        AbstractCommand abstractCommand51 = new AbstractCommandImpl(null, null);
        AbstractCommand abstractCommand52 = new AbstractCommandImpl(null, abstractCommand51);
        CommandDelegate commandDelegate5 = new CommandDelegate(abstractCommand52);
        Assertions.assertThat(commandDelegate5.hasParentCommand(Command.class)).isTrue();
        Assertions.assertThat(commandDelegate5.hasParentCommand(AbstractCommand.class)).isTrue();
        Assertions.assertThat(commandDelegate5.hasParentCommand(AbstractCommandImpl.class)).isTrue();
        Assertions.assertThat(commandDelegate5.hasParentCommand(AbstractExecutionCommand.class)).isFalse();
        Assertions.assertThat(commandDelegate5.hasParentCommand(AbstractUserActionCommand.class)).isFalse();

        AbstractCommand abstractCommand61 = new AbstractExecutionCommandImpl(null);
        AbstractCommand abstractCommand62 = new AbstractCommandImpl(null, abstractCommand61);
        AbstractCommand abstractCommand63 = new AbstractCommandImpl(null, abstractCommand62);
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

        AbstractCommand abstractCommand3 = new AbstractCommandImpl(null);
        CommandDelegate commandDelegate3 = new CommandDelegate(abstractCommand3);
        Assertions.assertThat(commandDelegate3.getParentCommand()).isNull();

        AbstractCommand abstractCommand4 = new AbstractCommandImpl(null, null);
        CommandDelegate commandDelegate4 = new CommandDelegate(abstractCommand4);
        Assertions.assertThat(commandDelegate4.getParentCommand()).isNull();

        AbstractCommand abstractCommand51 = new AbstractCommandImpl(null, null);
        AbstractCommand abstractCommand52 = new AbstractCommandImpl(null, abstractCommand51);
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

        AbstractCommand abstractCommand3 = new AbstractCommandImpl(null);
        CommandDelegate commandDelegate3 = new CommandDelegate(abstractCommand3);
        Assertions.assertThat(commandDelegate3.getParentCommand(Command.class)).isNull();
        Assertions.assertThat(commandDelegate3.getParentCommand(AbstractCommand.class)).isNull();
        Assertions.assertThat(commandDelegate3.getParentCommand(AbstractCommandImpl.class)).isNull();
        Assertions.assertThat(commandDelegate3.getParentCommand(AbstractExecutionCommand.class)).isNull();
        Assertions.assertThat(commandDelegate3.getParentCommand(AbstractUserActionCommand.class)).isNull();

        AbstractCommand abstractCommand4 = new AbstractCommandImpl(null, null);
        CommandDelegate commandDelegate4 = new CommandDelegate(abstractCommand4);
        Assertions.assertThat(commandDelegate4.getParentCommand(Command.class)).isNull();
        Assertions.assertThat(commandDelegate4.getParentCommand(AbstractCommand.class)).isNull();
        Assertions.assertThat(commandDelegate4.getParentCommand(AbstractCommandImpl.class)).isNull();
        Assertions.assertThat(commandDelegate4.getParentCommand(AbstractExecutionCommand.class)).isNull();
        Assertions.assertThat(commandDelegate4.getParentCommand(AbstractUserActionCommand.class)).isNull();

        AbstractCommand abstractCommand51 = new AbstractCommandImpl(null, null);
        AbstractCommand abstractCommand52 = new AbstractCommandImpl(null, abstractCommand51);
        CommandDelegate commandDelegate5 = new CommandDelegate(abstractCommand52);
        Assertions.assertThat(commandDelegate5.getParentCommand(Command.class)).isSameAs(abstractCommand51);
        Assertions.assertThat(commandDelegate5.getParentCommand(AbstractCommand.class)).isSameAs(abstractCommand51);
        Assertions.assertThat(commandDelegate5.getParentCommand(AbstractCommandImpl.class)).isSameAs(abstractCommand51);
        Assertions.assertThat(commandDelegate5.getParentCommand(AbstractExecutionCommand.class)).isNull();
        Assertions.assertThat(commandDelegate5.getParentCommand(AbstractUserActionCommand.class)).isNull();

        AbstractCommand abstractCommand61 = new AbstractExecutionCommandImpl(null);
        AbstractCommand abstractCommand62 = new AbstractCommandImpl(null, abstractCommand61);
        AbstractCommand abstractCommand63 = new AbstractCommandImpl(null, abstractCommand62);
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
        // TODO
    }

    /**
     * {@link CommandDelegate} class test.
     */
    @Test
    public void setContextTest() {
        // TODO
    }

    /**
     * {@link CommandDelegate} class test.
     */
    @Test
    public void getCommandRunnerTest() {
        // TODO
    }

    /**
     * {@link CommandDelegate} class test.
     */
    @Test
    public void setCommandRunnerTest() {
        // TODO
    }

    /**
     * {@link CommandDelegate} class test.
     */
    @Test
    public void executeTest() {
        // TODO
    }

    /**
     * {@link CommandDelegate} class test.
     */
    @Test
    public void resetTest() {
        // TODO
    }

}
