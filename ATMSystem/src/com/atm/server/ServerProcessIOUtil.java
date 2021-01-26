package com.atm.server;

import com.atm.entity.CommonAtmVO;
import com.atm.entity.CommonResult;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * 策略选择
 */
public class ServerProcessIOUtil {
    public ServerProcessIOUtil(Socket socket){
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
            CommonAtmVO commonAtmVO=(CommonAtmVO)ois.readObject();
            if (commonAtmVO!=null){
                ProcessContext pc = new ProcessContext();
                //调用策略选择方法
                CommonResult commonResult = pc.delStrategy(commonAtmVO);
                oos.writeObject(commonResult);
                oos.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
                try {
                    if (ois!=null || oos!=null){
                    ois.close();
                    oos.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
}
