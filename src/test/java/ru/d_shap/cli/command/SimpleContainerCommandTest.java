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

/**
 * Tests for {@link SimpleContainerCommand}.
 *
 * @author Dmitry Shapovalov
 */
public final class SimpleContainerCommandTest extends BaseCliTest {

    /**
     * Test class constructor.
     */
    public SimpleContainerCommandTest() {
        super();
    }

    /**
     * {@link SimpleContainerCommand} class test.
     */
    @Test
    public void executeTest() {
        ByteArrayOutputStream os01 = createOutputStream();
        InputStream is01 = createInputStream("Строка 1", "Строка 2");
        CommandRunner commandRunner01 = new CommandRunner(os01, is01);
        Command container01 = new SimpleContainerCommand(null);
        Context context01 = new Context();
        commandRunner01.execute(container01, context01);
        Assertions.assertThat(getLines(os01)).containsExactlyInOrder();
        Assertions.assertThat(context01.getValue(AbstractCommandImpl.COUNTER_KEY)).isNull();

        ByteArrayOutputStream os02 = createOutputStream();
        InputStream is02 = createInputStream("Строка 1", "Строка 2");
        CommandRunner commandRunner02 = new CommandRunner(os02, is02);
        Command container02 = new SimpleContainerCommand(null, null);
        Context context02 = new Context();
        commandRunner02.execute(container02, context02);
        Assertions.assertThat(getLines(os02)).containsExactlyInOrder();
        Assertions.assertThat(context02.getValue(AbstractCommandImpl.COUNTER_KEY)).isNull();

        ByteArrayOutputStream os03 = createOutputStream();
        InputStream is03 = createInputStream("Строка 1", "Строка 2");
        CommandRunner commandRunner03 = new CommandRunner(os03, is03);
        Command command03 = new AbstractCommandImpl("Prompt 1");
        Command container03 = new SimpleContainerCommand(command03);
        Context context03 = new Context();
        commandRunner03.execute(container03, context03);
        Assertions.assertThat(getLines(os03)).containsExactlyInOrder("Prompt 1", "Строка 1");
        Assertions.assertThat(context03.getValue(AbstractCommandImpl.COUNTER_KEY)).isNull();

        ByteArrayOutputStream os04 = createOutputStream();
        InputStream is04 = createInputStream("Строка 1", "Строка 2");
        CommandRunner commandRunner04 = new CommandRunner(os04, is04);
        Command parentCommand04 = new AbstractCommandImpl("Prompt parent");
        Command command04 = new AbstractCommandImpl("Prompt 1");
        Command container04 = new SimpleContainerCommand(parentCommand04, command04);
        Context context04 = new Context();
        commandRunner04.execute(container04, context04);
        Assertions.assertThat(getLines(os04)).containsExactlyInOrder("Prompt 1", "Строка 1", "Prompt parent", "Строка 2");
        Assertions.assertThat(context04.getValue(AbstractCommandImpl.COUNTER_KEY)).isEqualTo(1);

        ByteArrayOutputStream os05 = createOutputStream();
        InputStream is05 = createInputStream("Строка 1", "Строка 2");
        CommandRunner commandRunner05 = new CommandRunner(os05, is05);
        Command command053 = new AbstractCommandImpl("Prompt 3");
        Command command052 = new AbstractCommandImpl("Prompt 2", command053);
        Command command051 = new AbstractCommandImpl("Prompt 1", command052);
        Command container05 = new SimpleContainerCommand(command051);
        Context context05 = new Context();
        commandRunner05.execute(container05, context05);
        Assertions.assertThat(getLines(os05)).containsExactlyInOrder("Prompt 1", "Строка 1", "Prompt 2", "Строка 2", "Prompt 3", "Prompt 2", "Prompt 3");
        Assertions.assertThat(context05.getValue(AbstractCommandImpl.COUNTER_KEY)).isEqualTo(2);

        ByteArrayOutputStream os06 = createOutputStream();
        InputStream is06 = createInputStream("Строка 1", "Строка 2");
        CommandRunner commandRunner06 = new CommandRunner(os06, is06);
        Command parentCommand06 = new AbstractCommandImpl("Prompt parent");
        Command command063 = new AbstractCommandImpl("Prompt 3");
        Command command062 = new AbstractCommandImpl("Prompt 2", command063);
        Command command061 = new AbstractCommandImpl("Prompt 1", command062);
        Command container06 = new SimpleContainerCommand(parentCommand06, command061);
        Context context06 = new Context();
        commandRunner06.execute(container06, context06);
        Assertions.assertThat(getLines(os06)).containsExactlyInOrder("Prompt 1", "Строка 1", "Prompt 2", "Строка 2", "Prompt 3", "Prompt parent");
        Assertions.assertThat(context06.getValue(AbstractCommandImpl.COUNTER_KEY)).isEqualTo(1);
    }

}
