package br.com.tdaqrcodescaner;

import java.net.URL;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.client.android.history.HistoryActivity;

public class MainActivity extends Activity {

	private String dados_recolhidos;
	public static final int REQUEST_CODE = 0;
	// vai receber o imei qnd for salvar no banco
	private String n;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void digitalizar(View view) {
		Toast.makeText(this, "Você selecionou a opção de digitalizar",
				Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(this,
				com.google.zxing.client.android.CaptureActivity.class);
		startActivityForResult(intent, REQUEST_CODE);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		dados_recolhidos = "Resultado do SCANER: "
				+ data.getStringExtra("SCAN_RESULT") + "( "
				+ data.getStringExtra("SCAN_FORMAT") + ")";
		if (REQUEST_CODE == requestCode && RESULT_OK == resultCode) {
			Toast.makeText(
					this,
					data.getStringExtra("SCAN_FORMAT").toUpperCase()
							+ " lido com sucesso!", Toast.LENGTH_LONG).show();
		}
	}

	public void historicoQr(View view) {
		Toast.makeText(this, "Você selecionou a opção de histórico",
				Toast.LENGTH_SHORT).show();
		Intent passarDados = new Intent(this, HistoryActivity.class);
		passarDados.putExtra("dados_inscrito", dados_recolhidos);
		startActivity(passarDados);

	}

	// pegando o IMEI do celular
	public String numCel() {
		String IMEI = "";

		TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		IMEI = telephonyManager.getDeviceId();

		return IMEI;
	}
}
