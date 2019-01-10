package com.example.hlamm.bkolsberg;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Downloader  {

    String address;

    public Downloader(String address) {
        this.address = address;
    }


    protected String downloadData()
    {
        //connect and get a stream
        InputStream is=null;
        String line =null;

        try {
                URL url=new URL(address);
                HttpURLConnection con= (HttpURLConnection) url.openConnection();
                is=new BufferedInputStream(con.getInputStream());

                BufferedReader br=new BufferedReader(new InputStreamReader(is));

                StringBuffer sb=new StringBuffer();

                if(br != null)
                {
                    while ((line=br.readLine()) != null)
                    {
                        sb.append(line+"\n");
                    }

                }else {
                    return null;
                }

                return sb.toString();
            }
        catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(is != null)
            {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}