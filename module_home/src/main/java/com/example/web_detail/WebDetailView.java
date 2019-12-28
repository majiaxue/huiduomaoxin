package com.example.web_detail;

import android.net.Uri;

import com.example.mvp.IView;

public interface WebDetailView extends IView {
    void loadUri(Uri uri);
}
