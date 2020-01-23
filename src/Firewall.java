import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 *
 */
public class Firewall implements FirewallInterface {

    List<String> DIRECTION = new ArrayList<>(List.of("inbound", "outbound"));
    List<String> PROTOCOL = new ArrayList<>(List.of("tcp", "udp"));
    private static int LOWER_PORT_VALUE = 1;
    private static int UPPER_PORT_VALUE = 65535;

    /**
     *
     */
    private Map<RuleType, List<List<String>>> rulesMap;

    /**
     * @param filePath
     * @throws IOException
     */
    Firewall(String filePath) throws IOException {

        rulesMap = new HashMap<>();

        List<Packet> fileOutput = CSVReader.readFile(filePath);
        List<Packet> inBoundTCP = fileOutput.stream()
                .filter(packet -> packet.getDirection().equals("inbound") && packet.getProtocol().equals("tcp"))
                .collect(Collectors.toList());
        List<Packet> inBoundUDP = fileOutput.stream()
                .filter(packet -> packet.getDirection().equals("inbound") && packet.getProtocol().equals("udp"))
                .collect(Collectors.toList());
        List<Packet> outBoundTCP = fileOutput.stream()
                .filter(packet -> packet.getDirection().equals("outbound") && packet.getProtocol().equals("tcp"))
                .collect(Collectors.toList());
        List<Packet> outBoundUDP = fileOutput.stream()
                .filter(packet -> packet.getDirection().equals("outbound") && packet.getProtocol().equals("udp"))
                .collect(Collectors.toList());

        addPacketsToMap(RuleType.INBOUNDTCP, inBoundTCP);
        addPacketsToMap(RuleType.INBOUNDUDP, inBoundUDP);
        addPacketsToMap(RuleType.OUTBOUNDTCP, outBoundTCP);
        addPacketsToMap(RuleType.OUTBOUNDUDP, outBoundUDP);
    }

    /**
     * @param ruleType
     * @param packetList
     */
    private void addPacketsToMap(RuleType ruleType, List<Packet> packetList) {
        List<List<String>> ipAddress = new ArrayList<>();
        for (int i = 1; i < 65535; i++) {
            ipAddress.add(new ArrayList<>());
        }
        for (Packet packet : packetList) {
            for (int port = packet.getLowerPort(); port <= packet.getUpperPort(); port++) {
                ipAddress.get(port).add(packet.getLowerIp() + "-" + packet.getUpperIp());
            }
        }
        rulesMap.put(ruleType, ipAddress);
    }

    @Override
    public boolean accept_packet(String direction, String protocol, int port, String ip_address) {
        if (isValidInput(direction, protocol, port, ip_address)) {
            List<String> validIpAddress;
            if (direction.equals("inbound")) {
                if (protocol.equals("tcp")) {
                    validIpAddress = rulesMap.get(RuleType.INBOUNDTCP).get(port);
                } else {
                    validIpAddress = rulesMap.get(RuleType.INBOUNDUDP).get(port);
                }
            } else {
                if (protocol.equals("tcp")) {
                    validIpAddress = rulesMap.get(RuleType.OUTBOUNDTCP).get(port);
                } else {
                    validIpAddress = rulesMap.get(RuleType.OUTBOUNDUDP).get(port);
                }
            }
            return checkIfIpIsIPresent(ip_address, validIpAddress);
        }
        return false;
    }

    /**
     *
     * @param inputIP
     * @param availableIp
     * @return
     */
    private boolean checkIfIpIsIPresent(String inputIP, List<String> availableIp) {
        String[] ipParts = inputIP.split("[.]");
        int ip1 = Integer.parseInt(ipParts[0]);
        int ip2 = Integer.parseInt(ipParts[1]);
        int ip3 = Integer.parseInt(ipParts[2]);
        int ip4 = Integer.parseInt(ipParts[3]);

        for (String ipRange : availableIp) {
            String[] lowerIpParts = ipRange.split("-")[0].split("[.]");
            String[] upperIpParts = ipRange.split("-")[1].split("[.]");

            if (ip1 >= Integer.parseInt(lowerIpParts[0]) && ip1 <= Integer.parseInt(upperIpParts[0]) &&
                    ip2 >= Integer.parseInt(lowerIpParts[1]) && ip2 <= Integer.parseInt(upperIpParts[1]) &&
                    ip3 >= Integer.parseInt(lowerIpParts[2]) && ip3 <= Integer.parseInt(upperIpParts[2]) &&
                    ip4 >= Integer.parseInt(lowerIpParts[3]) && ip4 <= Integer.parseInt(upperIpParts[3])) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param direction
     * @param protocol
     * @param port
     * @param ip_address
     * @return
     */
    private boolean isValidInput(String direction, String protocol, int port, String ip_address) {
        String[] ipValues = ip_address.split("[.]");
        if (DIRECTION.contains(direction) && PROTOCOL.contains(protocol) &&
                port >= LOWER_PORT_VALUE && port <= UPPER_PORT_VALUE && ipValues.length == 4) {
            int[] ipNumbers = new int[4];
            for (int i = 0; i < ipValues.length; i++) {
                ipNumbers[i] = Integer.parseInt(ipValues[i]);
            }
            if (ipNumbers[0] >= 0 && ipNumbers[0] <= 255 && ipNumbers[1] >= 0 && ipNumbers[1] <= 255 &&
                    ipNumbers[2] >= 0 && ipNumbers[2] <= 255 && ipNumbers[3] >= 0 && ipNumbers[3] <= 255) {
                return true;
            }
        }
        return false;
    }
}
