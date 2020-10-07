package command;

import java.io.*;
import java.util.List;
import java.util.function.Consumer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * реализует разархивирование файлов
 */
public class Unpacking implements Command {
    /**
     * реализует операцию разархивирования файлов
     *
     * @param pathsList - пути к файлам для разархивирования
     */
    @Override
    public void execute(List<String> pathsList) {
        pathsList.forEach(new UnpackConsumer());
    }

    @Override
    public String info() {
        return null;
    }

    @Override
    public String commandName() {
        return "unpacking";
    }

    /**
     * выполняет разархивирование для файла, к котрому был получен путь
     */
    static class UnpackConsumer implements Consumer<String> {
        private final File destDir = new File(System.getProperty("user.dir"));

        @Override
        public void accept(String fileZip) {
            try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(fileZip))) {
                ZipEntry zipEntry = zipInputStream.getNextEntry();
                while (zipEntry != null) {
                    File newFile = new File(destDir, zipEntry.getName());
                    new File(newFile.getParent()).mkdirs();
                    FileOutputStream fileOutputStream = new FileOutputStream(newFile);
                    byte[] buffer = new byte[1024];
                    int len = -1;
                    while ((len = zipInputStream.read(buffer)) != -1) {
                        fileOutputStream.write(buffer, 0, len);
                    }
                    fileOutputStream.close();
                    zipEntry = zipInputStream.getNextEntry();
                }
                zipInputStream.closeEntry();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
