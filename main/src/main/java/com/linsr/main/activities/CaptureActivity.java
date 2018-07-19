package com.linsr.main.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Vibrator;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.linsr.common.biz.ActivityEx;
import com.linsr.common.router.url.MainModule;
import com.linsr.main.R;
import com.linsr.scancodelib.zxing.camera.CameraManager;
import com.linsr.scancodelib.zxing.camera.PreviewFrameShotListener;
import com.linsr.scancodelib.zxing.camera.Size;
import com.linsr.scancodelib.zxing.decode.DecodeListener;
import com.linsr.scancodelib.zxing.decode.DecodeThread;
import com.linsr.scancodelib.zxing.decode.LuminanceSource;
import com.linsr.scancodelib.zxing.decode.PlanarYUVLuminanceSource;
import com.linsr.scancodelib.zxing.decode.RGBLuminanceSource;
import com.linsr.scancodelib.zxing.view.CaptureView;

@Route(path = MainModule.Activity.SCAN_CODE)
public class CaptureActivity extends ActivityEx implements SurfaceHolder.Callback,
        PreviewFrameShotListener, DecodeListener,
        OnCheckedChangeListener {

    private static final long VIBRATE_DURATION = 200L;
    public static final String EXTRA_RESULT = "result";

    private CaptureView captureView;
    private CheckBox flashCb;

    private CameraManager mCameraManager;
    private DecodeThread mDecodeThread;
    private Rect previewFrameRect = null;
    private boolean isDecoding = false;


    @Override
    protected int getLayoutId() {
        return R.layout.main_activity_scan_code;
    }

    @Override
    protected void initView() {
        SurfaceView previewSv = (SurfaceView) findViewById(R.id.sv_preview);
        captureView = (CaptureView) findViewById(R.id.cv_capture);
        flashCb = (CheckBox) findViewById(R.id.cb_capture_flash);
        flashCb.setOnCheckedChangeListener(this);
        flashCb.setEnabled(false);
        previewSv.getHolder().addCallback(this);
        mCameraManager = new CameraManager(this);
        mCameraManager.setPreviewFrameShotListener(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mCameraManager.initCamera(holder);
        if (!mCameraManager.isCameraAvailable()) {
            Toast.makeText(CaptureActivity.this, R.string.main_capture_camera_failed, Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        if (mCameraManager.isFlashlightAvailable()) {
            flashCb.setEnabled(true);
        }
        mCameraManager.startPreview();
        if (!isDecoding) {
            mCameraManager.requestPreviewFrameShot();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mCameraManager.stopPreview();
        if (mDecodeThread != null) {
            mDecodeThread.cancel();
        }
        mCameraManager.release();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onPreviewFrame(byte[] data, Size dataSize) {
        if (mDecodeThread != null) {
            mDecodeThread.cancel();
        }
        if (previewFrameRect == null) {
            previewFrameRect = mCameraManager.getPreviewFrameRect(captureView.getFrameRect());
        }
        PlanarYUVLuminanceSource luminanceSource = new PlanarYUVLuminanceSource(data, dataSize, previewFrameRect);
        mDecodeThread = new DecodeThread(luminanceSource, CaptureActivity.this);
        isDecoding = true;
        mDecodeThread.execute();
    }

    @Override
    public void onDecodeSuccess(Result result, LuminanceSource source, Bitmap bitmap) {
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(VIBRATE_DURATION);
        isDecoding = false;

        Intent resultData = new Intent();
        resultData.putExtra(EXTRA_RESULT, result.getText());
        setResult(RESULT_OK, resultData);
        finish();
    }

    @Override
    public void onDecodeFailed(LuminanceSource source) {
        if (source instanceof RGBLuminanceSource) {
            Toast.makeText(CaptureActivity.this, R.string.main_capture_decode_failed, Toast.LENGTH_SHORT).show();
        }
        isDecoding = false;
        mCameraManager.requestPreviewFrameShot();
    }

    @Override
    public void foundPossibleResultPoint(ResultPoint point) {
        captureView.addPossibleResultPoint(point);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            mCameraManager.enableFlashlight();
        } else {
            mCameraManager.disableFlashlight();
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
