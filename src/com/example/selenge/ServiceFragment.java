package com.example.selenge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.example.selenge.adapter.ExpandableListAdapter;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;

public class ServiceFragment extends Fragment {
	
	private ExpandableListAdapter listAdapter;
    private ExpandableListView expListView;
    private List<String> listDataHeader;
    private HashMap<String, List<String>> listDataChild;
    
	public ServiceFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_service, container, false);
		expListView = (ExpandableListView) rootView.findViewById(R.id.servicelvExp);

		this.prepareListData();

		listAdapter = new ExpandableListAdapter(getActivity(), listDataHeader, listDataChild);
		expListView.setAdapter(listAdapter);
        expListView.setOnGroupClickListener(new OnGroupClickListener() { 
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
            	if(groupPosition != 5 && groupPosition != 6) {
            		toTransfer(String.valueOf(groupPosition + 2));
            	}
                return false;
            }
        });
 
        expListView.setOnChildClickListener(new OnChildClickListener() {
 
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub
            	toTransfer(String.valueOf(groupPosition + 2 + "_" + (childPosition + 1)));
                return false;
            }
        });
        
        return rootView;
    }
	
	private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();
 
        listDataHeader.add("1. Эрүүл мэндийн газраас үзүүлэх үйлчилгээ");
        listDataHeader.add("2. Стандартчилал хэмжил зүйн хэлтэс");
        listDataHeader.add("3. Хөдөлмөрийн хэлтэс");
        listDataHeader.add("4. Биеийн тамир спортын газар");
        listDataHeader.add("5. Хүүхэд, гэр бүлийн хөгжлийн хэлтэс");
        listDataHeader.add("6. Нийгмийн халамж үйлчилгээний хэлтэс");
        listDataHeader.add("7. Улсын бүртгэл, статистикийн газар");
        listDataHeader.add("8. Боловсрол, соёлын газар");
        listDataHeader.add("9. Онцгой байдлын газар");
        listDataHeader.add("10. Шүүхийн шийдвэр гүйцэтгэх алба");
        listDataHeader.add("11. Байгаль орчин, аялал жуулчлалын газар");
        listDataHeader.add("12. Газрын харилцаа, Барилга, хот байгуулалтын газар");
        listDataHeader.add("13. Хүнс, хөдөө аж ахуйн газар");
        listDataHeader.add("14. Цагдаагийн газар");
        listDataHeader.add("15. Нийгмийн даатгалын хэлтэс");        
 
        // Adding child data
        List<String> expChild1 = new ArrayList<String>();
        expChild1.add("6.1. Нийгмийн халамжийн тэтгэвэр");
        expChild1.add("6.2. Нийгмийн халамжийн тэтгэмж");
        expChild1.add("6.3. Ахмад настанд олгох хөнгөлөлт тусламж");
        expChild1.add("6.4. Алдар цолтой ахмад настанд улсын төсвөөс олгож байгаа тусламж, хөнгөлөлт");
        expChild1.add("6.5. Хөгжлийн бэрхшээлтэй иргэнд олгох хөнгөлөлт тусламж");
        expChild1.add("6.6. Хүүхдийн мөнгө олгох үйлчилгээ");
        
        List<String> expChild2 = new ArrayList<String>();
        expChild2.add("7.1. Иргэний бүртгэлийн чиглэлээр");
        expChild2.add("7.2. Эд хөрөнгийн бүртгэлийн чиглэлээр");
        expChild2.add("7.3. Хуулийн этгээдийн бүртгэлийн чиглэлээр");
        
        List<String> expChild3 = new ArrayList<String>();
 
        listDataChild.put(listDataHeader.get(0), expChild3);
        listDataChild.put(listDataHeader.get(1), expChild3);
        listDataChild.put(listDataHeader.get(2), expChild3);
        listDataChild.put(listDataHeader.get(3), expChild3);
        listDataChild.put(listDataHeader.get(4), expChild3);
        listDataChild.put(listDataHeader.get(5), expChild1);
        listDataChild.put(listDataHeader.get(6), expChild2);
        listDataChild.put(listDataHeader.get(7), expChild3);
        listDataChild.put(listDataHeader.get(8), expChild3);
        listDataChild.put(listDataHeader.get(9), expChild3);
        listDataChild.put(listDataHeader.get(10), expChild3);
        listDataChild.put(listDataHeader.get(11), expChild3);
        listDataChild.put(listDataHeader.get(12), expChild3);
        listDataChild.put(listDataHeader.get(13), expChild3);
        listDataChild.put(listDataHeader.get(14), expChild3);
        
	}
	
	private void toTransfer(String position) {
		Intent intent = new Intent(getActivity(), ServiceDetailActivity.class);
		intent.putExtra("ID", position);
		startActivity(intent);
	}
}
