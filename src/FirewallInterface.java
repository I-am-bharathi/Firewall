/**
 *
 */
public interface FirewallInterface {

    /**
     * @param direction
     * @param protocol
     * @param port
     * @param ip_address
     * @return
     */
    boolean accept_packet(String direction, String protocol, int port, String ip_address);
}
