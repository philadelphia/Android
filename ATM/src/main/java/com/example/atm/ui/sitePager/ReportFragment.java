package com.example.atm.ui.sitePager;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import org.json.JSONArray;

import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.support.v7.widget.Toolbar;

import android.text.TextUtils;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;


import com.example.atm.MainActivity;
import com.example.atm.R;
import com.example.atm.adapter.ReportToNOCAdapter;
import com.example.atm.common.BaseApplication;
import com.example.atm.entities.TroubleTicketEdit;
import com.example.atm.utils.Base64;
import com.example.atm.utils.ListviewUtlis;
import com.example.atm.utils.ReportToNOCHelper;
import com.example.atm.utils.ToastUtil;
import com.google.gson.Gson;

@SuppressLint({"CutPasteId", "SimpleDateFormat", "HandlerLeak",
        "InflateParams"})
@SuppressWarnings("unused")
public class ReportFragment extends Fragment implements OnClickListener {

    private Button btnTakephoto;
    private Button btnPhotos;
    private Button pic;
    private Button sen;
    private Button click_btn;
    private TextView txt_last;
    private ListView lv;
    private EditText ed_remark;

    private ArrayList<TroubleTicketEdit> troubleTickets;
    private ArrayList<TroubleTicketEdit> troubleTicketss;
    private ArrayList<String> strs;

    private SharedPreferences preferences;
    private String LoginID = null;
    private SharedPreferences pref;
    private Editor edit;

    private ReportToNOCAdapter adapter;
    private ReportToNOCHelper reportToNOCHelper;
    private TroubleTicketEdit troubleTicketEdit;

    private AlertDialog.Builder build;
    private static Bitmap bmp;


    private String str;
    private String remark;
    private String siteID;

    private JSONObject object;
    private JSONArray jsonarray;
    private JSONArray jsonarrayy;
    private JSONObject jsonObj;

    private static final String REQUEST_TAG = "TroubleTicketEditFragment";
    private static final String KEY_SITE_ID = "key_site_id";
    private static final String TAG = ReportFragment.class.getSimpleName();
    public final static int CAMERA_RESULT = 8888;
    public final static int REQ_CODE_PICK_IMAGE = 9999;

    private AlertDialog.Builder builder;
    private Parcelable parcelable;
    private Gson gson;

    private PopupWindow popupWindow;
    private ProgressDialog dialo;
    private File mImageFile;

    private MyHandler handler = new MyHandler();
    private View view;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.report_fragment, container, false);
        BaseApplication.setHandler(handler);
        troubleTickets = new ArrayList<TroubleTicketEdit>();
        troubleTicketss = new ArrayList<TroubleTicketEdit>();
        strs = new ArrayList<String>();

        preferences = getActivity().getSharedPreferences("config",
                Context.MODE_PRIVATE);
        LoginID = preferences.getString("LoginID", null);
        pref = getActivity().getSharedPreferences("config",
                Context.MODE_PRIVATE);
        siteID = pref.getString("mID", null);
        edit = pref.edit();
        builder = new AlertDialog.Builder(getActivity());
        build = new AlertDialog.Builder(getActivity());
//        MainActivity.getFloatingActionButton().setVisibility(View.VISIBLE);
//		reportToNOCHelper = new ReportToNOCHelper(getActivity());

//		getData();
        initView(view);
        return view;
    }

    private void initView(View view) {
        txt_last = (TextView) view.findViewById(R.id.txt_last);
        ed_remark = (EditText) view.findViewById(R.id.txt_last);
        lv = (ListView) view.findViewById(R.id.lv);
    }

//	private void getData() {
//		troubleTickets.clear();
//		troubleTicketss.clear();
//		troubleTickets = reportToNOCHelper.getTrobuleTickets(siteID, LoginID,
//				"xxx");
//		if (troubleTickets.size() > 0) {
//			for (int i = 0; i < troubleTickets.size(); i++) {
//				TroubleTicketEdit edit = troubleTickets.get(i);
//				if (LoginID.equals(edit.getLoginID())
//						&& siteID.equals(edit.getSiteID())) {
//					troubleTicketss.add(edit);
//				}
//			}
//		} else {
//			lv.setEmptyView(null);
//			adapter = new ReportToNOCAdapter(getActivity(), troubleTicketss,
//					LoginID, siteID, "xxx");
//			lv.setAdapter(adapter);
//			ListviewUtlis.setListViewHeightBasedOnChildren(lv);
//			txt_last.setText(null);
//		}
//
//		if (troubleTicketss.size() > 0) {
//			lv.setEmptyView(null);
//			adapter = new ReportToNOCAdapter(getActivity(), troubleTicketss,
//					LoginID, siteID, "xxx");
//			lv.setAdapter(adapter);
//			ListviewUtlis.setListViewHeightBasedOnChildren(lv);
//		}
//	}

    @SuppressWarnings("deprecation")
    private void showWindow(View parent) {

        if (popupWindow == null) {
            LayoutInflater layoutInflater = (LayoutInflater) getActivity()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(
                    R.layout.troubleticket_edit_image, null);
            btnPhotos = (Button) view.findViewById(R.id.btnTakephoto);
            btnTakephoto = (Button) view.findViewById(R.id.btnPhotos);
            btnPhotos.setOnClickListener(this);
            btnTakephoto.setOnClickListener(this);
            // 创建一个PopuWidow对象
            popupWindow = new PopupWindow(view, 350, 350);
        }

        // 使其聚集
        popupWindow.setFocusable(true);
        // 设置允许在外点击消失
        popupWindow.setOutsideTouchable(true);

        // 这个是为了点击“返回Back”也能使其消失，并且并不会影响你的背景
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        WindowManager windowManager = (WindowManager) getActivity()
                .getSystemService(Context.WINDOW_SERVICE);

        // 显示的位置为:屏幕的宽度的一半-PopupWindow的高度的一半
        int xPos = windowManager.getDefaultDisplay().getWidth() / 2
                - popupWindow.getWidth() / 2;
        popupWindow.showAsDropDown(parent, 1200, -350);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        Log.i(TAG, "onCreateOptionsMenu: ");
        inflater.inflate(R.menu.menu_image, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onResume() {
        Log.i(TAG, "onResume: ");
        super.onResume();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.camera:
                showWindow(view);
                break;
            case R.id.send:
                send();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void send() {
        Log.i(TAG, "send: ");
        builder.setMessage("Send report to NOC")
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                beginToUploadImages();
                            }
                        })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.i(TAG, "onClick: cancel");
                    }
                });
        builder.create().show();
//						gson = new Gson();
//						remark = ed_remark.getText().toString()
//								.trim();
//						ArrayList<TroubleTicketEdit> arrayList = reportToNOCHelper
//								.getTrobuleTickets(siteID,
//										LoginID, "xxx");
//						int num = arrayList.size();
//						if (TextUtils.isEmpty(remark)
//								|| num <= 0) {
//							builder.setMessage(
//									"The report cannot be sent without image and remark!")
//									.setPositiveButton(
//											"OK",
//											new DialogInterface.OnClickListener() {
//
//												@Override
//												public void onClick(
//														DialogInterface dialog,
//														int which) {
//													return;
//												}
//											}).show();
//						} else {
//							dialo = ProgressDialog.show(
//									getActivity(), "",
//									"Uploading");
//							dialo.setCanceledOnTouchOutside(false);
//							object = new JSONObject();
//							jsonarray = new JSONArray();
//							jsonarrayy = new JSONArray();
//
//							for (int i = 0; i < arrayList
//									.size(); i++) {
//								try {
//									jsonObj = new JSONObject();
//									String s = arrayList.get(i)
//											.getStr();
//									strs.add(s);
//									File mImageFile = new File(
//											s);
//									if (!mImageFile.exists()) {
//										try {
//											mImageFile
//													.createNewFile();
//										} catch (IOException e) {
//											e.printStackTrace();
//										}
//									}
//
//									jsonObj.put(
//											"Content",
//											initVolly(mImageFile));
//									jsonObj.put("ContentType",
//											"image/jpeg");
//									jsonObj.put(
//											"FileName",
//											arrayList
//													.get(i)
//													.getImageName());
//									jsonarray.put(jsonObj);
//								} catch (JSONException e) {
//									e.printStackTrace();
//								}
//							}
//
//							try {
//								object.put("Attachment",
//										jsonarray);
//								object.put("Remark", remark);
//								jsonarrayy.put(object);
//							} catch (JSONException e) {
//								e.printStackTrace();
//							}
//
//							try {
//								MyJsonArrayRequest myJsonArrayRequest = new MyJsonArrayRequest(
//										Method.POST,
//										Url.SEND_REPORT,
//										new Listener<JSONArray>() {
//
//											@Override
//											public void onResponse(
//													JSONArray response) {
//												if (response
//														.length() > 0) {
//													String s = response
//															.toString();
//													int k = s
//															.length();
//													String str = s
//															.substring(
//																	0 + 1,
//																	k - 1);
//													if ((str.equals(200 + ""))) {
//														dialo.dismiss();
//														for (int j = 0; j < strs
//																.size(); j++) {
//															String ss = strs
//																	.get(j);
//															File imageFile = new File(
//																	ss);
//															if (imageFile
//																	.exists()) {
//																try {
//																	int num = reportToNOCHelper
//																			.cancelByPath(
//																					siteID,
//																					LoginID,
//																					"xxx",
//																					ss);
//																	if (num > 0) {
//																		imageFile
//																				.delete();
//																		String where = MediaStore.Images.Media.DATA
//																				+ "='"
//																				+ imageFile
//																				+ "'";
//																		getActivity()
//																				.getContentResolver()
//																				.delete(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
//																						where,
//																						null);
//																	}
//																} catch (Exception e) {
//																	e.printStackTrace();
//																}
//															}
//
//														}
//														ToastUtil
//																.showToast(
//																		getActivity(),
//																		"Report sent successfully");
//													}
//													getData();
//												}
//											}
//										}, new ErrorListener() {
//
//											@Override
//											public void onErrorResponse(
//													VolleyError error) {
//												dialo.dismiss();
//												Log.d("error",
//														"onErrorResponse(): error = "
//																+ error);
//												if (null != error
//														&& error.networkResponse != null) {
//													if (error.networkResponse.statusCode == 400) {
//														Log.w("error",
//																"error.networkResponse.toString(): "
//																		+ error.networkResponse
//																				.toString());
//														ToastUtil
//																.showToast(
//																		getActivity(),
//																		"The request is invalid！");
//													} else {
//
//														build.setMessage(
//																"Submit timeout, please check the network or contact the background!")
//																.setNegativeButton(
//																		"OK",
//																		new DialogInterface.OnClickListener() {
//
//																			@Override
//																			public void onClick(
//																					DialogInterface dialog,
//																					int which) {
//
//																			}
//																		})
//																.create()
//																.show();
//													}
//												}
//											}
//										});
//								myJsonArrayRequest
//										.getHeaders()
//										.put("LoginID", LoginID);
//								myJsonArrayRequest.getHeaders()
//										.put("SiteID", siteID);
//								myJsonArrayRequest
//										.addBody(jsonarrayy);
//								myJsonArrayRequest
//										.setTag(REQUEST_TAG);
//								myJsonArrayRequest
//										.setRetryPolicy(new DefaultRetryPolicy(
//												60000,
//												2,
//												DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//								BaseApplication
//										.getRequestQueue()
//										.add(myJsonArrayRequest);
//							} catch (Exception e) {
//								e.printStackTrace();
//							}
//						}
//						return;
//					}
//				}).setNegativeButton("CANCEL", null).show();
//builder.setCancelable(false);
//
//	}


//		builder.setMessage("Send report to NOC")
//		.setPositiveButton("OK",
//				new DialogInterface.OnClickListener() {
//
//					@Override
//					public void onClick(DialogInterface dialog,
//							int which) {
//						gson = new Gson();
//						remark = ed_remark.getText().toString()
//								.trim();
//						ArrayList<TroubleTicketEdit> arrayList = reportToNOCHelper
//								.getTrobuleTickets(siteID,
//										LoginID, "xxx");
//						int num = arrayList.size();
//						if (TextUtils.isEmpty(remark)
//								|| num <= 0) {
//							builder.setMessage(
//									"The report cannot be sent without image and remark!")
//									.setPositiveButton(
//											"OK",
//											new DialogInterface.OnClickListener() {
//
//												@Override
//												public void onClick(
//														DialogInterface dialog,
//														int which) {
//													return;
//												}
//											}).show();
//						} else {
//							dialo = ProgressDialog.show(
//									getActivity(), "",
//									"Uploading");
//							dialo.setCanceledOnTouchOutside(false);
//							object = new JSONObject();
//							jsonarray = new JSONArray();
//							jsonarrayy = new JSONArray();
//
//							for (int i = 0; i < arrayList
//									.size(); i++) {
//								try {
//									jsonObj = new JSONObject();
//									String s = arrayList.get(i)
//											.getStr();
//									strs.add(s);
//									File mImageFile = new File(
//											s);
//									if (!mImageFile.exists()) {
//										try {
//											mImageFile
//													.createNewFile();
//										} catch (IOException e) {
//											e.printStackTrace();
//										}
//									}
//
//									jsonObj.put(
//											"Content",
//											initVolly(mImageFile));
//									jsonObj.put("ContentType",
//											"image/jpeg");
//									jsonObj.put(
//											"FileName",
//											arrayList
//													.get(i)
//													.getImageName());
//									jsonarray.put(jsonObj);
//								} catch (JSONException e) {
//									e.printStackTrace();
//								}
//							}
//
//							try {
//								object.put("Attachment",
//										jsonarray);
//								object.put("Remark", remark);
//								jsonarrayy.put(object);
//							} catch (JSONException e) {
//								e.printStackTrace();
//							}
//
//							try {
//								MyJsonArrayRequest myJsonArrayRequest = new MyJsonArrayRequest(
//										Method.POST,
//										Url.SEND_REPORT,
//										new Listener<JSONArray>() {
//
//											@Override
//											public void onResponse(
//													JSONArray response) {
//												if (response
//														.length() > 0) {
//													String s = response
//															.toString();
//													int k = s
//															.length();
//													String str = s
//															.substring(
//																	0 + 1,
//																	k - 1);
//													if ((str.equals(200 + ""))) {
//														dialo.dismiss();
//														for (int j = 0; j < strs
//																.size(); j++) {
//															String ss = strs
//																	.get(j);
//															File imageFile = new File(
//																	ss);
//															if (imageFile
//																	.exists()) {
//																try {
//																	int num = reportToNOCHelper
//																			.cancelByPath(
//																					siteID,
//																					LoginID,
//																					"xxx",
//																					ss);
//																	if (num > 0) {
//																		imageFile
//																				.delete();
//																		String where = MediaStore.Images.Media.DATA
//																				+ "='"
//																				+ imageFile
//																				+ "'";
//																		getActivity()
//																				.getContentResolver()
//																				.delete(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
//																						where,
//																						null);
//																	}
//																} catch (Exception e) {
//																	e.printStackTrace();
//																}
//															}
//
//														}
//														ToastUtil
//																.showToast(
//																		getActivity(),
//																		"Report sent successfully");
//													}
//													getData();
//												}
//											}
//										}, new ErrorListener() {
//
//											@Override
//											public void onErrorResponse(
//													VolleyError error) {
//												dialo.dismiss();
//												Log.d("error",
//														"onErrorResponse(): error = "
//																+ error);
//												if (null != error
//														&& error.networkResponse != null) {
//													if (error.networkResponse.statusCode == 400) {
//														Log.w("error",
//																"error.networkResponse.toString(): "
//																		+ error.networkResponse
//																				.toString());
//														ToastUtil
//																.showToast(
//																		getActivity(),
//																		"The request is invalid！");
//													} else {
//
//														build.setMessage(
//																"Submit timeout, please check the network or contact the background!")
//																.setNegativeButton(
//																		"OK",
//																		new DialogInterface.OnClickListener() {
//
//																			@Override
//																			public void onClick(
//																					DialogInterface dialog,
//																					int which) {
//
//																			}
//																		})
//																.create()
//																.show();
//													}
//												}
//											}
//										});
//								myJsonArrayRequest
//										.getHeaders()
//										.put("LoginID", LoginID);
//								myJsonArrayRequest.getHeaders()
//										.put("SiteID", siteID);
//								myJsonArrayRequest
//										.addBody(jsonarrayy);
//								myJsonArrayRequest
//										.setTag(REQUEST_TAG);
//								myJsonArrayRequest
//										.setRetryPolicy(new DefaultRetryPolicy(
//												60000,
//												2,
//												DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//								BaseApplication
//										.getRequestQueue()
//										.add(myJsonArrayRequest);
//							} catch (Exception e) {
//								e.printStackTrace();
//							}
//						}
//						return;
//					}
//				}).setNegativeButton("CANCEL", null).show();
//builder.setCancelable(false);
//
    }

    private void beginToUploadImages() {
        Log.i(TAG, "beginToUploadImages: ");
        remark = ed_remark.getText().toString().trim();
        if (TextUtils.isEmpty(remark)) {
            builder.setMessage(
                    "The report cannot be sent without image and remark!")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        //	@Override
                        public void onClick(
                                DialogInterface dialog,
                                int which) {
                            ;
                        }
                    }).show();
        } else {
            dialo = ProgressDialog.show(
                    getActivity(), "",
                    "Uploading");
            dialo.setCanceledOnTouchOutside(false);
        }
    }

    @Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnTakephoto:
            takePhotoes();
			popupWindow.dismiss();
			break;
		case R.id.btnPhotos:
            choosePhotoesFromGallery();
			popupWindow.dismiss();
			break;
		}
	}

    private void choosePhotoesFromGallery() {
        Intent i = new Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        getActivity().startActivityForResult(i, REQ_CODE_PICK_IMAGE);
    }

    private void takePhotoes() {
        File dir = new File(Environment.getExternalStorageDirectory(),
            "image");
        if (!dir.exists()) {
            dir.mkdirs();
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss", Locale.US);
        String fileName = sdf.format(new Date()) + ".jpg";
        mImageFile = new File(dir, fileName);
        if (!mImageFile.exists()) {
            try {
                mImageFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        parcelable = Uri.fromFile(mImageFile);
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mImageFile));
        getActivity().startActivityForResult(intent, CAMERA_RESULT);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//		if (null != mToolbar) {
//			mToolbar.getMenu().removeItem(R.id.camera);
//			mToolbar.getMenu().removeItem(R.id.send);
//			mToolbar = null;
//		}
        hideSoftKeyboard();
    }

    private void hideSoftKeyboard() {
        InputMethodManager inputManager = (InputMethodManager) getActivity()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        String str = ed_remark.getText().toString().trim();
        if (null != inputManager) {
            if (null != str) {
                inputManager.hideSoftInputFromWindow(
                        ed_remark.getWindowToken(), 0);
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();

    }

    public String initVolly(File mImage) {
        String res = null;
        try {
            FileInputStream fStream = new FileInputStream(mImage);
            long total = fStream.available();
            byte[] readStream = readStream(fStream, total);
            char[] encode = Base64.encode(readStream);
            Log.i("FileLength", "char_length:" + encode.length + "");
            res = new String(encode);
            Log.i("FileLength", "String_length:" + res.length() + "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public static byte[] readStream(InputStream inStream, long total)
            throws Exception {

        byte[] buffer = new byte[1024];
        int len = -1;
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);

        }
        byte[] data = outStream.toByteArray();
        outStream.close();
        inStream.close();
        len = -1;
        return data;
    }


    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    Bitmap drawable = BitmapFactory.decodeFile(mImageFile
                            .toString());
                    if (null == drawable) {
                        ToastUtil.showToast(getActivity(),
                                "Picture does not exist!");
                    } else {
                        try {
                            troubleTicketEdit = new TroubleTicketEdit();
                            troubleTicketEdit.setStr(mImageFile.toString());
                            troubleTicketss.add(troubleTicketEdit);
                            if (troubleTicketss.size() > 3) {
                                troubleTicketss.remove(troubleTicketEdit);
                                ToastUtil
                                        .showToast(getActivity(),
                                                "You can only attach 3 image files to one trouble tictet!");
                                return;
                            } else {
                                lv.setEmptyView(null);
                                adapter = new ReportToNOCAdapter(getActivity(),
                                        troubleTicketss, LoginID, siteID, "xxx");
                                lv.setAdapter(adapter);
                                ListviewUtlis.setListViewHeightBasedOnChildren(lv);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 2:

                    String str = (String) msg.getData().get("da");
                    System.out.println(str);

                    Uri selectedImage = Uri.parse(str);

                    troubleTicketEdit = new TroubleTicketEdit();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    Cursor cursor = getActivity().getContentResolver().query(
                            selectedImage, filePathColumn, null, null, null);
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String filePath = cursor.getString(columnIndex);
                    cursor.close();
                    TroubleTicketEdit troubleTicketEdit2 = reportToNOCHelper
                            .getTrobuleTicketByID(siteID, LoginID, filePath, "xxx");
                    if (null == troubleTicketEdit2) {
                        Bitmap imageBitmap = BitmapFactory.decodeFile(filePath);
                        if (null == imageBitmap) {
                            ToastUtil.showToast(getActivity(),
                                    "Images can not be empty!");
                            return;
                        }
                        troubleTicketEdit.setStr(filePath);
                        troubleTicketss.add(troubleTicketEdit);
                        if (troubleTicketss.size() > 3) {
                            troubleTicketss.remove(troubleTicketEdit);
                            ToastUtil
                                    .showToast(getActivity(),
                                            "You can only attach 3 image files to one trouble tictet!");
                            return;
                        } else {
                            lv.setEmptyView(null);
                            adapter = new ReportToNOCAdapter(getActivity(),
                                    troubleTicketss, LoginID, siteID, "xxx");
                            lv.setAdapter(adapter);
                            ListviewUtlis.setListViewHeightBasedOnChildren(lv);
                        }

                    } else {
                        ToastUtil.showToast(getActivity(),
                                "Can't save the same image as the name!");
                    }

                    break;
//			case 3:
//				showWindow(view);
//				break;
//
//			case 4:
//				builder.setMessage("Send report to NOC")
//						.setPositiveButton("OK",
//								new DialogInterface.OnClickListener() {
//
//									@Override
//									public void onClick(DialogInterface dialog,
//											int which) {
//										gson = new Gson();
//										remark = ed_remark.getText().toString()
//												.trim();
//										ArrayList<TroubleTicketEdit> arrayList = reportToNOCHelper
//												.getTrobuleTickets(siteID,
//														LoginID, "xxx");
//										int num = arrayList.size();
//										if (TextUtils.isEmpty(remark)
//												|| num <= 0) {
//											builder.setMessage(
//													"The report cannot be sent without image and remark!")
//													.setPositiveButton(
//															"OK",
//															new DialogInterface.OnClickListener() {
//
//																@Override
//																public void onClick(
//																		DialogInterface dialog,
//																		int which) {
//																	return;
//																}
//															}).show();
//										} else {
//											dialo = ProgressDialog.show(
//													getActivity(), "",
//													"Uploading");
//											dialo.setCanceledOnTouchOutside(false);
//											object = new JSONObject();
//											jsonarray = new JSONArray();
//											jsonarrayy = new JSONArray();
//
//											for (int i = 0; i < arrayList
//													.size(); i++) {
//												try {
//													jsonObj = new JSONObject();
//													String s = arrayList.get(i)
//															.getStr();
//													strs.add(s);
//													File mImageFile = new File(
//															s);
//													if (!mImageFile.exists()) {
//														try {
//															mImageFile
//																	.createNewFile();
//														} catch (IOException e) {
//															e.printStackTrace();
//														}
//													}
//
//													jsonObj.put(
//															"Content",
//															initVolly(mImageFile));
//													jsonObj.put("ContentType",
//															"image/jpeg");
//													jsonObj.put(
//															"FileName",
//															arrayList
//																	.get(i)
//																	.getImageName());
//													jsonarray.put(jsonObj);
//												} catch (JSONException e) {
//													e.printStackTrace();
//												}
//											}
//
//											try {
//												object.put("Attachment",
//														jsonarray);
//												object.put("Remark", remark);
//												jsonarrayy.put(object);
//											} catch (JSONException e) {
//												e.printStackTrace();
//											}
//
//											try {
//												MyJsonArrayRequest myJsonArrayRequest = new MyJsonArrayRequest(
//														Method.POST,
//														Url.SEND_REPORT,
//														new Listener<JSONArray>() {
//
//															@Override
//															public void onResponse(
//																	JSONArray response) {
//																if (response
//																		.length() > 0) {
//																	String s = response
//																			.toString();
//																	int k = s
//																			.length();
//																	String str = s
//																			.substring(
//																					0 + 1,
//																					k - 1);
//																	if ((str.equals(200 + ""))) {
//																		dialo.dismiss();
//																		for (int j = 0; j < strs
//																				.size(); j++) {
//																			String ss = strs
//																					.get(j);
//																			File imageFile = new File(
//																					ss);
//																			if (imageFile
//																					.exists()) {
//																				try {
//																					int num = reportToNOCHelper
//																							.cancelByPath(
//																									siteID,
//																									LoginID,
//																									"xxx",
//																									ss);
//																					if (num > 0) {
//																						imageFile
//																								.delete();
//																						String where = MediaStore.Images.Media.DATA
//																								+ "='"
//																								+ imageFile
//																								+ "'";
//																						getActivity()
//																								.getContentResolver()
//																								.delete(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
//																										where,
//																										null);
//																					}
//																				} catch (Exception e) {
//																					e.printStackTrace();
//																				}
//																			}
//
//																		}
//																		ToastUtil
//																				.showToast(
//																						getActivity(),
//																						"Report sent successfully");
//																	}
//																	getData();
//																}
//															}
//														}, new ErrorListener() {
//
//															@Override
//															public void onErrorResponse(
//																	VolleyError error) {
//																dialo.dismiss();
//																Log.d("error",
//																		"onErrorResponse(): error = "
//																				+ error);
//																if (null != error
//																		&& error.networkResponse != null) {
//																	if (error.networkResponse.statusCode == 400) {
//																		Log.w("error",
//																				"error.networkResponse.toString(): "
//																						+ error.networkResponse
//																								.toString());
//																		ToastUtil
//																				.showToast(
//																						getActivity(),
//																						"The request is invalid！");
//																	} else {
//
//																		build.setMessage(
//																				"Submit timeout, please check the network or contact the background!")
//																				.setNegativeButton(
//																						"OK",
//																						new DialogInterface.OnClickListener() {
//
//																							@Override
//																							public void onClick(
//																									DialogInterface dialog,
//																									int which) {
//
//																							}
//																						})
//																				.create()
//																				.show();
//																	}
//																}
//															}
//														});
//												myJsonArrayRequest
//														.getHeaders()
//														.put("LoginID", LoginID);
//												myJsonArrayRequest.getHeaders()
//														.put("SiteID", siteID);
//												myJsonArrayRequest
//														.addBody(jsonarrayy);
//												myJsonArrayRequest
//														.setTag(REQUEST_TAG);
//												myJsonArrayRequest
//														.setRetryPolicy(new DefaultRetryPolicy(
//																60000,
//																2,
//																DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//												BaseApplication
//														.getRequestQueue()
//														.add(myJsonArrayRequest);
//											} catch (Exception e) {
//												e.printStackTrace();
//											}
//										}
//										return;
//									}
//								}).setNegativeButton("CANCEL", null).show();
//				builder.setCancelable(false);
//				break;
                case 5:
//				getData();
                    break;
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case REQ_CODE_PICK_IMAGE:
                Uri uri = data.getData();
                Log.i(TAG, "onActivityResult: "+uri.getPath());
                Uri thumb = Uri.withAppendedPath(MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI,uri.getLastPathSegment());
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), thumb);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
