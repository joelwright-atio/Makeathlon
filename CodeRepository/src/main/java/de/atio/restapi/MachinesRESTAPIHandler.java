package de.atio.restapi;


import com.thingworx.communications.client.ConnectedThingClient;
import com.thingworx.communications.client.things.VirtualThing;
import com.thingworx.metadata.annotations.ThingworxServiceDefinition;
import com.thingworx.metadata.annotations.ThingworxServiceParameter;
import com.thingworx.metadata.annotations.ThingworxServiceResult;
import de.atio.main.config.ConfigFileHandler;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;

public class MachinesRESTAPIHandler extends VirtualThing {
    //private static final Logger LOG = LoggerFactory.getLogger(AditecRESTAPIHandler.class);
    private final CloseableHttpClient httpclient;
    private final ConfigFileHandler configHandler;
    private final String protocol;
    private final String domain;
    private final String port;
    /*
    private String user;
    private String password;
    private String portNumber;
    private String databaseName;
    */

    public MachinesRESTAPIHandler(String name, String description, String identifier, ConnectedThingClient client, ConfigFileHandler configHandler) throws Exception {
        super(name, description, identifier, client);
        this.initializeFromAnnotations();

        this.httpclient = HttpClients.createDefault();
        this.configHandler = configHandler;
        this.protocol = this.configHandler.GetBooleanValue("machineController,serverSettings,isSecure") ? "https://" : "http://";
        this.domain = this.configHandler.GetStringValue("machineController,serverSettings,domain");
        this.port = this.configHandler.GetStringValue("machineController,serverSettings,port");

    }

    /*
     * Adds a machine to the server. Body containing the information about the machine. When the post is scuessfully made, the machineGuid is returned. POST REQUEST
     * @param machineName The name of the machine
     * @param heatingPower The heating power of the machine
     * @param volume The space in the machine
     * @param serialNo The serial number of the machine
     * */

    @ThingworxServiceDefinition(name = "addMachine", description = "Add a Machine")
    @ThingworxServiceResult(name = "result", description = "return machine GUID.", baseType = "STRING")
    public String addMachine(@ThingworxServiceParameter(name = "machineName", description = "The name of the machine", baseType = "STRING") String machineName,
                             @ThingworxServiceParameter(name = "heatingPower", description= "The heating power of the Machine", baseType = "INTEGER") Integer heatingPower,
                             @ThingworxServiceParameter(name = "volume", description= "The space in the machine", baseType = "INTEGER") Integer volume,
                             @ThingworxServiceParameter(name = "serialNo", description= "The heating power of the Machine", baseType = "STRING") String serialNo) throws Exception {

        String path = "/addMachine";
        String url = this.protocol + this.domain + ":" + this.port + path;
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        JSONObject json = new JSONObject();
        json.put("machineName", machineName);
        json.put("heatingPower", heatingPower);
        json.put("volume", volume);
        json.put("serialNo", serialNo);
        HttpPost request = new HttpPost(url);
        StringEntity params = new StringEntity(json.toString());
        request.addHeader("content-type", "application/json");
        request.addHeader("Accept", "application/json");
        request.setEntity(params);

        try (CloseableHttpResponse response = httpClient.execute(request)) {
            HttpEntity entity = response.getEntity();
            String content = EntityUtils.toString(entity, StandardCharsets.UTF_8);
            return content;
        }
    }

    /*
     * List all the machines on the server. No inputs required. GET REQUEST
     * */
    @ThingworxServiceDefinition(name = "listMachines", description = "Lists all the Machines on the server")
    @ThingworxServiceResult(name = "result", description = "returns a JSON of Machines", baseType = "JSON")

    public JSONArray listMachines() throws Exception {
        String path = "/listMachines";
        String url = this.protocol + this.domain + ":" + this.port + path;
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);
        request.addHeader("content-type", "application/json");
        request.addHeader("Accept", "application/json");


        try (CloseableHttpResponse response = httpClient.execute(request)) {
            HttpEntity entity = response.getEntity();
            String content = EntityUtils.toString(entity, StandardCharsets.UTF_8);
            JSONArray jsonArray = new JSONArray(content);
            return jsonArray;
        }
    }

    /*
     * Get the process values from the machine. MachineGuid is a request peramter and is required. GET REQUEST
     * */
    @ThingworxServiceDefinition(name = "getProcessValues", description = "Get the process values from the machine")
    @ThingworxServiceResult(name = "result", description = "returns a JSON of process values", baseType = "JSON")

    public JSONArray getProcessValues(@ThingworxServiceParameter(name = "machineGuid", description = "The GUID of the machine", baseType = "STRING") String machineGuid) throws Exception {
        String path = "/getProcessValues?machineGuid=" + machineGuid;
        String url = this.protocol + this.domain + ":" + this.port + path;
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);
        request.addHeader("content-type", "application/json");
        request.addHeader("Accept", "application/json");

        try (CloseableHttpResponse response = httpClient.execute(request)){
            HttpEntity entity = response.getEntity();
            String content = EntityUtils.toString(entity, StandardCharsets.UTF_8);
            JSONArray jsonArray = new JSONArray(content);
            return jsonArray;
        }
    }

    /*
     * Adds a program to the machine. Body contains the program name and the machine guid which is should be attached to the program.
     * When the post is sucessfully made, the programGuid is returned. POST REQUEST
     * */
    @ThingworxServiceDefinition(name = "addProgram", description = "Add a Program")
    @ThingworxServiceResult(name = "result", description = "return program GUID.", baseType = "STRING")

    public String addProgram(@ThingworxServiceParameter(name = "programName", description = "The name of the program", baseType = "STRING") String programName,
                             @ThingworxServiceParameter(name = "machineGuid", description = "The GUID of the machine", baseType = "STRING") String machineGuid) throws Exception {
        String path = "/addProgram";
        String url = this.protocol + this.domain + ":" + this.port + path;
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        JSONObject json = new JSONObject();
        json.put("programName", programName);
        json.put("machineGuid", machineGuid);
        HttpPost request = new HttpPost(url);
        StringEntity params = new StringEntity(json.toString());
        request.addHeader("content-type", "application/json");
        request.addHeader("Accept", "application/json");
        request.setEntity(params);

        try (CloseableHttpResponse response = httpClient.execute(request)) {
            HttpEntity entity = response.getEntity();
            String content = EntityUtils.toString(entity, StandardCharsets.UTF_8);
            return content;
        }
    }

    /*
     * Adds a step to the program. Body contains the programGuid and the step information which is should be attached to the step.
     * When the post is sucessfully made, the stepGuid is returned. POST REQUEST
     * */
    @ThingworxServiceDefinition(name = "addStep", description = "Add a Step")
    @ThingworxServiceResult(name = "result", description = "return step GUID.", baseType = "STRING")

    public String addStep(@ThingworxServiceParameter(name = "programGuid", description = "The GUID of the program", baseType = "STRING") String programGuid,
                          @ThingworxServiceParameter(name = "stepName", description = "The name of the step", baseType = "STRING") String stepName,
                          @ThingworxServiceParameter(name = "stepDuration", description = "The step duration in seconds", baseType = "INTEGER") String stepDuration,
                          @ThingworxServiceParameter(name = "nominalTemperature", description = "The value of the step", baseType = "INTEGER") Integer nominalTemperature) throws Exception {
        String path = "/addStep";
        String url = this.protocol + this.domain + ":" + this.port + path;
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        JSONObject json = new JSONObject();
        json.put("programGuid", programGuid);
        json.put("stepName", stepName);
        json.put("stepDuration", stepDuration);
        json.put("nominalTemperature", nominalTemperature);
        HttpPost request = new HttpPost(url);
        StringEntity params = new StringEntity(json.toString());
        request.addHeader("content-type", "application/json");
        request.addHeader("Accept", "application/json");
        request.setEntity(params);

        try (CloseableHttpResponse response = httpClient.execute(request)) {
            HttpEntity entity = response.getEntity();
            String content = EntityUtils.toString(entity, StandardCharsets.UTF_8);
            return content;
        }
    }

    /*
     * List all the programs for a machine on the server. The MachineGuid is a required request perameter GET REQUEST.
     * */

    @ThingworxServiceDefinition(name = "listPrograms", description = "Lists all the Programs on the server")
    @ThingworxServiceResult(name = "result", description = "returns a JSON of Programs", baseType = "JSON")

    public JSONArray listPrograms(@ThingworxServiceParameter(name = "machineGuid", description = "The GUID of the machine", baseType = "STRING") String machineGuid) throws Exception {
        String path = "/listPrograms?machineGuid=" + machineGuid;
        String url = this.protocol + this.domain + ":" + this.port + path;
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);
        request.addHeader("content-type", "application/json");
        request.addHeader("Accept", "application/json");

        try (CloseableHttpResponse response = httpClient.execute(request)) {
            HttpEntity entity = response.getEntity();
            String content = EntityUtils.toString(entity, StandardCharsets.UTF_8);
            JSONArray jsonArray = new JSONArray(content);
            return jsonArray;
        }
    }

    /*
     * his request starts a program operating on a machine. The process values are then saved every second and can be later displayed on a chart.
     * The service requires the inputs of the programGuid and the batch name. These inputs are both given as request perameters.  POST REQUEST
     * */

    @ThingworxServiceDefinition(name = "startProgram", description = "Start a program")
    @ThingworxServiceResult(name = "result", description = "returns a JSON of Programs", baseType = "JSON")

    public String startProgram(@ThingworxServiceParameter(name = "programGuid", description = "The GUID of the program", baseType = "STRING") String programGuid,
                               @ThingworxServiceParameter(name = "batchName", description = "The name of the batch", baseType = "STRING") String batchName) throws Exception {
        String path = "/StartProgram?programGuid=" + programGuid + "&batchName=" + batchName;
        String url = this.protocol + this.domain + ":" + this.port + path;
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost(url);
        request.addHeader("content-type", "application/json");
        request.addHeader("Accept", "application/json");


        try (CloseableHttpResponse response = httpClient.execute(request)) {
            HttpEntity entity = response.getEntity();
            String content = EntityUtils.toString(entity, StandardCharsets.UTF_8);
            return content;
        }
    }

    /*
     * This function pauses program. The program has to be running for this fuction to work.
     * The fuction requires programGuid as request perameter. POST REQUEST
     * */

    public String pauseProgram(@ThingworxServiceParameter(name = "programGuid", description = "The GUID of the program", baseType = "STRING") String programGuid) throws Exception {
        String path = "/PauseProgram?programGuid=" + programGuid;
        String url = this.protocol + this.domain + ":" + this.port + path;
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost(url);
        request.addHeader("content-type", "application/json");
        request.addHeader("Accept", "application/json");

        try (CloseableHttpResponse response = httpClient.execute(request)) {
            HttpEntity entity = response.getEntity();
            String content = EntityUtils.toString(entity, StandardCharsets.UTF_8);
            return content;
        }
    }

    /*
     * This function continues a paused program. The program has to be previously paused for this fuction to work.
     * The fuction requires programGuid as request perameter. POST REQUEST
     * */

    public String continueProgram(@ThingworxServiceParameter(name = "programGuid", description = "The GUID of the program", baseType = "STRING") String programGuid) throws Exception {
        String path = "/ContinueProgram?programGuid=" + programGuid;
        String url = this.protocol + this.domain + ":" + this.port + path;
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost(url);
        request.addHeader("content-type", "application/json");
        request.addHeader("Accept", "application/json");

        try (CloseableHttpResponse response = httpClient.execute(request)) {
            HttpEntity entity = response.getEntity();
            String content = EntityUtils.toString(entity, StandardCharsets.UTF_8);
            return content;
        }
    }

    /*
     * This fuction stops a program from continuing. This can be performed when the program is running or when the program is paused.
     * Afterwards the machines process data will continue running until the machine reaches room tempreture again.
     * The fuction requires programGuid as request perameter. POST REQUEST
     * */

    public String stopProgram(@ThingworxServiceParameter(name = "programGuid", description = "The GUID of the program", baseType = "STRING") String programGuid) throws Exception {
        String path = "/StopProgram?programGuid=" + programGuid;
        String url = this.protocol + this.domain + ":" + this.port + path;
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost(url);
        request.addHeader("content-type", "application/json");
        request.addHeader("Accept", "application/json");

        try (CloseableHttpResponse response = httpClient.execute(request)) {
            HttpEntity entity = response.getEntity();
            String content = EntityUtils.toString(entity, StandardCharsets.UTF_8);
            return content;
        }
    }


    /*
     * Instead of showing the instantanous process data as is the case when calling the GetProcessValues function,
     * this call shows the process values of the machine.
     * The fuction requires machineGuid as request perameter. GET REQUEST
     * */

    @ThingworxServiceDefinition(name = "MachineDataStream", description = "Get the process values of a machine")
    @ThingworxServiceResult(name = "result", description = "returns a JSON of process values", baseType = "JSON")

    public String MachineDataStream(@ThingworxServiceParameter(name = "machineGuid", description = "The GUID of the machine", baseType = "STRING") String machineGuid) throws Exception {
        String path = "/MachineDataStream?machineGuid=" + machineGuid;
        String url = this.protocol + this.domain + ":" + this.port + path;
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);
        request.addHeader("content-type", "application/json");
        request.addHeader("Accept", "application/json");

        try (CloseableHttpResponse response = httpClient.execute(request)) {
            HttpEntity entity = response.getEntity();
            String content = EntityUtils.toString(entity, StandardCharsets.UTF_8);
            return content;
        }
    }

}

