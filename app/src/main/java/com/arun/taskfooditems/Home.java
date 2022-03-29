package com.arun.taskfooditems;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;

import java.util.Arrays;
import java.util.List;

public class Home extends AppCompatActivity {

    String food[] = {"Bread", "Burger", "Chicken Nuggets", "Cookies", "Donuts",
            "French Fries", "Fried Rice", "Ice Cream", "Muffins", "Pan Cakes",
            "Pasta", "Pizza", "Ribs", "Salads", "Steak", "Tacos"};
    int foodImg[] = {R.drawable.bread, R.drawable.burger, R.drawable.chickennuggets, R.drawable.cookies, R.drawable.donuts,
            R.drawable.fenchfries,R.drawable.fenchfries,R.drawable.icecream,R.drawable.muffins,R.drawable.pancakes,
            R.drawable.pasta,R.drawable.pizza,R.drawable.ribs,R.drawable.salad,R.drawable.steak,R.drawable.tacos};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        List al = Arrays.asList(food);//array to array list


        GridView gridViewFood=(GridView) findViewById(R.id.gridv);
        CusAdapFood adapter=new CusAdapFood(getApplicationContext(),al,foodImg);
        gridViewFood.setAdapter(adapter);


        SearchView searchView=(SearchView) findViewById(R.id.searchView);
        searchView.setQueryHint("Search by food items");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if(al.contains(s)){
                    adapter.getFilter().filter(s);
                }else {
                    Toast.makeText(Home.this, "No match found", Toast.LENGTH_SHORT).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });




        gridViewFood.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent ii =new Intent(getApplicationContext(),DetailedPage.class);
                int img =foodImg[i];
                String fud=food[i];
                ii.putExtra("img",img);
                ii.putExtra("food",fud);
                startActivity(ii);
            }
        });

    }
}