package com.dimon.bashorg;

import com.dimon.bashorg.net.Downloader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by dsv on 24.09.17.
 */
public class DownloaderImpl implements Downloader {

    public String download(final String url) {


        URL bashUrl;
        InputStream is = null;
        BufferedReader br;
        String line;
        String result = "";

        try {
            bashUrl = new URL("http://bash.im");
            is = bashUrl.openStream();  // throws an IOException
            br = new BufferedReader(new InputStreamReader(is, "cp1251"));

            while ((line = br.readLine()) != null) {
                result = result + line;
            }
        } catch (MalformedURLException mue) {
            mue.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try {
                if (is != null) is.close();
            } catch (IOException ioe) {
            }
        }

        return result;
    }
}
