package ois.internship.view.ui.Cards;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;

import ois.internship.view.activity.BaseActivity;


public abstract class CardFragment extends Fragment {

    public CardAdapter adapter;
    ArrayList<CardModel> data;
    CardLayout cardLayout;
    BaseActivity activity;
    GridView gridView;
    FragmentTransaction fragmentTransaction;

    public CardFragment(BaseActivity activity, GridView gridView, CardLayout cardLayout){
        this.activity = activity;
        this.gridView = gridView;
        this.cardLayout = cardLayout;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentTransaction = activity.getFragmentManager().beginTransaction();
        fragmentTransaction.add(cardLayout.gridViewLayout, this).commit();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public void reload(){
        if(adapter == null){
            adapter = new CardAdapter(activity, data, cardLayout);
            gridView.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
            gridView.invalidateViews();
        }
    }

    public void setData(ArrayList<CardModel> data){
        this.data = data;
        reload();
    }
}
