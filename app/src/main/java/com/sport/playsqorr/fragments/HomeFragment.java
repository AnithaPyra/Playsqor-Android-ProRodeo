package com.sport.playsqorr.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sport.playsqorr.R;
import com.sport.playsqorr.dynamic.Leagues;
import com.sport.playsqorr.dynamic.MenuAdapter;
import com.sport.playsqorr.dynamic.MenuData;
import com.sport.playsqorr.dynamic.RetrofitDataService;
import com.sport.playsqorr.dynamic.RetrofitInstance;
import com.sport.playsqorr.dynamic.SubMenuAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    Context mContext;
    RecyclerView rvMenu,rvSubMenu;
    MenuAdapter menuAdapter;
    SubMenuAdapter subMenuAdapter;
    public ArrayList<MenuData> menuArrayList;
    public ArrayList<Leagues> subMenuArrayList;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);


    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvMenu = (RecyclerView) getView().findViewById(R.id.rvmenu);
        rvSubMenu = (RecyclerView) getView().findViewById(R.id.rvSubmenu);
        rvMenu.setLayoutManager(new LinearLayoutManager(mContext));
        menuArrayList = new ArrayList<>();
//        menuArrayList.addAll(response.body());
        MenuData obj = new MenuData("0","ALL SPORTS","0",new ArrayList<>());
        menuArrayList.add(0,obj);
        menuAdapter = new MenuAdapter(menuArrayList, mContext, getActivity(), HomeFragment.this);
        RecyclerView.LayoutManager menuLayout = new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
        rvMenu.setLayoutManager(menuLayout);
        rvMenu.setItemAnimator(new DefaultItemAnimator());
        rvMenu.setAdapter(menuAdapter);


        /** Create handle for the RetrofitInstance interface*/
        RetrofitDataService service = RetrofitInstance.getRetrofitInstance().create(RetrofitDataService.class);

        Call<ArrayList<MenuData>> menuCall = service.getmenu();
        Log.wtf("URL Called", menuCall.request().url() + "");
        menuCall.enqueue(new Callback<ArrayList<MenuData>>() {
            @Override
            public void onResponse(Call<ArrayList<MenuData>> call, Response<ArrayList<MenuData>> response) {
                System.out.println("response check -->"+response.body());
                menuArrayList.addAll(response.body());
                menuAdapter = new MenuAdapter(menuArrayList, mContext, getActivity(), HomeFragment.this);
                RecyclerView.LayoutManager menuLayout = new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
                rvMenu.setLayoutManager(menuLayout);
                rvMenu.setItemAnimator(new DefaultItemAnimator());
                rvMenu.setAdapter(menuAdapter);
            }

            @Override
            public void onFailure(Call<ArrayList<MenuData>> call, Throwable t) {

            }
        });
    }

    public void refreshSubMenu(ArrayList<Leagues> leagues) {
//        if (leagues == null)
//        {     leagues.clear(); }

            rvSubMenu.setLayoutManager(new LinearLayoutManager(mContext));
            subMenuAdapter = new SubMenuAdapter(leagues, mContext, getActivity());
            RecyclerView.LayoutManager menuLayout = new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
            rvSubMenu.setLayoutManager(menuLayout);
            rvSubMenu.setItemAnimator(new DefaultItemAnimator());
            rvSubMenu.setAdapter(subMenuAdapter);



    }
}