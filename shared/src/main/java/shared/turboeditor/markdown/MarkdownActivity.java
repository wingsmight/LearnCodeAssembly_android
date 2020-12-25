

package shared.turboeditor.markdown;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.mukesh.MarkdownView;

public class MarkdownActivity extends Activity {
    @Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		MarkdownView webView = new MarkdownView(this);
		setContentView(webView);
		webView.setMarkDownText(getIntent().getStringExtra("text"));
	}
}