package com.example.hoosiers.singularitytwodolist;


/***
 *
 *
 *
 *
 */

    import android.os.AsyncTask;
    import android.util.Log;

    import org.json.JSONException;
    import org.json.JSONObject;

    import java.io.BufferedReader;
    import java.io.ByteArrayInputStream;
    import java.io.DataOutputStream;
    import java.io.InputStream;
    import java.io.InputStreamReader;
    import java.io.UnsupportedEncodingException;
    import java.net.MalformedURLException;
    import java.net.URL;
    import java.util.HashMap;
    import java.util.Map;

    import javax.net.ssl.HttpsURLConnection;

    /**
     * Created by brian on 7/19/16.
     */
    public abstract class TodoClient extends AsyncTask<String, Void, String> {

        private static final String BASE_URL = "https://xspfync7pf.execute-api.us-east-1.amazonaws.com/prod/";
        private static String authorization = "";
        private String path;
        private String id;
        private String method;
        private String payload;
        private String response;

        private URL getUrl() throws MalformedURLException {
            String endpoint = TodoClient.BASE_URL + path;
            if (id!=null) endpoint += "/" + id;
            return new URL(endpoint);
        }

        private TodoClient doGet() {
            this.method = "GET";
            return this;
        }

        private TodoClient doPut() {
            this.method = "PUT";
            return this;
        }

        private TodoClient doPost() {
            this.method = "POST";
            return this;
        }

        private TodoClient doDelete() {
            this.method = "DELETE";
            return this;
        }

        private TodoClient loginPath() {
            this.path = "login";
            return this;
        }

        private TodoClient pingPath() {
            this.path = "ping";
            return this;
        }

        private TodoClient todoPath() {
            this.path = "todo";
            return this;
        }

        private TodoClient with(String payload) {
            this.payload = payload;
            return this;
        }

        private TodoClient forId(String id) {
            this.id = id;
            return this;
        }

        public TodoClient login(String email, String password) {
            Map<String, String> credentials = new HashMap<String, String>();
            credentials.put("email", email);
            credentials.put("password", password);
            return login((new JSONObject(credentials)).toString());
        }

        public TodoClient login(String payload) {
            return doPost().loginPath().with(payload);
        }

        public TodoClient createTodo(String payload) {
            if (payload.startsWith("{")) {
                //create a stub. assume the payload is the name.
            }
            return doPost().todoPath().with(payload);
        }

        public TodoClient updateTodo(String payload) {
            return doPut().todoPath().with(payload);
        }

        public TodoClient getTodo(String id) {
            return doGet().todoPath().forId(id);
        }

        public TodoClient getTodos() {
            return doGet().todoPath();
        }

        public TodoClient deleteTodo(String id) {
            return doDelete().todoPath().forId(id);
        }

        public TodoClient ping() {
            return doGet().pingPath();
        }

        private void storeAuthorizationHeader(String s) {
            JSONObject json = null;
            try {
                json = new JSONObject(s);
                TodoClient.authorization = ((JSONObject) json.getJSONArray("Items").get(0)).getString("self");
                Log.d("TodoClient", "Authorize: " + TodoClient.authorization);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected String doInBackground(String... params) {
            HttpsURLConnection https = null;
            JSONObject responseJson = new JSONObject();
            try {
                https = (HttpsURLConnection) getUrl().openConnection();
                https.setRequestMethod(method);
                if (TodoClient.authorization.length()>0) {
                    https.setRequestProperty("Authorization", TodoClient.authorization);
                }
                https.setDoInput(true);
                if("POST".equalsIgnoreCase(method) || "PUT".equalsIgnoreCase(method)) {
                    https.setDoOutput(true);
                    // set the payload.
                    if (payload != null) {
                        https.setRequestProperty("Content-Type", "application/json");
                        https.setRequestProperty("Content-Length", "" + Integer.toString(payload.length()));
                        Log.d("TodoClient", payload);
                        DataOutputStream os = new DataOutputStream(https.getOutputStream());
                        os.writeBytes(payload);
                        os.flush();
                        os.close();
                    }
                } else {
                    https.setDoOutput(false);
                }
                https.connect();
                long responseCode = (long) https.getResponseCode();
                responseJson.put("responseCode", responseCode);

                if (responseCode==200) {
                    response = TodoClient.toStringFromInputStream(https.getInputStream());
                    responseJson.put("data", new JSONObject(response));
                }

                Log.d("TodoClient", "code: " + responseCode );
                if (response!=null) Log.d("TodoClient", "response: " + response );

                // special case for login response:
                if ("login".equalsIgnoreCase(path) && responseCode==200) {
                    storeAuthorizationHeader(response);
                }
            } catch(Exception e) {

                try {
                    responseJson.put("responseCode", 500);
                    responseJson.put("reason", e.getMessage());
                } catch (JSONException e1) {
                    e1.printStackTrace();
                }
                e.printStackTrace();
            }
            response = responseJson.toString();
            return response;
        }

        public static InputStream toInputStream(String s) {
            try {
                return new ByteArrayInputStream(s.getBytes("UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return null;
        }

        public static String toStringFromInputStream(InputStream in) {
            String s = "";
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                String line;
                while((line = reader.readLine()) != null) {
                    s += line;
                }
                reader.close();
            } catch (Exception ignore) {
                ignore.printStackTrace();
                // ignore
            }
            return s;
        }
    }

