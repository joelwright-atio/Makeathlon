package de.atio;

import com.ghgande.j2mod.modbus.io.ModbusTCPTransaction;
import com.ghgande.j2mod.modbus.msg.*;
import com.ghgande.j2mod.modbus.net.TCPMasterConnection;
import com.ghgande.j2mod.modbus.procimg.InputRegister;
import com.ghgande.j2mod.modbus.procimg.Register;
import com.serotonin.modbus4j.ModbusFactory;
import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.code.DataType;
import com.serotonin.modbus4j.code.RegisterRange;
import com.serotonin.modbus4j.exception.ErrorResponseException;
import com.serotonin.modbus4j.exception.ModbusInitException;
import com.serotonin.modbus4j.exception.ModbusTransportException;
import com.serotonin.modbus4j.ip.IpParameters;
import com.serotonin.modbus4j.locator.BaseLocator;
import com.thingworx.metadata.annotations.ThingworxServiceParameter;
import de.re.easymodbus.exceptions.ModbusException;
import de.re.easymodbus.modbusclient.ModbusClient;
import org.eclipse.paho.client.mqttv3.*;
import org.json.JSONException;
import org.json.JSONObject;
import com.serotonin.modbus4j.base.KeyedModbusLocator;

import java.io.IOException;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

public class TestModbus {

    public static void main (String[] args) throws IOException, ModbusException, MqttException, InterruptedException {
        //readModbusTCP(2);
        //MQTTTest();
        //readModbusTCP(7025);
        System.out.print(GetPositionInJSON());

    }

    private static JSONObject GetPositionInJSON() {


        TCPMasterConnection con = null; //the conection
        ModbusTCPTransaction trans = null; //the transaction
        //ReadMultipleRegistersRequest req = null; //the request
        //ReadMultipleRegistersResponse res = null; //the response
        ReadInputRegistersRequest req = null; //the request
        ReadInputRegistersResponse res = null; //the response
        /* Variables for storing the parameters */
        InetAddress addr = null; //the slave's address

        JSONObject result = new JSONObject();

        int port = 502;
        //int ref = Integer.parseInt("011E", 16); //the reference; offset where to start reading from
        int ref = 7015;
        int count = 6; //the number of DI's to read
        int repeat = 1; //a loop for repeating the transaction
        try {
            addr = InetAddress.getByName("169.254.69.120");
            //2. Open the connection
            con = new TCPMasterConnection(addr);

            con.setPort(port);
            con.connect();
            //req = new ReadMultipleRegistersRequest(ref, count);
            req = new ReadInputRegistersRequest(ref, count);
            //req = new
            trans = new ModbusTCPTransaction(con);

            List<Integer> inputRegisters = new ArrayList<>();
            for (int a = 0; a < 6; a++) {
                req.setUnitID(a);
                trans.setRequest(req);
                trans.execute();
                res = (ReadInputRegistersResponse) trans.getResponse();

                int output = res.getRegisterValue(a);
                inputRegisters.add(output);

                //System.out.println("The output is" + output);

            }
            //6. Close the connection
            con.close();

            for (int i =0; i<3; i++) {
                int intValue = (inputRegisters.get(2 * i) << 16) | inputRegisters.get(2 * i + 1);


                float floatValue = ByteBuffer.wrap(new byte[]{(byte) ((intValue >> 24) & 0xFF),
                        (byte) ((intValue >> 16) & 0xFF),
                        (byte) ((intValue >> 8) & 0xFF),
                        (byte) ((intValue & 0xFF))})
                .order(ByteOrder.BIG_ENDIAN)
                .getFloat();
                //System.out.println("Int Value: " + intValue);
                //float floatValue = Float.intBitsToFloat(intValue);

                //System.out.println("Float Value: " + floatValue);
                double doubleValue = floatValue;


                if(i==0){
                    try {
                        result.put("x", doubleValue);
                    } catch (JSONException e){
                        // non-finite number problem
                        System.out.println("X is non-finite");
                    }
                }
                if(i==1) {
                    try {
                        result.put("y", doubleValue);
                    } catch (JSONException e) {
                        // non-finite number problem
                        System.out.println("Y is non-finite");
                    }
                }
                if(i==2) {
                    try {
                        result.put("z", doubleValue);
                    } catch (JSONException e) {
                        // non-finite number problem
                        System.out.println("Z is non-finite");
                    }
                }

            }
            //System.out.println(result.toString(4));


        } catch (Exception ex) {
            //Logger.getLogger(ModbusTest.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Exception: " + ex.getMessage());
        }
        return result;
    }

    private static void testModbus4J() {
        IpParameters params = new IpParameters();
        params.setHost("localhost");
        params.setPort(502);

        ModbusMaster master = new ModbusFactory().createTcpMaster(params, false);

        try {
            master.init();

            BaseLocator loc = BaseLocator.holdingRegister(1, 0, DataType.TWO_BYTE_INT_SIGNED);


            // Get the point value
            System.out.println(master.getValue(loc));

        } catch (ModbusInitException e) {
            throw new RuntimeException(e);
        } catch (ModbusTransportException e) {
            throw new RuntimeException(e);
        } catch (ErrorResponseException e) {
            throw new RuntimeException(e);
        }
    }

    public static JSONObject readModbusTCP(int startingAddress) throws IOException, ModbusException {
        // read holding register
        ModbusClient modbusClient = new ModbusClient();
        //modbusClient.Connect("192.168.2.33", 502);
        modbusClient.Connect("169.254.69.120", 502);
        int[] inputRegisters = modbusClient.ReadInputRegisters(startingAddress, 6);
        //int[] inputRegisters = modbusClient.ReadInputRegisters(7860, 1);
        for (int inputRegister : inputRegisters) {
            System.out.println(inputRegister);
        }

        JSONObject result = new JSONObject();

        for (int i =0; i<3; i++) {
            int intValue = (inputRegisters[2*i] << 16) | inputRegisters[2*i+1];

/*
            float floatValue = ByteBuffer.wrap(new byte[]{(byte) ((intValue >> 24) & 0xFF),
                            (byte) ((intValue >> 16) & 0xFF),
                            (byte) ((intValue >> 8) & 0xFF),
                            (byte) ((intValue & 0xFF))})
                    .order(ByteOrder.BIG_ENDIAN)
                    .getFloat();*/
            System.out.println("Int Value: " + intValue);
            float floatValue = Float.intBitsToFloat(intValue);



            System.out.println("Float Value: " + floatValue);
            double doubleValue = floatValue;


            if(i==0){
                try {
                    result.put("x", doubleValue);
                } catch (JSONException e){
                    // non-finite number problem
                    System.out.println("X is non-finite");
                }
            }
            if(i==1) {
                try {
                    result.put("y", doubleValue);
                } catch (JSONException e) {
                    // non-finite number problem
                    System.out.println("Y is non-finite");
                }
            }
            if(i==2) {
                try {
                    result.put("z", doubleValue);
                } catch (JSONException e) {
                    // non-finite number problem
                    System.out.println("Z is non-finite");
                }
            }


        }
        System.out.println(result.toString(4));
        return result;

    }

    public static void MQTTTest() throws MqttException, InterruptedException {
        String broker = "tcp://192.168.10.1:1883";
        String clientId = "SimpleMqttClient";
        String topic = "cmtk/cmtkv1/#";


        MqttClient client = new MqttClient(broker, clientId);

        MqttConnectOptions connOpts = new MqttConnectOptions();
        connOpts.setCleanSession(true);

        System.out.println("Connecting to broker: " + broker);
        client.connect(connOpts);
        System.out.println("Connected");

        // Subscribe to a topic
        client.subscribe(topic);

        // Set up a callback for incoming messages
        client.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable cause) {
                System.out.println("Connection lost");
            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                System.out.println("Received message from topic '" + topic + "': " + new String(message.getPayload()));
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {
                // Not used in this example
            }
        });

        // Publish a message
      /*  String payload = "Hello, MQTT!";
        MqttMessage message = new MqttMessage(payload.getBytes());
        client.publish(topic, message);*/

        // Wait for some time to receive messages
        Thread.sleep( 5000);

        // Disconnect from the broker
        client.disconnect();
        System.out.println("Disconnected");

    }

}
