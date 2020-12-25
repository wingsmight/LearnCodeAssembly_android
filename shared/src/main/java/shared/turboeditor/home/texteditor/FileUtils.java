

package shared.turboeditor.home.texteditor;

import org.mozilla.universalchardet.UniversalDetector;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

public class FileUtils {
    public static String getDetectedEncoding(InputStream is) {
        String encoding = null;
        try {
            UniversalDetector detector = new UniversalDetector(null);
            byte[] buf = new byte[4096];
            int nread;
            while ((nread = is.read(buf)) > 0 && !detector.isDone()) {
                detector.handleData(buf, 0, nread);
            }
            detector.dataEnd();
            encoding = detector.getDetectedCharset();
        } catch (IOException e) {
            // nothing to do
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (encoding == null) {
                return Charset.defaultCharset().name();
            }
        }
        return encoding;
    }

    public static String getDetectedEncoding(File file) throws FileNotFoundException {
        return getDetectedEncoding(new FileInputStream(file));
    }
}
