package examples.websocketstream;

import com.binance.connector.client.WebSocketStreamClient;
import com.binance.connector.client.impl.WebSocketStreamClientImpl;

public final class AllRollingWindowTicker {
    private AllRollingWindowTicker() {
    }

    private static Proxy proxy;

    public static class Proxy {
        private String httpsProxyHost = "127.0.0.1";
        private String httpsProxyPort = "58591";

        public Proxy(String httpsProxyHost, String httpsProxyPort) {
            this.httpsProxyHost = httpsProxyHost;
            this.httpsProxyPort = httpsProxyPort;
        }

        public String getHttpsProxyHost() {
            return httpsProxyHost;
        }

        public void setHttpsProxyHost(String httpsProxyHost) {
            this.httpsProxyHost = httpsProxyHost;
        }

        public String getHttpsProxyPort() {
            return httpsProxyPort;
        }

        public void setHttpsProxyPort(String httpsProxyPort) {
            this.httpsProxyPort = httpsProxyPort;
        }
    }


    public static void main(String[] args) {
        Proxy proxy = new Proxy("127.0.0.1", "58591");
        System.setProperty("https.proxyHost", proxy.getHttpsProxyHost());
        System.setProperty("https.proxyPort", proxy.getHttpsProxyPort());
        System.out.println(String.format("设置本地代理 http://%s:%s", proxy.getHttpsProxyHost(), proxy.getHttpsProxyPort()));

        WebSocketStreamClient client = new WebSocketStreamClientImpl();
        client.allRollingWindowTicker("1h", ((event) -> {
            System.out.println(event);
            client.closeAllConnections();
        }));
    }
}
