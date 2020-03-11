package tinmoinhat.huutri.com;

/**
 * Created by Thanh Trung on 20/05/2019.
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import tinmoinhat.huutri.com.Adapter.AdapterRecyclerDetail;
import tinmoinhat.huutri.com.Model.Content;
import tinmoinhat.huutri.com.Model.URLDATA;

public class LoadArticle extends AsyncTask<String, Void, String> {

    public LoadArticle(Context context,String id, ArrayList<Content> arrayList, AdapterRecyclerDetail adapter, FrameLayout frm, ProgressBar progressBar) {
        this.context = context;
        this.id=id;
        this.arrayList = arrayList;
        this.adapter = adapter;
        this.frm = frm;
        this.progressBar = progressBar;
        progressBar.setVisibility(View.VISIBLE);
    }

    Context context;
    ArrayList<Content> arrayList;
    AdapterRecyclerDetail adapter;
    FrameLayout frm;
    ProgressBar progressBar;
    String id;

    OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .retryOnConnectionFailure(true).build();

    @Override
    protected String doInBackground(String... strings) {
        Request.Builder builder = new Request.Builder();
        builder.url(strings[0]);
        Request request = builder.build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        if (s!=null &&!s.equals("")) {
            Document document = Jsoup.parse(s);
            Element element = document.select("div.content-detail").first();

            if (element != null) {

                Elements elements = element.select("p");
                String title = document.select("h3").text();
                String source = document.select("p").first().text();
                arrayList.add(new Content(title, ""));
                arrayList.add(new Content(source, ""));

                //API cũ
                /*String shot = document.select("p.title").first().text();
                arrayList.add(new Content(shot, ""));*/

                //API mới
                String shot;
                if (document.select("p.title").first() != null) {
                    shot = document.select("p.title").first().text();
                    arrayList.add(new Content(shot, ""));
                }
                for (Element e : elements) {
                    String text = "";
                    String img = "";
                    if (e.getElementsByTag("img").first() != null) {
                        img = e.getElementsByTag("img").first().attr("src");
                    }

                    if (img.equals("")) {
                        text = e.text();
                    }
                    arrayList.add(new Content(text, img));
                    if(img.equals(""))
                        adapter.notifyDataSetChanged();
                }
            }

        } else {
            /*SharedPreferences preferences=context.getSharedPreferences("URL",Context.MODE_PRIVATE);
            String url=preferences.getString("detail","");
            SharedPreferences.Editor editorUrl=preferences.edit();
            SharedPreferences preferencesCount=context.getSharedPreferences("retry",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor=preferencesCount.edit();
            int dem=preferencesCount.getInt("dem",0);
            if(!url.equals("")){
                if(dem<5) {
                    Log.d("Myservices", "===Doi IP===");
                    if (url.equals(URLDATA.URL_DATA_CHINH)) {
                        editorUrl.putString("detail",URLDATA.URL_DATA_PHU).apply();
                        new LoadArticle(context, id, arrayList, adapter, frm, progressBar).executeOnExecutor(SERIAL_EXECUTOR, URLDATA.URL_DATA_PHU + id);
                    } else {
                        editorUrl.putString("detail",URLDATA.URL_DATA_CHINH).apply();
                        new LoadArticle(context, id, arrayList, adapter, frm, progressBar).executeOnExecutor(SERIAL_EXECUTOR, URLDATA.URL_DATA_CHINH + id);
                    }
                    dem++;
                    editor.putInt("dem", dem);
                    editor.apply();
                }
            }*/
        }

        frm.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);

        super.onPostExecute(s);
    }
}
