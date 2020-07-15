package com.kekxv;

import se.vidstige.jadb.JadbConnection;
import se.vidstige.jadb.JadbDevice;
import se.vidstige.jadb.JadbException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class jadb_logcat {
    public static void main(String[] args) {
        JadbConnection jadb = new JadbConnection();
        List<JadbDevice> devices = null;
        try {
            devices = jadb.getDevices();
            if (devices.isEmpty()) throw new Exception("没有设备");
            JadbDevice jadbDevice = devices.get(0);
            InputStream in = jadbDevice.execute("logcat -d -v time ");
            Thread.sleep(100);
            while (in.available() > 0) {
                byte[] c = new byte[in.available()];
                int ret = in.read(c);
                System.out.println(new String(c, 0, ret));
                Thread.sleep(10);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JadbException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
