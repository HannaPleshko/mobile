package client.sample;

public enum ClientInstance {
    INSTANCE;

    private Client instance;

    ClientInstance() {
        instance = new Client();
    }

    public Client getInstance() {
        return instance;
    }
}
