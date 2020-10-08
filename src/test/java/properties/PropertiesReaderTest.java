package properties;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Valentin
 * @version 1.00
 * @since 08/10/2020
 */

public class PropertiesReaderTest {

    @Test
    public void whenReadAllArgs() {
        String[] args = {"command", "/folder/folder2", "/new folder/ new folder", "/folder/new"};
        PropertiesReader propReader = new PropertiesReader();
        boolean resultOperation = propReader.setPropertyFromArgs(args);
        String command = propReader.getCommand();
        List<String> pathsList = propReader.getPathsList();
        String errorMessage = propReader.getErrorMessage();

        assertThat(resultOperation, is(true));
        assertThat(command, is("command"));
        assertThat(pathsList.isEmpty(), is(false));
        assertThat(pathsList.get(0), is("/folder/folder2"));
        assertThat(pathsList.get(1), is("/new folder/ new folder"));
        assertThat(pathsList.get(2), is("/folder/new"));
    }

    @Test
    public void whenArgsHavingOnlyOneProperty() {
        String[] args = {"command"};
        PropertiesReader propReader = new PropertiesReader();
        boolean resultOperation = propReader.setPropertyFromArgs(args);
        String command = propReader.getCommand();
        List<String> pathsList = propReader.getPathsList();
        String errorMessage = propReader.getErrorMessage();

        assertThat(resultOperation, is(false));
        assertThat(command, is("empty"));
        assertThat(pathsList.isEmpty(), is(true));
    }
}