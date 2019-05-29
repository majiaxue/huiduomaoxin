package com.example.assess_detail;

import com.example.assess_detail.adapter.InsideAssessAdapter;
import com.example.assess_detail.adapter.InsideImageAdapter;
import com.example.mvp.IView;

public interface AssessDetailView extends IView {

    void loadImg(InsideImageAdapter adapter);

    void loadInsideAssess(InsideAssessAdapter adapter);

    void zan();

    void cancelZan();
}
