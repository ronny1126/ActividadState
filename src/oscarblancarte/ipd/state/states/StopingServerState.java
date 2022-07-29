package oscarblancarte.ipd.state.states;

import oscarblancarte.ipd.state.Server;
/**
 *
 * @author rjaimea
 */
public class StopingServerState extends AbstractServerState {

    private Thread monitoringThread;

    public StopingServerState(final Server server) {
        server.getMessageProcess().start();
        monitoringThread = new Thread(new Runnable() {

            @Override
            public void run() {
                
                try {
                    while (true) {
                        Thread.sleep(1 * 1);
                        if (server.getMessageProcess().countMessage() == 0) {
                            server.setState(new StopServerState(server));
                            stop();
                        }
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        });
        monitoringThread.start();
    }
    
    public void stop() {
        if (this.monitoringThread != null) {
            this.monitoringThread.stop();
            this.monitoringThread = null;
        }
    }

    @Override
    public void handleMessage(Server server, String message) {
        System.out.println("Servidor enviado pendientes y apagando.");
    }

}
