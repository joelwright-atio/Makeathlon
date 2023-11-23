package de.atio.main;


import com.thingworx.communications.client.ClientConfigurator;
import com.thingworx.communications.client.ConnectedThingClient;
import com.thingworx.communications.client.things.VirtualThing;
import de.atio.main.config.ConfigFileHandler;
import de.atio.main.config.SamplePasswordCallback;
import de.atio.restapi.MachinesRESTAPIHandler;


import java.util.ArrayList;

public class MainApp extends ConnectedThingClient{
    public MainApp(ClientConfigurator config) throws Exception {
        super(config);
        // TODO Auto-generated constructor stub
    }

    //private static final Logger LOG = LoggerFactory.getLogger(MainApp.class);


    public static void main(String[] args) throws Exception{
        ConfigFileHandler configHandler = new ConfigFileHandler("config.json");


        // Let's configure the client which connects to ThingWorx
        ClientConfigurator config = new ClientConfigurator();

        String protocol = configHandler.GetBooleanValue("thingworx,serverSettings,isSecure") ? "wss://" : "ws://";
        String domain = configHandler.GetStringValue("thingworx,serverSettings,domain");
        String port = configHandler.GetStringValue("thingworx,serverSettings,port");

        config.setUri(protocol + domain + ":" + port + "/Thingworx/WS");
        config.setReconnectInterval(15);
        config.setSecurityClaims(new SamplePasswordCallback(configHandler.GetStringValue("thingworx,serverSettings,appKey")));
        config.setName(configHandler.GetStringValue("thingworx,clientSettings,identifier"));
        config.ignoreSSLErrors(configHandler.GetBooleanValue("thingworx,serverSettings,ignoreSSLErrors"));

        // Override default log levels
        /*
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        //StatusPrinter.print(loggerContext);
        ch.qos.logback.classic.Logger logger = loggerContext.getLogger("ROOT");
        Appender<ILoggingEvent> fileLogAppender = logger.getAppender("ROLLING");
        if(fileLogAppender!=null){
            System.out.println("***** Detaching rolling appender.");
            logger.detachAppender("ROLLING");
        }
        logger.setLevel(Level.INFO);
        */

        int scanRate = 5000;
        MainApp client = new MainApp(config);

        try {
            // Start the client
            client.start();
        } catch (Exception eStart) {
            System.out.println("Initial Start Failed : " + eStart.getMessage());
        }

        String identifierZebra = configHandler.GetStringValue("zebra,identifier");
        final MachinesRESTAPIHandler handler1 = new MachinesRESTAPIHandler(identifierZebra, "Thing has been created based on information in config.json", identifierZebra, client, configHandler);

        client.bindThing(handler1); // I have added the Aditec service in the ModbusRESTAPIHandler class.


        while (!client.isShutdown()) {
            // Only process the Virtual Things if the client is connected
            if (client.isConnected()) {
                // Loop over all the Virtual Things and process them
                for (VirtualThing thing : client.getThings().values()) {
                    try {
                        thing.processScanRequest();
                    } catch (Exception eProcessing) {
                        System.out.println("Error Processing Scan Request for [" + thing.getName() + "] : " + eProcessing.getMessage());
                    }
                }
            }



            // Suspend processing at the scan rate interval
            Thread.sleep(scanRate);

        }
    }
}

