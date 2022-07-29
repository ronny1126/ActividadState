package oscarblancarte.ipd.state;

import oscarblancarte.ipd.state.states.AbstractServerState;
import oscarblancarte.ipd.state.states.StartServerState;
import oscarblancarte.ipd.state.states.StartingServerState;
import oscarblancarte.ipd.state.states.StopServerState;
import oscarblancarte.ipd.state.states.StopingServerState;

public class Server {

    private static final int maxRequest = 5;
    private final MessageProcess messageProcess 
            = new MessageProcess(this);
    private AbstractServerState state;

    public Server() {
        setState(new StopServerState(this));
    }

    public void handleMessage(String message) {
        state.handleMessage(this, message);
    }

    public int getMaxRequest() {
        return maxRequest;
    }

    public MessageProcess getMessageProcess() {
        return messageProcess;
    }

    public AbstractServerState getState() {
        return state;
    }

    public void setState(AbstractServerState state) {
        if (this.state instanceof StartingServerState 
                && state instanceof StopServerState) {
            System.out.println("Server is starting, "
                    + "cannot change state");
            return;
        } 
        if (this.state instanceof StopingServerState 
                && state instanceof StartServerState) {
            System.out.println("Servidor apagando, "
                    + "no se puede cambiar de estado");
            return;
        }
        this.state = state;
        System.out.println("Server change state > " + 
                this.state.getClass().getSimpleName());
    }
}
