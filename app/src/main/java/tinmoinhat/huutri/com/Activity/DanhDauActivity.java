package tinmoinhat.huutri.com.Activity;

/**
 * Created by Thanh Trung on 21/05/2019.
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import tinmoinhat.huutri.com.Adapter.AdapterSaveArticle;
import tinmoinhat.huutri.com.Model.Article;
import tinmoinhat.huutri.com.R;

public class DanhDauActivity extends AppCompatActivity {

    ArrayList<Article> arrayList = new ArrayList<>();
    RecyclerView recyclerView;
    AdapterSaveArticle adapter;
    LinearLayout XoaTatCa;
    ImageView imgback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_dau);

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.startusColor));
        }

        SharedPreferences appSharedPrefs = getSharedPreferences("save_article", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = appSharedPrefs.getString("save_article", "");
        Type type = new TypeToken<List<Article>>(){}.getType();
        if(!json.equals("")){
            arrayList = gson.fromJson(json, type);
        }
        init();
        XoaTatCa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(arrayList.size()!=0){
                    arrayList.clear();
                    SharedPreferences appSharedPrefs1 = getSharedPreferences("save_article", Context.MODE_PRIVATE);
                    SharedPreferences.Editor prefsEditor1 = appSharedPrefs1.edit();
                    prefsEditor1.remove("save_article");
                    prefsEditor1.commit();
                    prefsEditor1.apply();
                    adapter.notifyDataSetChanged();
                    Toast.makeText(getApplicationContext(),"Xóa thành công",Toast.LENGTH_SHORT).show();
                } else Toast.makeText(DanhDauActivity.this, "Danh sách tin rỗng", Toast.LENGTH_SHORT).show();

            }
        });
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void init(){
        recyclerView = findViewById(R.id.recycler_view_danh_dau);
        adapter = new AdapterSaveArticle(this, arrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        XoaTatCa=findViewById(R.id.xoa_tat_ca_save);
        imgback=findViewById(R.id.imgBack);
    }

}
