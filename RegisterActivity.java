package com.cicciopasticcio.chiappette;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class RegisterActivity extends AsyncTask<String,Void,String>{
    TextView roleField;
    private Context context;

    public RegisterActivity(Context context,TextView roleField) {
        this.roleField = roleField;
        this.context = context;

    }
    @Override
    protected String doInBackground(String... arg0) {
        try {
            String name = (String) arg0[0];
            String nascita = (String) arg0[1];
            String username = (String) arg0[2];
            String password = (String) arg0[3];
            String mail = (String) arg0[4];


            String link = "http://trains.altervista.org/SignUp.php";
            String dati = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8");
            dati += "&" + URLEncoder.encode("nascita", "UTF-8") + "=" + URLEncoder.encode(nascita, "UTF-8");
            dati += "&" + URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8");
            dati += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
            dati += "&" + URLEncoder.encode("mail", "UTF-8") + "=" + URLEncoder.encode(mail, "UTF-8");

            URL url = new URL(link);
            URLConnection conn = url.openConnection();

            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

            wr.write(dati);
            wr.flush();

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            StringBuilder sb = new StringBuilder();
            String line = null;

            // Read Server Response
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                break;
            }
            return sb.toString();
        } catch (Exception e) {
            return new String("Exception: " + e.getMessage());
        }
    }
    protected void onPostExecute(String result){
        this.roleField.setText(result);
    }
}
