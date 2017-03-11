package trans.miaoyongjun.ptf;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import miaoyongjun.pagetransformer.MagicTransformer;
import miaoyongjun.pagetransformer.TransitionEffect;

import static android.widget.RelativeLayout.CENTER_IN_PARENT;

public class ShowZoomActivity extends AppCompatActivity {

    public static TransitionEffect transitionEffect;

    String[] imageUrls = new String[]{
            "http://bmob-cdn-982.b0.upaiyun.com/2017/03/11/74ef38b3409b3eaf804f88a21b599d42.jpg",
            "http://bmob-cdn-982.b0.upaiyun.com/2017/02/24/f387251e4038bf4380169a6c5e5d64f9.jpg",
            "http://bmob-cdn-982.b0.upaiyun.com/2017/03/11/86f8a6bf40b27c9d80f5b3e4a733c0b1.jpg",
            "http://bmob-cdn-982.b0.upaiyun.com/2017/03/11/609a7ba94018c5858029c43b28a1d263.jpg"};
    List<View> mData = new ArrayList<View>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_show_zoom);
        ImageView bg = (ImageView) findViewById(R.id.bg);
        Picasso.with(this).load("http://bmob-cdn-982.b0.upaiyun.com/2017/03/11/6d13406f40d047e1801f140e6798eac0.jpeg")
                .into(bg);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        getLayoutData(mData);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams((int) (getResources().getDisplayMetrics().widthPixels * 0.8f),
                (int) (getResources().getDisplayMetrics().widthPixels * 0.8f));
        layoutParams.addRule(CENTER_IN_PARENT);
        viewPager.setLayoutParams(layoutParams);
        viewPager.setAdapter(new MyAdapter(mData));
        viewPager.setPageTransformer(true, MagicTransformer.getPageTransformer(transitionEffect));
        viewPager.setOffscreenPageLimit(imageUrls.length);
    }

    private void getLayoutData(List<View> data) {
        View view = LayoutInflater.from(this).inflate(R.layout.layout_item, null);
        for (String id : imageUrls) {
            data.add(view);
        }
    }

    public class MyAdapter extends PagerAdapter {

        List<View> mList = null;

        MyAdapter(List<View> list) {
            mList = list;
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = LayoutInflater.from(ShowZoomActivity.this).inflate(R.layout.layout_item_zoom, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
            Picasso.with(ShowZoomActivity.this).load(imageUrls[position]).into(imageView);
            container.addView(view);
            return view;
        }

    }
}
