import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Utils {

    public static List<String> readFile(String name) throws IOException {
        List<String> out = new ArrayList<>();
        InputStream is = getFileFromResourceAsStream(name);
        InputStreamReader streamReader =
                new InputStreamReader(is, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(streamReader);

        out = reader.lines().collect(Collectors.toList());
        return out;
    }

    private static InputStream getFileFromResourceAsStream(String fileName) {

        InputStream inputStream = Utils.class.getClassLoader().getResourceAsStream(fileName);
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return inputStream;
        }

    }
}
