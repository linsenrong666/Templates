package com.linsr.scancodelib.zxing.camera;

public interface PreviewFrameShotListener {
	public void onPreviewFrame(byte[] data, Size frameSize);
}
