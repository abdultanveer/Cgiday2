package com.example.cgiday2;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

public class DownloadTask extends AsyncTask<String,Integer,Boolean> {//inputtype,progresstype,resulttype
    static String TAG = DownloadTask.class.getSimpleName();

    ProgressBar progressBar;
    public DownloadTask(ProgressBar mProgressBar) {
        progressBar = mProgressBar;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override// this method will execute in the bckground thread//2
    protected Boolean doInBackground(String... strings) {
        Log.i(TAG,"url downloaded = "+strings[0]);
        for(int i=1; i<20;i++){
            try {
                Thread.sleep(300);
                publishProgress(new Integer(i*5));//3


            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
        return true;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onProgressUpdate(Integer... i) {
        //super.onProgressUpdate(i);
        progressBar.setProgress(i[0]);
    }
}
