package com.example.caowj.newtask.example.activity;

import android.hardware.fingerprint.FingerprintManager;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.hardware.fingerprint.FingerprintManagerCompat;
import android.support.v4.os.CancellationSignal;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.caowj.newtask.R;
import com.example.caowj.newtask.example.helper.fingerPrint.CryptoObjectHelper;
import com.example.caowj.newtask.example.helper.fingerPrint.MyAuthCallback;
import com.kedacom.base.common.BaseHandler;
import com.kedacom.base.mvc.BaseActivity1;

/**
 * 指纹识别
 * https://blog.csdn.net/createchance/article/details/51991764
 */
public class TestFingerPrintActivity extends BaseActivity1 {


    private TextView mResultInfo = null;
    private Button mCancelBtn = null;
    private Button mStartBtn = null;

    private FingerprintManagerCompat fingerprintManager = null;
    private MyAuthCallback myAuthCallback = null;
    private CancellationSignal cancellationSignal = null;//用来在指纹识别器扫描用户指纹的时候取消当前的扫描操作,如果不取消的话，那么指纹扫描器会移植扫描直到超时（一般为30s，取决于具体的厂商实现）.

    private MyHandler handler = new MyHandler(this);
    public static final int MSG_AUTH_SUCCESS = 100;
    public static final int MSG_AUTH_FAILED = 101;
    public static final int MSG_AUTH_ERROR = 102;
    public static final int MSG_AUTH_HELP = 103;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_finger_print);

        mResultInfo = (TextView) this.findViewById(R.id.fingerprint_status);
        mCancelBtn = (Button) this.findViewById(R.id.cancel_button);
        mStartBtn = (Button) this.findViewById(R.id.start_button);

        mCancelBtn.setEnabled(false);
        mStartBtn.setEnabled(true);

        // set button listeners
        mCancelBtn.setOnClickListener(v -> {
            // set button state
            mCancelBtn.setEnabled(false);
            mStartBtn.setEnabled(true);

            // cancel fingerprint auth here.
            cancellationSignal.cancel();
            cancellationSignal = null;
        });

        mStartBtn.setOnClickListener(v -> {
            // reset result info.
            mResultInfo.setText(R.string.fingerprint_hint);
            mResultInfo.setTextColor(getResources().getColor(R.color.hint_color));

            // start fingerprint auth here.
            try {
                CryptoObjectHelper cryptoObjectHelper = new CryptoObjectHelper();
                if (cancellationSignal == null) {
                    cancellationSignal = new CancellationSignal();
                }
                //开始扫描用户按下的指纹
                fingerprintManager.authenticate(cryptoObjectHelper.buildCryptoObject(), 0,
                        cancellationSignal, myAuthCallback, null);
                // set button state.
                mStartBtn.setEnabled(false);
                mCancelBtn.setEnabled(true);
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(mActivity, "Fingerprint init failed! Try again!", Toast.LENGTH_SHORT).show();
            }
        });


        // init fingerprint.
        fingerprintManager = FingerprintManagerCompat.from(this);


        if (!fingerprintManager.isHardwareDetected()) {
            // no fingerprint sensor is detected, show dialog to tell user.
            // 未检测到指纹传感器，显示对话框告知用户。
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.no_sensor_dialog_title);
            builder.setMessage(R.string.no_sensor_dialog_message);
            builder.setIcon(android.R.drawable.stat_sys_warning);
            builder.setCancelable(false);
            builder.setNegativeButton(R.string.cancel_btn_dialog, (dialog, which) -> finish());
            // show this dialog.
            builder.create().show();
        } else if (!fingerprintManager.hasEnrolledFingerprints()) {
            // no fingerprint image has been enrolled.
            // 未登记指纹图像。
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.no_fingerprint_enrolled_dialog_title);
            builder.setMessage(R.string.no_fingerprint_enrolled_dialog_message);
            builder.setIcon(android.R.drawable.stat_sys_warning);
            builder.setCancelable(false);
            builder.setNegativeButton(R.string.cancel_btn_dialog, (dialog, which) -> finish());
            // show this dialog
            builder.create().show();
        } else {
            try {
                myAuthCallback = new MyAuthCallback(handler);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    private static class MyHandler extends BaseHandler<TestFingerPrintActivity> {


        public MyHandler(TestFingerPrintActivity referencedObject) {
            super(referencedObject);
        }

        @Override
        public void handleMessage2(Message msg, int what) {
            TestFingerPrintActivity reference = getWeakReference();
            Log.d("caowj", "msg: " + msg.what + " ,arg1: " + msg.arg1);
            String jsonStr = (String) msg.obj;
            switch (msg.what) {
                case MSG_AUTH_SUCCESS:
                    reference.setResultInfo(R.string.fingerprint_success);
                    reference.mCancelBtn.setEnabled(false);
                    reference.mStartBtn.setEnabled(true);
                    reference.cancellationSignal = null;
                    break;
                case MSG_AUTH_FAILED:
                    reference.setResultInfo(R.string.fingerprint_not_recognized);
                    reference.mCancelBtn.setEnabled(false);
                    reference.mStartBtn.setEnabled(true);
                    reference.cancellationSignal = null;
                    break;
                case MSG_AUTH_ERROR:
                    reference.handleErrorCode(msg.arg1);
                    break;
                case MSG_AUTH_HELP:
                    reference.handleHelpCode(msg.arg1);
                    break;
            }

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (!mStartBtn.isEnabled() && cancellationSignal != null) {
            cancellationSignal.cancel();
        }
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_test_finger_print;
    }

    @Override
    protected void memoryRecovery() {

    }

    private void handleHelpCode(int code) {
        switch (code) {
            case FingerprintManager.FINGERPRINT_ACQUIRED_GOOD:
                setResultInfo(R.string.AcquiredGood_warning);
                break;
            case FingerprintManager.FINGERPRINT_ACQUIRED_IMAGER_DIRTY:
                setResultInfo(R.string.AcquiredImageDirty_warning);
                break;
            case FingerprintManager.FINGERPRINT_ACQUIRED_INSUFFICIENT:
                setResultInfo(R.string.AcquiredInsufficient_warning);
                break;
            case FingerprintManager.FINGERPRINT_ACQUIRED_PARTIAL:
                setResultInfo(R.string.AcquiredPartial_warning);
                break;
            case FingerprintManager.FINGERPRINT_ACQUIRED_TOO_FAST:
                setResultInfo(R.string.AcquiredTooFast_warning);
                break;
            case FingerprintManager.FINGERPRINT_ACQUIRED_TOO_SLOW:
                setResultInfo(R.string.AcquiredToSlow_warning);
                break;
        }
    }

    /**
     * 显示错误信息
     *
     * @param code
     */
    private void handleErrorCode(int code) {
        switch (code) {
            case FingerprintManager.FINGERPRINT_ERROR_CANCELED:
                setResultInfo(R.string.ErrorCanceled_warning);
                break;
            case FingerprintManager.FINGERPRINT_ERROR_HW_UNAVAILABLE:
                setResultInfo(R.string.ErrorHwUnavailable_warning);
                break;
            case FingerprintManager.FINGERPRINT_ERROR_LOCKOUT:
                setResultInfo(R.string.ErrorLockout_warning);
                break;
            case FingerprintManager.FINGERPRINT_ERROR_NO_SPACE:
                setResultInfo(R.string.ErrorNoSpace_warning);
                break;
            case FingerprintManager.FINGERPRINT_ERROR_TIMEOUT:
                setResultInfo(R.string.ErrorTimeout_warning);
                break;
            case FingerprintManager.FINGERPRINT_ERROR_UNABLE_TO_PROCESS:
                setResultInfo(R.string.ErrorUnableToProcess_warning);
                break;
        }
    }

    /**
     * 显示结果信息
     *
     * @param stringId
     */
    private void setResultInfo(int stringId) {
        if (mResultInfo != null) {
            if (stringId == R.string.fingerprint_success) {
                mResultInfo.setTextColor(getResources().getColor(R.color.success_color));
            } else {
                mResultInfo.setTextColor(getResources().getColor(R.color.warning_color));
            }
            mResultInfo.setText(stringId);
        }
    }
}
