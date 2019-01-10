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
    public void getParentCommandTest() {
        AbstractCommand abstractCommand1 = new AbstractCommandImpl(null);
        Assertions.assertThat(abstractCommand1.getParentCommand()).isNull();

        AbstractCommand abstractCommand2 = new AbstractCommandImpl(null, null);
        Assertions.assertThat(abstractCommand2.getParentCommand()).isNull();

        AbstractCommand abstractCommand3 = new AbstractCommandImpl(null, null);
        AbstractCommand abstractCommand4 = new AbstractCommandImpl(null, abstractCommand3);
        Assertions.assertThat(abstractCommand4.getParentCommand()).isSameAs(abstractCommand3);
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

        AbstractCommand abstractCommand3 = new AbstractCommandImpl(null, null);
        AbstractCommand abstractCommand4 = new AbstractCommandImpl(null, abstractCommand3);
        Assertions.assertThat(abstractCommand4.getContext()).isNull();
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

        AbstractCommand abstractCommand3 = new AbstractCommandImpl(null, null);
        Context context3 = new Context();
        abstractCommand3.setContext(context3);
        AbstractCommand abstractCommand4 = new AbstractCommandImpl(null, abstractCommand3);
        Assertions.assertThat(abstractCommand4.getContext()).isNull();

        AbstractCommand abstractCommand5 = new AbstractCommandImpl(null, null);
        AbstractCommand abstractCommand6 = new AbstractCommandImpl(null, abstractCommand5);
        Context context6 = new Context();
        abstractCommand6.setContext(context6);
        Assertions.assertThat(abstractCommand6.getContext()).isSameAs(context6);
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

        AbstractCommand abstractCommand3 = new AbstractCommandImpl(null, null);
        AbstractCommand abstractCommand4 = new AbstractCommandImpl(null, abstractCommand3);
        Assertions.assertThat(abstractCommand4.getCommandRunner()).isNull();
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

        AbstractCommand abstractCommand3 = new AbstractCommandImpl(null, null);
        CommandRunner commandRunner3 = new CommandRunner(System.out, System.in);
        abstractCommand3.setCommandRunner(commandRunner3);
        AbstractCommand abstractCommand4 = new AbstractCommandImpl(null, abstractCommand3);
        Assertions.assertThat(abstractCommand4.getCommandRunner()).isNull();

        AbstractCommand abstractCommand5 = new AbstractCommandImpl(null, null);
        AbstractCommand abstractCommand6 = new AbstractCommandImpl(null, abstractCommand5);
        CommandRunner commandRunner4 = new CommandRunner(System.out, System.in);
        abstractCommand6.setCommandRunner(commandRunner4);
        Assertions.assertThat(abstractCommand6.getCommandRunner()).isSameAs(commandRunner4);
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

}
