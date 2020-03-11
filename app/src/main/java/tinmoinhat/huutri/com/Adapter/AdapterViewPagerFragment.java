package tinmoinhat.huutri.com.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import tinmoinhat.huutri.com.Fragment.Frm;

public class AdapterViewPagerFragment extends FragmentPagerAdapter {
    private int page_num;
    private Context context;

    private String[] tabTitles = new String[]{"Tin Hot","Pháp Luật", "Thế Giới" , "Thể Thao", "Xã Hội", "Văn Hóa", "Kinh Tế", "Công Nghệ", "Giải Trí"
            , "Giáo Dục", "Sức Khỏe", "Nhà Đất", "Xe Cộ"};
    private String[] cateID=new String[]{"0","8","1","6","2","3","4","5","7","9","10","11","12"};
    private final String URL="&page=0";

    public AdapterViewPagerFragment(Context context,FragmentManager fm, int page_num) {
        super(fm);
        this.context=context;
        this.page_num = page_num;
    }

    @Override
    public Fragment getItem(int position) {
        SharedPreferences preferences=context.getSharedPreferences("URL",Context.MODE_PRIVATE);
        String url=preferences.getString("cate","");
        if(!url.equals("")) {
            switch (position) {
                case 0:
                    return Frm.newInstant(0,cateID[position], tabTitles[0], url+cateID[0]+URL);
                case 1:
                    return Frm.newInstant(1,cateID[position], tabTitles[1], url+cateID[1]+URL);
                case 2:
                    return Frm.newInstant(2,cateID[position], tabTitles[2], url+cateID[2]+URL);
                case 3:
                    return Frm.newInstant(3,cateID[position], tabTitles[3], url+cateID[3]+URL);
                case 4:
                    return Frm.newInstant(4,cateID[position], tabTitles[4], url+cateID[4]+URL);
                case 5:
                    return Frm.newInstant(5,cateID[position], tabTitles[5], url+cateID[5]+URL);
                case 6:
                    return Frm.newInstant(6,cateID[position], tabTitles[6], url+cateID[6]+URL);
                case 7:
                    return Frm.newInstant(7,cateID[position], tabTitles[7], url+cateID[7]+URL);
                case 8:
                    return Frm.newInstant(8,cateID[position], tabTitles[8], url+cateID[8]+URL);
                case 9:
                    return Frm.newInstant(9,cateID[position], tabTitles[9], url+cateID[9]+URL);
                case 10:
                    return Frm.newInstant(10,cateID[position], tabTitles[10], url+cateID[10]+URL);
                case 11:
                    return Frm.newInstant(11,cateID[position], tabTitles[11], url+cateID[11]+URL);
                case 12:
                    return Frm.newInstant(12,cateID[position], tabTitles[12], url+cateID[12]+URL);
                default:
                    return null;
            }
        }
        return null;
    }

    @Override
    public int getCount() {
        return page_num;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}