package com.example.dusan.movieinfo.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dusan.movieinfo.R;
import com.example.dusan.movieinfo.data.model.CrewMember;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import java.io.InputStream;
import java.net.URL;

@EViewGroup(R.layout.view_item_crew) public class CrewItemView extends RelativeLayout {

    private static final String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/w185";
    @ViewById TextView crewName;
    @ViewById TextView department;
    @ViewById TextView job;
    @ViewById ImageView crewImage;

    public CrewItemView(Context context) {
        super(context);
    }

    public CrewItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CrewItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void bind(CrewMember crewMember) {
        this.crewName.setText(crewMember.getName());
        this.department.setText(crewMember.getDepartment());
        this.job.setText(crewMember.getJob());
        ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting()) {
            new DownloadImageTask(crewImage, crewMember.getProfileUrl()).execute();
        } else {
            crewImage.setImageResource(R.mipmap.placeholder);
        }
    }

    @SuppressLint("StaticFieldLeak") private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView crewImage;
        String url;

        DownloadImageTask(ImageView crewImage, String url) {
            this.crewImage = crewImage;
            this.url = url;
        }

        protected Bitmap doInBackground(String... urls) {
            Bitmap bitmap = null;
            try {
                InputStream in = new URL(IMAGE_BASE_URL.concat(url)).openStream();
                bitmap = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        protected void onPostExecute(Bitmap result) {
            crewImage.setImageBitmap(result);
        }
    }
}
