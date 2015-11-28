/*
 * Decompiled with CFR 0_110.
 */
package com.dcmanproductions.vid_eo.Updater;

import com.dcmanproductions.vid_eo.Updater.UpdateInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.URL;

public class Updater
implements ActionListener {
    private static final String versionURL = "";
    private static final String historyURL = "";

    @Override
    public void actionPerformed(ActionEvent e) {
        UpdateInfo.update();
        try {
            System.out.println(Updater.getLatestVersion());
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static String getLatestVersion() throws Exception {
        String data = Updater.getData("");
        return data.substring(data.indexOf("[version]") + 9, data.indexOf("[/version]"));
    }

    public static String getWhatsNew() throws Exception {
        String data = Updater.getData("");
        return data.substring(data.indexOf("[history]") + 9, data.indexOf("[/history]"));
    }

    private static String getData(String address) throws Exception {
        URL url = new URL(address);
        InputStream html = null;
        html = url.openStream();
        int c = 0;
        StringBuffer buffer = new StringBuffer("");
        while (c != -1) {
            c = html.read();
            buffer.append((char)c);
        }
        return buffer.toString();
    }
}

