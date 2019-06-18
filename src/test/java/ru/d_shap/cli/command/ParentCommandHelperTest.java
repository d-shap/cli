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

/**
 * Tests for {@link ParentCommandHelper}.
 *
 * @author Dmitry Shapovalov
 */
public final class ParentCommandHelperTest extends BaseCliTest {

    /**
     * Test class constructor.
     */
    public ParentCommandHelperTest() {
        super();
    }

    /**
     * {@link ParentCommandHelper} class test.
     */
    @Test
    public void constructorTest() {
        Assertions.assertThat(ParentCommandHelper.class).hasOnePrivateConstructor();
    }

    /**
     * {@link ParentCommandHelper} class test.
     */
    @Test
    public void getParentCommandTest() {
        Assertions.assertThat(ParentCommandHelper.getParentCommand(null, null)).isNull();

        Assertions.assertThat(ParentCommandHelper.getParentCommand(null, new AbstractCommandImpl(null, null))).isNull();

        AbstractCommand parentCommand1 = new AbstractCommandImpl(null, null);
        Assertions.assertThat(ParentCommandHelper.getParentCommand(null, new AbstractCommandImpl(null, parentCommand1))).isSameAs(parentCommand1);

        AbstractCommand parentCommand2 = new AbstractCommandImpl(null, null);
        Assertions.assertThat(ParentCommandHelper.getParentCommand(parentCommand2, new AbstractCommandImpl(null, null))).isSameAs(parentCommand2);

        AbstractCommand parentCommand31 = new AbstractCommandImpl(null, null);
        AbstractCommand parentCommand32 = new AbstractCommandImpl(null, null);
        Assertions.assertThat(ParentCommandHelper.getParentCommand(parentCommand31, new AbstractCommandImpl(null, parentCommand32))).isSameAs(parentCommand31);
    }

}
