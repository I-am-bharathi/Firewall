import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class CSVReader {

    /**
     *
     * @param filePath
     * @return
     * @throws IOException
     */
    public static List<Packet> readFile(String filePath) throws IOException {
        StringBuilder fileOutput = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String fileLine;
        while ((fileLine = reader.readLine()) != null) {
            fileOutput.append(fileLine);
            fileOutput.append("\n");
        }
        return parseFileOutput(fileOutput.toString());
    }

    /**
     *
     * @param fileOutput
     * @return
     */
    private static List<Packet> parseFileOutput(String fileOutput) {
        List<Packet> packets = new ArrayList<>();
        String[] packetsList = fileOutput.split("\n");
        for (int i = 1; i < packetsList.length; i++) {
            String[] packetDetails = packetsList[i].split(",");

            String direction = packetDetails[0];
            String protocol = packetDetails[1];
            String port = packetDetails[2];
            String ipAddress = packetDetails[3];

            int lowerPort, upperPort;
            String lowerIp, upperIp;

            if (port.contains("-")) {
                String[] portRange = port.split("-");
                lowerPort = Integer.parseInt(portRange[0]);
                upperPort = Integer.parseInt(portRange[1]);
            } else {
                lowerPort = Integer.parseInt(port);
                upperPort = Integer.parseInt(port);
            }

            if (ipAddress.contains("-")) {
                String[] ipRange = ipAddress.split("-");
                lowerIp = ipRange[0];
                upperIp = ipRange[1];
            } else {
                lowerIp = ipAddress;
                upperIp = ipAddress;
            }

            Packet packet = new Packet(direction, protocol, lowerPort, upperPort, lowerIp, upperIp);
            packets.add(packet);
        }
        return packets;
    }
}
