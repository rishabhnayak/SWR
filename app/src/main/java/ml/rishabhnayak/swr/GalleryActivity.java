package ml.rishabhnayak.swr;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ml.rishabhnayak.swr.POJO.Gallery;

public class GalleryActivity  extends AppCompatActivity {
    private static final String URL="http://rishabhnayak.ml/sw.json";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pass_list_recycler);
        final RecyclerView link = (RecyclerView) findViewById(R.id.pass_list_recycler);
        link.setLayoutManager(new LinearLayoutManager(this));
        StringRequest request=new StringRequest(URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                System.out.println(response);
//                JSONObject object = null;
//                try {
//                    object = new JSONObject(response);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                JSONArray array=null;
//                try {
//                    array = object.getJSONArray("cars");
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
                System.out.println(response);
                Gallery[] imagee = new Gson().fromJson(response, Gallery[].class);
                link.setAdapter(new PnrPassengerListAdapter(getApplicationContext(), imagee));

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "somthing went wrong", Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue queue= Volley.newRequestQueue(this);
        queue.add(request);
    }
}
