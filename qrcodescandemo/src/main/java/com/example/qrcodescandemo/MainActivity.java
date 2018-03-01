package com.example.qrcodescandemo;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qrcodescandemo.activity.CustomActivity;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    private Toolbar toolbar;
    private FloatingActionButton fab;
    private Button btnEncrypt;
    private Button btnDEncrypt;
    private TextView tvContent;
    private static final String CIPHER_ALGORITHM = "AES/CBC/NOPadding";
    public final String AES_IV = "yaQlZ8C0zWWb8WX0";/*加密向量*/
    private final String AES_KEY = "MclKHWyYq6UpRGlE";/*加密秘钥*/
    //    mTcSqNwwS3yoyLZHiaTQeQ==/*美丽屋加密后数据*/
    public static final String content = "meiliwu";
    private String ret;
    private byte[] enCryptedResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setSupportActionBar(toolbar);

    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(this);
        btnEncrypt = findViewById(R.id.btn_encrypt);
        btnEncrypt.setOnClickListener(this);

        btnDEncrypt = findViewById(R.id.btn_dencrypt);
        btnDEncrypt.setOnClickListener(this);

        tvContent = findViewById(R.id.tv);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // 获取解析结果
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "取消扫描", Toast.LENGTH_LONG).show();
            } else {
                showBarcodeImage(result.getBarcodeImagePath());
                Toast.makeText(this, "扫描内容:" + result.getContents(), Toast.LENGTH_LONG).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    /**
     * 加载并显示条形码图片
     */
    private void showBarcodeImage(String barcodeImagePath) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(new File(barcodeImagePath));
            ((ImageView) findViewById(R.id.img)).setImageBitmap(BitmapFactory.decodeStream(fis));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                testScanQRCode();
                break;
            case R.id.btn_encrypt:
                try {
                    encrypt();
                } catch (NoSuchPaddingException e) {
                    Log.i(TAG, "onClick: ");
                    e.printStackTrace();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (InvalidAlgorithmParameterException e) {
                    e.printStackTrace();
                } catch (InvalidKeyException e) {
                    e.printStackTrace();
                } catch (BadPaddingException e) {
                    e.printStackTrace();
                } catch (IllegalBlockSizeException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_dencrypt:
                try {
                    decrypt();
                } catch (NoSuchPaddingException e) {
                    e.printStackTrace();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (InvalidAlgorithmParameterException e) {
                    e.printStackTrace();
                } catch (InvalidKeyException e) {
                    e.printStackTrace();
                } catch (BadPaddingException e) {
                    e.printStackTrace();
                } catch (IllegalBlockSizeException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }


    private void encrypt() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        //1，创建秘钥,向量
        SecretKey key = new SecretKeySpec(AES_KEY.getBytes(), "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(AES_IV.getBytes());

        //2,得到cipher 对象（可翻译为密码器或密码系统）
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);

        //3，设置操作模式（加密/解密）
        cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);

        //5，执行操作

        int blockSize = cipher.getBlockSize();
        byte[] dataBytes = content.getBytes();
        int plaintextLength = dataBytes.length;
        if (plaintextLength % blockSize != 0) {
            plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
        }

        byte[] plaintext = new byte[plaintextLength];
        System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);

        enCryptedResult = cipher.doFinal(plaintext);
        ret = Base64.encodeToString(enCryptedResult, Base64.DEFAULT);
        Log.i(TAG, "ret.length: " + ret.length());
        Log.i(TAG, "ret==: " + ret);
        tvContent.setText(ret);
        Toast.makeText(this, ret, Toast.LENGTH_LONG).show();

    }

    private void decrypt() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException {
        //1，创建秘钥,向量
        SecretKey key = new SecretKeySpec(AES_KEY.getBytes(), "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(AES_IV.getBytes());

        //2,得到cipher 对象（可翻译为密码器或密码系统）
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);

        //3，设置操作模式（加密/解密）
        cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);

        //5，执行操作

        byte[] result = cipher.doFinal(enCryptedResult);
        String originalText = new String(result, "utf-8");

        Log.i(TAG, "decrypt: enCryptedResult.length " + enCryptedResult.length );
        Log.i(TAG, "decrypt: " + originalText + "length ==" + originalText.length());
        Log.i(TAG, "originalText.trim().length: " + originalText.trim().length());
        tvContent.setText(originalText);
        Base64.encodeToString(result, Base64.DEFAULT);
        Log.i(TAG, "base64: " + Base64.encodeToString(result, Base64.DEFAULT) );
        Toast.makeText(this, "originalText == " + originalText, Toast.LENGTH_LONG).show();

    }


    private void testScanQRCode() {
//        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
////                        .setAction("Action", null).show();

        // 创建IntentIntegrator对象
        IntentIntegrator intentIntegrator = new IntentIntegrator(MainActivity.this);
        // 开始扫描
                /*开启保存图片到本地*/
        intentIntegrator.setBarcodeImageEnabled(true);
                /*取消声音*/
        intentIntegrator.setBeepEnabled(false);
                /*自定义扫描界面*/
        intentIntegrator.setCaptureActivity(CustomActivity.class);
                /*自定义扫描提示语*/
        intentIntegrator.setPrompt("请将条形码置于扫描框内扫描");
                /*设置扫描超时*/
//                intentIntegrator.setTimeout(2000);
        intentIntegrator.initiateScan();
    }
}
