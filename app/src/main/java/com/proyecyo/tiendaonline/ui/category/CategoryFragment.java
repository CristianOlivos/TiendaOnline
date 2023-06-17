package com.proyecyo.tiendaonline.ui.category;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.proyecyo.tiendaonline.R;
import com.proyecyo.tiendaonline.adapters.NavCategoryAdapter;
import com.proyecyo.tiendaonline.adapters.PopularAdapter;
import com.proyecyo.tiendaonline.models.NavCategoryModel;
import com.proyecyo.tiendaonline.models.PopularModel;

import java.util.ArrayList;
import java.util.List;


public class CategoryFragment extends Fragment {
    FirebaseFirestore db;
    RecyclerView recyclerView;
    List<NavCategoryModel> categoryModelList;
    NavCategoryAdapter navCategoryAdapter;

    ProgressBar progressBar;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root=inflater.inflate(R.layout.fragment_category, container, false);
        db=FirebaseFirestore.getInstance();
        recyclerView=root.findViewById(R.id.cat_rec);
        progressBar=root.findViewById(R.id.progressbar);
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        //Category items
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));
        categoryModelList = new ArrayList<>();
        navCategoryAdapter = new NavCategoryAdapter(getActivity(),categoryModelList);
        recyclerView.setAdapter(navCategoryAdapter);


        db.collection("NavCategory")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(QueryDocumentSnapshot document: task.getResult()){

                                NavCategoryModel navCategoryModel = document.toObject(NavCategoryModel.class);
                                categoryModelList.add(navCategoryModel);
                                navCategoryAdapter.notifyDataSetChanged();
                                progressBar.setVisibility(View.GONE);
                                recyclerView.setVisibility(View.VISIBLE);

                            }
                        }
                        else{
                            Toast.makeText(getActivity(), "Error "+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        return root;

    }
}