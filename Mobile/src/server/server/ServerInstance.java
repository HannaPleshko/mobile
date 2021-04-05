package server.server;

public enum ServerInstance {
    INSTANCE;

    private Server instance;

    ServerInstance() {
        instance = new Server();
    }

    public Server getInstance() {
        return instance;
    }
}
