package com.example.senzagro.mqtt;

import com.example.senzagro.repository.Device;
import org.eclipse.paho.client.mqttv3.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

public class SampleMqttCallBack implements MqttCallback {


    @Autowired
    Device device;


    private MqttClient client;

    @Override
    public void connectionLost(Throwable cause) {

    }

    @Override
    public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
        String message = new String(mqttMessage.getPayload()); //Pick the payload String
        System.out.println(message);

        String[] records=message.split(";"); //Split device records 0-T:32.3,LI:30.0,......................
        for (String record:records){

            String[] rec = record.split(","); //Split a single device records 0-T:32.3
            for (String set:rec){
                //System.out.println(set);
                String[] data=set.split(":");
                //System.out.println(data[0]);
                switch (data[0]){
                    case "0-T":
//                        device=deviceRepository.findByDeviceId(data[1]);
                        break;
                    case "LI":
//                        tem.add(device.getTempareture());
//                        device.setTempareture(Double.parseDouble(data[1]));
                        break;
                    case "CN":
//                        device.setRespirationRate(Integer.parseInt(data[1]));
                        break;
                    case "MAC":
//                        device.setHeartRate(Integer.parseInt(data[1]));
                        break;
                    default:
                }
            }

        }


    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {

    }

    @PostConstruct
    public void subscribe() throws MqttException {

        System.out.println("== START SUBSCRIBER ==");

        client=new MqttClient("tcp://mqtt.senzmate.com:1883", "vipisanan");
        client.connect();
        client.subscribe("SenzMate/D2S/senzflora-A");

        client.setCallback(this);
    }
}
