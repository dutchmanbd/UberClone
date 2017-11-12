package zxdmjr.com.uberclone.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import zxdmjr.com.uberclone.R;

/**
 * Created by SWAJAN on 11/12/2017.
 */

public class RiderLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rider_login);

        ButterKnife.bind(this);
    }
}
