/**
 *
 */
public class Packet {

    /**
     *
     */
    private String direction;

    /**
     *
     */
    private String protocol;

    /**
     *
     */
    private int lowerPort;

    /**
     *
     */
    private int upperPort;

    /**
     *
     */
    private String lowerIp;

    /**
     *
     */
    private String upperIp;

    /**
     *
     * @param direction
     * @param protocol
     * @param lowerPort
     * @param upperPort
     * @param lowerIp
     * @param upperIp
     */
    Packet(String direction, String protocol, int lowerPort, int upperPort, String lowerIp, String upperIp) {
        this.direction = direction;
        this.protocol = protocol;
        this.lowerPort = lowerPort;
        this.upperPort = upperPort;
        this.lowerIp = lowerIp;
        this.upperIp = upperIp;
    }

    /**
     *
     * @return
     */
    public String getDirection() {
        return direction;
    }

    /**
     *
     * @return
     */
    public String getProtocol() {
        return protocol;
    }

    /**
     *
     * @return
     */
    public int getLowerPort() {
        return lowerPort;
    }

    /**
     *
     * @return
     */
    public int getUpperPort() {
        return upperPort;
    }

    /**
     *
     * @return
     */
    public String getLowerIp() {
        return lowerIp;
    }

    /**
     *
     * @return
     */
    public String getUpperIp() {
        return upperIp;
    }
}
