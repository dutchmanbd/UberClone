package zxdmjr.com.uberclone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zxdmjr.com.uberclone.activities.CustomerLoginActivity;
import zxdmjr.com.uberclone.activities.RiderLoginActivity;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.iv_main_logo)
    ImageView ivLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        Animation anim = new ScaleAnimation(
                0.95f, 1f, // Start and end values for the X axis scaling
                0.95f, 1f, // Start and end values for the Y axis scaling
                Animation.RELATIVE_TO_SELF, 0.5f, // Pivot point of X scaling
                Animation.RELATIVE_TO_SELF, 0.5f); // Pivot point of Y scaling
        anim.setFillAfter(true); // Needed to keep the result of the animation
        anim.setDuration(2000);
        anim.setRepeatMode(Animation.INFINITE);
        anim.setRepeatCount(Animation.INFINITE);
        ivLogo.startAnimation(anim);


    }

    @OnClick(R.id.btn_main_rider)
    void gotoRider(){

        startActivity(new Intent(getApplicationContext(), RiderLoginActivity.class));
        finish();
        return;
    }

    @OnClick(R.id.btn_main_customer)
    void gotoCustomer(){

        startActivity(new Intent(getApplicationContext(), CustomerLoginActivity.class));
        finish();
        return;
    }

}
