package com.whinc.test.recyclerviewcomponent;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.whinc.recyclerview.itemdecoration.LinearItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<String> data = new ArrayList<>();
        for (int i = 0; i < 100; ++i) {
            data.add(String.valueOf(i));
        }

        // Vertical List
        RecyclerView verticalList = (RecyclerView) findViewById(R.id.vertical_list_recycler_view);
        verticalList.setLayoutManager(new LinearLayoutManager(this));
        verticalList.setAdapter(new MyAdapter(data));
        RecyclerView.ItemDecoration itemDecoration = LinearItemDecoration.builder()
                .color(getResources().getColor(android.R.color.holo_blue_light))
                .create();
        verticalList.addItemDecoration(itemDecoration);
        verticalList.requestDisallowInterceptTouchEvent(true);

        // Horizontal List
        RecyclerView horizontalList = (RecyclerView) findViewById(R.id.horizontal_list_recycler_view);
        horizontalList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        horizontalList.setAdapter(new MyAdapter(data));
        RecyclerView.ItemDecoration itemDecoration2 = LinearItemDecoration.builder()
                .color(getResources().getColor(android.R.color.holo_green_light))
                .orientation(RecyclerView.HORIZONTAL)
                .top(20)
                .bottom(30)
                .left(40)
                .right(50)
                .horizontalDividerSpace(20)
                .create();
        horizontalList.addItemDecoration(itemDecoration2);

        // Vertical Grid
        RecyclerView verticalGrid = (RecyclerView) findViewById(R.id.vertical_grid_recycler_view);
        verticalGrid.setLayoutManager(new GridLayoutManager(this, 3));
        verticalGrid.setAdapter(new MyAdapter(data));
        RecyclerView.ItemDecoration itemDecoration3 = LinearItemDecoration.builder()
                .color(getResources().getColor(android.R.color.holo_red_light))
                .orientation(RecyclerView.VERTICAL)
                .create();
        verticalGrid.addItemDecoration(itemDecoration3);
        verticalGrid.requestDisallowInterceptTouchEvent(true);

        // Horizontal Grid
        RecyclerView horizontalGrid = (RecyclerView) findViewById(R.id.horizontal_grid_recycler_view);
        GridLayoutManager horizontalGridLayout = new GridLayoutManager(this, 3, RecyclerView.HORIZONTAL, false);
        horizontalGrid.setLayoutManager(horizontalGridLayout);
        horizontalGrid.setAdapter(new MyAdapter(data));
        RecyclerView.ItemDecoration itemDecoration4 = LinearItemDecoration.builder()
                .color(getResources().getColor(android.R.color.holo_orange_light))
                .top(20)
                .left(30)
                .right(40)
                .bottom(50)
                .horizontalDividerSpace(20)
                .verticalDividerSpace(50)
                .layoutManger(horizontalGridLayout)
                .create();
        horizontalGrid.addItemDecoration(itemDecoration4);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    static class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

        private List<String> mData = new ArrayList<>();

        public MyAdapter(@NonNull List<String> data) {
            mData.addAll(data);
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
            View view = inflater.inflate(R.layout.item, viewGroup, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int i) {
            viewHolder.itemTxt.setText(mData.get(i));
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        static class ViewHolder extends RecyclerView.ViewHolder {
            TextView itemTxt;

            public ViewHolder(View itemView) {
                super(itemView);
                itemTxt = (TextView) itemView.findViewById(R.id.text1);
            }
        }
    }
}
