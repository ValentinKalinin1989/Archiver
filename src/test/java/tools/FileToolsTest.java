package tools;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Valentin
 * @version 1.00
 * @since 12/10/2020
 */

public class FileToolsTest {
    @Test
    public void whenNoChangeName() {
        String result = FileTools.getBaseName("file");
        assertThat(result, is("file"));
    }

    @Test
    public void whenOnePoint() {
        String result = FileTools.getBaseName("file.word");
        assertThat(result, is("file"));
    }

    @Test
    public void whenTwoPoint() {
        String result = FileTools.getBaseName("file.name.txt");
        assertThat(result, is("file.name"));
    }

}