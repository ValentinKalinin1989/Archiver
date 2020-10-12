package command;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Valentin
 * @version 1.00
 * @since 08/10/2020
 */

public class PackingTest {
    /**
     * Архивирует два файла в архив,
     * читает именя файлов  и строки записанные в текстовые файлы
     * из полученного архива, и сравнивает их с теми, которые архивировались.
     * <p>
     * После получения результатов архивирования, полученный архив удаляется.
     */
    @Test
    public void packingTwoFiles() {
        final String FS = File.separator;
        List<String> paths = new ArrayList<>();
        paths.add("src" + FS + "test" + FS + "java" + FS + "command" + FS + "files_for_test" + FS + "fistfile.txt");
        paths.add("src" + FS + "test" + FS + "java" + FS + "command" + FS + "files_for_test" + FS + "innerfolder");
        new Packing().execute(paths);

        List<String> fileNames = new ArrayList<>();
        List<String> fileString = new ArrayList<>();

        String zipNameFirst = "src" + FS + "test" + FS + "java" + FS + "command" + FS + "files_for_test" + FS + "fistfile.zip";
        String zipNameSecond = "src" + FS + "test" + FS + "java" + FS + "command" + FS + "files_for_test" + FS + "innerfolder.zip";

        getFilesNamesAndStrings(zipNameFirst, fileNames, fileString);
        getFilesNamesAndStrings(zipNameSecond, fileNames, fileString);


        boolean resultDelFirstZip = new File(zipNameFirst).delete();
        boolean resultDelSecondZip = new File(zipNameSecond).delete();

        assertThat(fileNames.get(0), is("fistfile.txt"));
        assertThat(fileNames.get(1), is("innerfolder" + FS + "innerfile.txt"));
        assertThat(fileString.get(0), is("first file"));
        assertThat(fileString.get(1), is("for test"));
        assertThat(fileString.get(2), is("inner file"));
        assertThat(resultDelFirstZip, is(true));
        assertThat(resultDelSecondZip, is(true));
    }

    /**
     * считывает имена заархивированных файлов,
     * и записанные в нмх строки
     *
     * @param pathToZip  - путь к архиву
     * @param fileNames  - список для хранения полученных имен файлов
     * @param fileString - список для хранения полученных из файлов строк
     */
    private void getFilesNamesAndStrings(String pathToZip, List<String> fileNames, List<String> fileString) {
        try (ZipFile zipFile = new ZipFile(pathToZip)) {
            for (Enumeration<? extends ZipEntry> iterator = zipFile.entries(); iterator.hasMoreElements(); ) {
                ZipEntry zipEntry = iterator.nextElement();
                fileNames.add(zipEntry.getName());
                if (!zipEntry.isDirectory()) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(zipFile.getInputStream(zipEntry)));
                    String str;
                    while ((str = reader.readLine()) != null) {
                        fileString.add(str);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}