package com.example.core.net.download;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;

import com.example.core.app.ProjectInit;
import com.example.core.net.callback.IRequest;
import com.example.core.net.callback.ISuccess;
import com.example.core.net.utils.file.FileUtil;

import java.io.File;
import java.io.InputStream;

import okhttp3.ResponseBody;

public class SaveFileTask extends AsyncTask<Object,Void, File> {

    private IRequest REQUEST;
    private ISuccess SUCCESS;

    public SaveFileTask(IRequest REQUEST, ISuccess SUCCESS) {
        this.REQUEST = REQUEST;
        this.SUCCESS = SUCCESS;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected File doInBackground(Object... params) {

        String downloadDir=(String)params[0];
        String extension=(String)params[1];
        ResponseBody body=(ResponseBody)params[2];
        String name=(String)params[3];
        InputStream is=body.byteStream();
        if(downloadDir==null || downloadDir.equals("")){
            downloadDir="downloads";
        }
        if(extension==null){
            extension="";
        }
        if(name==null){
            return FileUtil.writeToDisk(is,downloadDir,extension.toUpperCase(),extension);
        }else{
            return FileUtil.writeToDisk(is,downloadDir,name);
        }
    }

    //如果文件已经下完了
    @Override
    protected void onPostExecute(File file) {
        super.onPostExecute(file);
        if(SUCCESS!=null){
            SUCCESS.onSuccess(file.getPath());
        }
        if(REQUEST!=null){
            REQUEST.onRequestEnd();
        }
        //如果下了APK文件，就直接安装
        autoInstallApk(file);
    }


    private void autoInstallApk(File file) {
        if(FileUtil.getExtension(file.getPath()).equals("apk")){
            Intent intent=new Intent();
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setAction(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.fromFile(file),"application/vnd.android.package-archive");
            ProjectInit.getAppContext().startActivity(intent);
        }
    }
}












