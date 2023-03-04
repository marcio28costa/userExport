import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ConfigReader {

    public static Config readConfig(String fileName) throws IOException {
        File file = new File(fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        String ip = "";
        String port = "";
        String database = "";
        String filename = "";
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(":");
            if (parts.length == 2) {
                String key = parts[0].trim();
                String value = parts[1].trim();
                switch (key) {
                    case "ip":
                        ip = value;
                        break;
                    case "porta":
                        port = value;
                        break;
                    case "banco":
                        database = value;
                        break;
                    case "arquivo":
                        filename = value;
                        break;
                }
            }
        }
        reader.close();
        return new Config(ip, port, database, filename);
    }

    public static void writeConfig(String fileName, Config config) throws IOException {
        File file = new File(fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter writer = new FileWriter(file);
        if (config != null) {
            writer.write("ip:" + config.getIp() + "\n");
            writer.write("porta:" + config.getPort() + "\n");
            writer.write("banco:" + config.getDatabase() + "\n");
            writer.write("arquivo:" + config.getFilename() + "\n");
        } else {
            writer.write("ip:\n");
            writer.write("porta:\n");
            writer.write("banco:\n");
            writer.write("arquivo:\n");
        }
        writer.close();
    }
}
