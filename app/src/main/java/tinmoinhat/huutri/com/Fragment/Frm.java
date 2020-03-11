package tinmoinhat.huutri.com.Fragment;

/**
 * Created by Thanh Trung on 20/05/2019.
 */

import android.os.AsyncTask;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import tinmoinhat.huutri.com.Adapter.AdapterRecyclerMain;
import tinmoinhat.huutri.com.GetData;
import tinmoinhat.huutri.com.Model.Article;
import tinmoinhat.huutri.com.R;

public class Frm extends Fragment{

    private String tabtitle, category,id;
    private int page;
    boolean loading = true;
    int pastVisiblesItems,visibleItemCount,totalItemCount, pageLoadMore = 1;
    ArrayList<Article> arrayListSave;
    ArrayList<Article> arrayList,arrayListRelate;
    RecyclerView recyclerView;
    AdapterRecyclerMain adapter;

    public static Frm newInstant(int page,String id, String text, String category) {

        Frm frm = new Frm();
        Bundle argBundle = new Bundle();
        argBundle.putInt("page", page);
        argBundle.putString("tabtitle", text);
        argBundle.putString("id",id);
        argBundle.putString("cate", category);
        frm.setArguments(argBundle);
        return frm;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("page");
        id=getArguments().getString("id");
        tabtitle = getArguments().getString("tabtitle");
        category = getArguments().getString("cate");
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frm, container, false);
        recyclerView = view.findViewById(R.id.recycler_view_main);
        arrayListSave = new ArrayList<>();
        arrayList = new ArrayList<>();
        arrayListRelate = new ArrayList<>();
        adapter = new AdapterRecyclerMain(getContext(), arrayList,category,tabtitle);
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                switch (position%6){
                    case 0: return 1;
                    case 1: return 1;
                    case 2: return 2;
                    case 3: return 1;
                    case 4: return 1;
                    case 5: return 2;
                    default: return 2;
                }
            }
        });
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);

        final SwipeRefreshLayout swipeRefreshLayout = view.findViewById(R.id.swipe_to_refesh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new GetData(getContext(),id, arrayList, adapter, swipeRefreshLayout, arrayListRelate).executeOnExecutor(AsyncTask.SERIAL_EXECUTOR,category);
            }
        });

        new GetData(getContext(),id, arrayList, adapter,swipeRefreshLayout, arrayListRelate).executeOnExecutor(AsyncTask.SERIAL_EXECUTOR,category);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener()
        {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy)
            {
                if(dy > 0) //check for scroll down
                {
                    visibleItemCount = gridLayoutManager.getChildCount();
                    totalItemCount = gridLayoutManager.getItemCount();
                    pastVisiblesItems = gridLayoutManager.findFirstVisibleItemPosition();

                    if (loading)
                    {
                        if ( (visibleItemCount + pastVisiblesItems) >= totalItemCount)
                        {
                            loading = false;
                            //Do pagination.. i.e. fetch new data.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,category);
                            new GetData(getContext(),id, arrayList, adapter,  swipeRefreshLayout, arrayListRelate).executeOnExecutor(AsyncTask.SERIAL_EXECUTOR,category+"&page="+pageLoadMore);
                            pageLoadMore++;
                            loading = true;
                        }
                    }
                }
            }
        });
        return view;
    }
}

