package com.xdja.xduideom.basecomponent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.xdja.xdui.XDUISearchBar;
import com.xdja.xduideom.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchBarActivity extends AppCompatActivity {

    @BindView(R.id.searchBar)
    XDUISearchBar searchBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        searchBar.addLeftBackImageButton()
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });

    }
}
