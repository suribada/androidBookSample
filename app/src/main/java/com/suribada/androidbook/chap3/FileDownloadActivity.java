package com.suribada.androidbook.chap3;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.suribada.androidbook.R;

/**
 * Created by Noh.Jaechun on 2017. 3. 2..
 */
public class FileDownloadActivity extends Activity {

	private final static String TAG = "FileDownloadActivity";

	private static final String SOME_SOUND_FILE_URL = "http://suribada.com/hrk.zip"; // 다운로드할 파일

	private static final String SOME_FILE = "hrk.obb"; // 로컬에 저장할 파일

	public void onClickButton1(View view) {
		new FileDownloadTask().execute(SOME_SOUND_FILE_URL);
	}

	private class FileDownloadTask extends AsyncTask<String, Integer, Boolean> {

		private ProgressDialog pd;

		@Override
		protected void onPreExecute() {
	    	/* 파일 다운로드가 실행되기 전에 ProgressDialog를 실행한다.*/
			pd = new ProgressDialog(FileDownloadActivity.this);
			pd.setMessage(getString(R.string.file_download));
			pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			pd.setCancelable(true); // Back Key를 통한 Cancel이 가능하다.
			pd.setCanceledOnTouchOutside(false); // 다이얼로그 바깥을 터치했을때 Cancel이 되지 않는다.
			pd.setButton(DialogInterface.BUTTON_NEGATIVE, getResources().getString(R.string.cancel),
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						pd.cancel(); // 취소 버튼을 클릭하면 다이얼로그 닫히고, 아래 OnCancelListener.onCancel이 불린다.
					}

				});
			pd.setOnCancelListener(new DialogInterface.OnCancelListener() {

				@Override
				public void onCancel(DialogInterface dialog) {
					FileDownloadTask.this.cancel(true); // 파라미터는 mayInterruptIfRunning로 Task 인터럽트를 시도할지 여부이다.
					Log.d(TAG, "AsyncTask cancel");
				}

			});
			pd.show();
		}

		private File sosiFile, sosiTempFile;

		@Override
		protected Boolean doInBackground(String... params) {
			int count;
			InputStream input = null;
			OutputStream output = null;
	        /* 정상적으로 다운이 다 되기 전까지는 temp 파일에 저장한다. */
			sosiTempFile = new File(FileDownloadActivity.this.getFilesDir().getPath(), "hrk.temp");
			sosiFile = new File(FileDownloadActivity.this.getFilesDir().getPath(), SOME_FILE);
			try {
				URL url = new URL(params[0]);
				URLConnection connection = url.openConnection();
				connection.setConnectTimeout(10000);
				connection.connect();

				int lenghtOfFile = connection.getContentLength(); // 전체에서 몇 퍼센트 진행중인지 알기 위해서 전체 길이를 먼저 구한다.

				input = new BufferedInputStream(url.openStream());
				output = new FileOutputStream(sosiTempFile);
				byte data[] = new byte[1024];
				long total = 0;
				while ((count = input.read(data)) != -1 && isCancelled() == false) { // isCancelled를 사용해서 취소하면 바로 끝내도록 한다.
					//while ((count = input.read(data)) != -1) { // isCancelled를 사용해서 취소하면 바로 끝내도록 한다.
					Log.d(TAG, "read continuous");
					total += count;
					publishProgress((int) ((total * 100) / lenghtOfFile));  // 퍼센트 비율을 알려주고, onProgressUpdate에서 내용을 보여준다.
					output.write(data, 0, count);
				}
				output.flush();
			} catch (Exception e) {
				Log.i(TAG, "file Download Problem", e);
				sosiTempFile.delete(); // 문제가 발생하면 temp 파일을 삭제한다.
				return Boolean.FALSE; // 결과를 FALSE로 리턴한다.
			} finally {
				if (output != null) {
					try {
						output.close();
					} catch (IOException e) {
					}
				}
				if (input != null) {
					try {
						input.close();
					} catch (IOException e) {
					}
				}
			}
			return Boolean.TRUE; // 여기까지 왔으면
		}

		@Override
		protected void onCancelled() {
			pd.dismiss(); // 진행중 표시를 종료한다.
			sosiTempFile.delete(); // 취소 되었으면 temp 파일도 삭제한다.
			Toast.makeText(FileDownloadActivity.this, "취소하였습니다.", Toast.LENGTH_LONG).show();
		}

		@Override
		protected void onPostExecute(Boolean result) {
			pd.dismiss(); // 진행중 표시를 종료한다.
			if (result == Boolean.FALSE) {
				Toast.makeText(FileDownloadActivity.this, "File Download Error", Toast.LENGTH_LONG).show();
				Log.d(TAG, "file download cancel or error occurred");
			} else {
				sosiTempFile.renameTo(sosiFile);
				Toast.makeText(FileDownloadActivity.this, "File Download Ended", Toast.LENGTH_LONG).show();
			}
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			pd.setProgress(values[0]); // ProgressDialog에서 현재 진행 비율을 업데이트한다.
		}

	}

}
