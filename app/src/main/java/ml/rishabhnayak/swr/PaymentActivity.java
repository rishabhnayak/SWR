package ml.rishabhnayak.swr;

import android.support.v7.app.AppCompatActivity;
import android.app.Activity;

import cn.pedant.SweetAlert.SweetAlertDialog;
import instamojo.library.InstapayListener;
import instamojo.library.InstamojoPay;
import instamojo.library.Config;
import ml.rishabhnayak.swr.POJO.Subject;

import org.json.JSONObject;
import org.json.JSONException;
import android.content.IntentFilter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//public class PaymentActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    public class PaymentActivity extends AppCompatActivity{
    Spinner spinner;
    String main_response;
    EditText name, email, mobile,purpose,amount;
    String  nameS, emailS, mobileS, amountS,purposeS;

    private void callInstamojoPay(String email, String phone, String amount, String purpose, String buyername) {
        final Activity activity = this;
        InstamojoPay instamojoPay = new InstamojoPay();
        IntentFilter filter = new IntentFilter("ai.devsupport.instamojo");
        registerReceiver(instamojoPay, filter);
        JSONObject pay = new JSONObject();
        try {
            pay.put("email", email);
            pay.put("phone", phone);
            pay.put("purpose", purpose);
            pay.put("amount", amount);
            pay.put("name", buyername);
            pay.put("send_sms", true);
            pay.put("send_email", true);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        initListener();
        instamojoPay.start(activity, pay, listener);
    }

    InstapayListener listener;


    private void initListener() {
        listener = new InstapayListener() {
            @Override
            public void onSuccess(String response) {
                Toast.makeText(getApplicationContext(), "Payment Successful", Toast.LENGTH_LONG)
                        .show();

//                new SweetAlertDialog(getApplicationContext(), SweetAlertDialog.SUCCESS_TYPE)
//                        .setTitleText("Payment Successful!")
//                        .show();

            }

            @Override
            public void onFailure(int code, String reason) {
                Toast.makeText(getApplicationContext(), "Failed: " + reason, Toast.LENGTH_LONG)
                        .show();
//
//
//                new SweetAlertDialog(getApplicationContext(), SweetAlertDialog.WARNING_TYPE)
//                        .setTitleText("Payment Failed!")
//                        .show();
            }
        };
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        // Call the function callInstamojo to start payment here
//        volley();
//        setId();

     name=findViewById(R.id.name);
     email=findViewById(R.id.email);
     mobile=findViewById(R.id.mobile_no);
     purpose=findViewById(R.id.purpose);
     amount=findViewById(R.id.amount);



    }


//    @Override
//    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        // On selecting a spinner item
//        String item = parent.getItemAtPosition(position).toString();
//        // Showing selected spinner item
//        selected=item;
//        Subject[] subject = new Gson().fromJson(main_response, Subject[].class);
//        List<Subject> main_list= new ArrayList<Subject>(Arrays.asList(subject));
//        amount =main_list.get(position).getPrice();
//        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
//    }
//
//    @Override
//    public void onNothingSelected(AdapterView<?> parent) {
//        Toast.makeText(this, "nothing selected", Toast.LENGTH_SHORT).show();
//    }
//
//
//    public void volley(){
//        String url= "http://raysitworld.com/course";
//        RequestQueue queue= Volley.newRequestQueue(this);
//        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
//                new Response.Listener<String>()
//                {
//
//                    @Override
//                    public void onResponse(String response) {
//                        System.out.println("yhi hai response....."+response);
//                        main_response = response;
//                        subjectSelect();
//                    }
//
//                },
//                new Response.ErrorListener()
//                {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        System.out.println("volley error"+error);
//                        volley();
//                        Toast.makeText(getApplicationContext(), "Server Error please Retry", Toast.LENGTH_SHORT).show();
//                    }
//                }
//        ) {
////            @Override
////            protected Map<String, String> getParams() throws AuthFailureError {
////                HashMap<String,String> map=new HashMap<>();
////
////                return map;
////            }
//        };
//
//        queue.add(postRequest);
//    }
//
//    public void subjectSelect(){
//        // Spinner element
//        spinner = (Spinner) findViewById(R.id.spinner);
//
//        // Spinner click listener
//        spinner.setOnItemSelectedListener(this);
//        // Creating adapter for spinner
//        Subject[] subject = new Gson().fromJson(main_response, Subject[].class);
//        List<Subject> main_list= new ArrayList<Subject>(Arrays.asList(subject));
//        List<String> categories = new ArrayList<String>();
//        for(int i=0;i<main_list.size();i++){
//            categories.add(main_list.get(i).getCourse_name());
//        }
//        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,categories );
//        // Drop down layout style - list view with radio button
//        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//        // attaching data adapter to spinner
//        spinner.setAdapter(dataAdapter);
//    }
//
    public void pay(View view) {
        nameS=name.getText().toString();
        emailS=email.getText().toString();
        mobileS=mobile.getText().toString();
        purposeS=purpose.getText().toString();
        amountS=amount.getText().toString();
//        Toast.makeText(getApplicationContext(), emailS, Toast.LENGTH_SHORT).show();
//        Toast.makeText(getApplicationContext(), nameS, Toast.LENGTH_SHORT).show();
//        Toast.makeText(getApplicationContext(), amount, Toast.LENGTH_SHORT).show();
//        Toast.makeText(getApplicationContext(), selected, Toast.LENGTH_SHORT).show();
        callInstamojoPay(emailS,mobileS,amountS,purposeS,nameS);


    }
}
//    public void setId(){
//     name=findViewById(R.id.name);
//     email=findViewById(R.id.email);
//     mobile=findViewById(R.id.mobile_no);
//    }
//    public void getString(){
//        nameS=name.getText().toString();
//        emailS=email.getText().toString();
//        mobileS=mobile.getText().toString();
//    }
//}
