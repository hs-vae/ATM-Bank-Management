package com.atm.service;

import com.atm.entity.CommonAtmVO;
import com.atm.entity.CommonResult;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * ¿Í»§¶Ë
 */
public class ClientService {
    public CommonResult clientSendObject(CommonAtmVO commonAtmVO){
        CommonResult commonResult = new CommonResult();
        Socket clientSocket = null;
        try {
            clientSocket = new Socket("localhost",2021);
            ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
            oos.writeObject(commonAtmVO);
            oos.flush();
            commonResult = (CommonResult) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return commonResult;
    }
}
