package com.example.youtube.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.youtube.Adapter.VideoAdapter;
import com.example.youtube.Model.Video;
import com.example.youtube.R;
import com.example.youtube.SqliteDatabase.DatabaseHelper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class HomeFragment extends Fragment {
    private FirebaseFirestore firestore;
    private RecyclerView recyclerView;
    private VideoAdapter videoAdapter;
    private ArrayList<Video> videoArrayList;
    private DatabaseHelper databaseHelper;

    private TextView videoTitle;

    public HomeFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.video_container);
        videoArrayList = new ArrayList<>();
        videoAdapter = new VideoAdapter(getContext(), videoArrayList);
        videoTitle = view.findViewById(R.id.title_video);
        recyclerView.setAdapter(videoAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        loadVideoData();
        return view;
    }

    private void loadVideoData() {
        firestore = FirebaseFirestore.getInstance();
        databaseHelper = new DatabaseHelper(getActivity());
        firestore.collection("Video")
                .get()
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                })
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        //lấy dữ liệu từ firebase
                        if(task.isSuccessful()) {
                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                                Video video = documentSnapshot.toObject(Video.class);
                                videoArrayList.add(video);
                            }
                        }
                        videoAdapter.notifyDataSetChanged();
                    }
                });
    }
}