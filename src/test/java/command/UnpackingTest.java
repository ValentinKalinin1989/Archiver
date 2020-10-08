package command;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Valentin
 * @version 1.00
 * @since 08/10/2020
 */

public class UnpackingTest {
    /**
     * Распаковывает два файла,
     * читает строки записанные в текстовые файлы при распаковке архива,
     * и сравнивает их с теми, которые были в архиве.
     * <p>
     * После получения результатов распаковки, полученный файлы удаляются.
     */
    @Test
    public void whenUnpackingTwoFiles() {
        final String FS = File.separator;
        List<String> paths = new ArrayList<>();
        paths.add("src" + FS + "test" + FS + "java" + FS + "command" + FS + "files_for_test" + FS + "fold.zip");
        paths.add("src" + FS + "test" + FS + "java" + FS + "command" + FS + "files_for_test" + FS + "one.zip");
        new Unpacking().execute(paths);
        String oneStr = null;
        String twoStr = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("one.txt"));
            oneStr = reader.readLine();
            reader.close();
            reader = new BufferedReader(new FileReader("fold" + FS + "two.txt"));
            twoStr = reader.readLine();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        new File("one.txt").delete();
        new File("fold" + FS + "two.txt").delete();
        new File("fold").delete();

        assertThat(oneStr, is("one"));
        assertThat(twoStr, is("two"));

    }

}