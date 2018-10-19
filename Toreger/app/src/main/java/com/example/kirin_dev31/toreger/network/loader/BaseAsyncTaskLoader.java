package com.example.kirin_dev31.toreger.network.loader;

import android.content.AsyncTaskLoader;
import android.content.Context;

public abstract class BaseAsyncTaskLoader<D> extends AsyncTaskLoader<D> {

    private D mResult;

    private boolean mIsStarted = false;

    public BaseAsyncTaskLoader(Context context) {
        super(context);
    }

    @Override
    protected void onForceLoad() {
        super.onForceLoad();
        mIsStarted = true;
    }

    @Override
    public void deliverResult(D data) {
        super.deliverResult(data);
        mResult = data;
    }

    @Override
    protected void onStartLoading() {
        if (mResult != null) {
            deliverResult(mResult);
            return;
        }
        if (!mIsStarted || takeContentChanged()) {
            forceLoad();
        }
        super.onStartLoading();
    }
}
