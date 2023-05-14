package ingkonkurs.routing;

import ingkonkurs.services.atmservice.handlers.AtmServiceHandler;
import ingkonkurs.services.onlinegame.handlers.OnlineGameHandler;
import ingkonkurs.services.transactions.handlers.TransactionsHandler;

import io.netty.channel.ChannelHandler;

import java.util.HashMap;
import java.util.Map;

public class Routes {

    private static final Map<String, Map<String, ChannelHandler>> routes = new HashMap<>();

    static {
        addRoute("POST", "/atms/calculateOrder", new AtmServiceHandler());
        addRoute("POST", "/onlinegame/calculate", new OnlineGameHandler());
        addRoute("POST", "/transactions/report", new TransactionsHandler());
    }

    private static void addRoute(String method, String path, ChannelHandler handler) {
        Map<String, ChannelHandler> methodRoutes = routes.computeIfAbsent(method, k -> new HashMap<>());
        methodRoutes.put(path, handler);
    }

    public static Map<String, Map<String, ChannelHandler>> getRoutes() {
        return routes;
    }
}
