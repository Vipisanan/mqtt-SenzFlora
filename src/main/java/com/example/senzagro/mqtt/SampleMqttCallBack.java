package com.example.senzagro.mqtt;

import com.example.senzagro.model.Floradevice;
import com.example.senzagro.repository.Device;
import org.eclipse.paho.client.mqttv3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class SampleMqttCallBack implements MqttCallback {


    @Autowired
    Device device;


    private MqttClient client;

    @Override
    public void connectionLost(Throwable cause) {
        try {
            this.subscribe();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
            String message = new String(mqttMessage.getPayload()); //Pick the payload String
            System.out.println(message);
        Floradevice floradevice =new Floradevice();

        String[] records=message.split(";"); //Split device records '0-T:' + T + ',' + 'LI:' + L + ',' + 'CN:' + C + ',' + 'MAC:' + mac[x]


        for(String rec:records){
            System.out.println(rec);
            String[] id_and_datas =rec.split("-");
            //set id
            floradevice.setId(id_and_datas[0]);

            String[] datas = id_and_datas[1].split(",");
            for(String data:datas){
                String[] singledata = data.split(":");



                switch (singledata[0]){
                    case "T":
                        floradevice.setTemperature(singledata[1]);
                        break;
                    case "LI":
                        floradevice.setMi_light(singledata[1]);
                        break;
                    case "CN":
                        floradevice.setMi_conductivity(singledata[1]);
                        break;
                    case  "MAC" :
                        floradevice.setMac_id(singledata[1]);
                        break;
                    case "B":
                        floradevice.setMi_battery(singledata[1]);
                        break;
                    default:
                }

                device.save(floradevice);
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
            client.subscribe("SenzMate/D2S/senzflora-exhibition");

        client.setCallback(this);
    }
}
