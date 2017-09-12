package forums.minimissions.com.mini_missions;

import android.content.DialogInterface;
import android.provider.Settings;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DialogTitle;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.webkit.WebSettings;

import static android.R.id.button1;

public class MainActivity extends AppCompatActivity {
     WebView Web;
    SwipeRefreshLayout swipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        swipe = (SwipeRefreshLayout)findViewById(R.id.swipe);
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
                                   {
                                       @Override
                                       public void onRefresh(){
                                           Web.reload();
                                       }
                                   }
        );
        Web = (WebView) findViewById(R.id.myWeb);
        Web.setWebViewClient(new WebViewClient());
        Web.loadUrl("http://forum.mini-missions.org/");
        Web.getSettings().setSupportZoom(true);
        Web.getSettings().setBuiltInZoomControls(true);
        Web.getSettings().setJavaScriptEnabled(true);
        Web.getSettings().setDisplayZoomControls(false);
        Web.getSettings().setDomStorageEnabled(true);
        Web.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView view, String url) {
                swipe.setRefreshing(false);
            }
        });

    }

    @Override
    public void onBackPressed()
    {
        if(Web.canGoBack()){
            Web.goBack();
        } else {
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_info)
                    .setTitle("Closing Forums")
                    .setMessage("Are you sure that you want to leave the forums?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }

                    })
                    .setNegativeButton("No", null)
                    .show();
        }

    }

}