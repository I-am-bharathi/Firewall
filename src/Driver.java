import java.io.IOException;

/**
 *
 */
public class Driver {

    public static void main(String[] args) {
        try {
            FirewallInterface firewall = new Firewall("res/fw.csv");
            System.out.println(firewall.accept_packet("inbound", "udp", 53, "192.168.2.1"));
            System.out.println(firewall.accept_packet("outbound", "tcp", 10234, "192.168.10.11"));
            System.out.println(firewall.accept_packet("inbound", "tcp", 81, "192.168.1.2"));
            System.out.println(firewall.accept_packet("inbound", "udp", 24, "52.12.48.92"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
