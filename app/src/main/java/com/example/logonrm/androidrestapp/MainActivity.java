package com.example.logonrm.androidrestapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.logonrm.androidrestapp.adapter.AndroidAdapter;
import com.example.logonrm.androidrestapp.api.APIUtils;
import com.example.logonrm.androidrestapp.api.AndroidAPI;
import com.example.logonrm.androidrestapp.model.Android;
import com.example.logonrm.androidrestapp.model.ResponseAndroid;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AndroidAdapter androidAdapter;
    private AndroidAPI androidAPI;
    private List<Android> androids;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        androidAdapter = new AndroidAdapter(new ArrayList<Android>());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(androidAdapter);
        recyclerView.setHasFixedSize(true);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
    }

    private void carregarDados() {

        androidAPI = APIUtils.getAndroidApiService();

        androidAPI.getVersoes().enqueue(new Callback<ResponseAndroid>() {
            @Override
            public void onResponse(Call<ResponseAndroid> call, Response<ResponseAndroid> response) {
                androidAdapter.update(response.body().getAndroids());
                recyclerView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<ResponseAndroid> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Deu ruim",Toast.LENGTH_LONG).show();
                recyclerView.setVisibility(View.VISIBLE);
            }
        });

    }
}
