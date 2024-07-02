




package com.example.youtube.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.youtube.Model.User;
import com.example.youtube.R;
import com.example.youtube.SqliteDatabase.DatabaseHelper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


public class ProfileFragment extends Fragment {

    //firebase
    private FirebaseAuth firebaseAuth;
    private DatabaseHelper databaseHelper;
    private StorageReference storageReference;
    private FirebaseUser user;
    private FirebaseFirestore firestore;
    private TextView fullName, phonenumber, dateOfBirth, gender;
    private ImageButton btnChangeUserAvata, btnChangeUserBackground;
    private ImageView userAvata;

    private ActivityResultLauncher<Intent> selectImageLauncher;
    public ProfileFragment() {
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
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        fullName = view.findViewById(R.id.full_name);
        phonenumber = view.findViewById(R.id.phone_number);
        firebaseAuth = FirebaseAuth.getInstance();
        userAvata = view.findViewById(R.id.user_avata);
        //firebase firestorage
        storageReference = FirebaseStorage.getInstance().getReference();
        //chọn ảnh với button image
        selectImageLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            // Handle the result
                            Uri selectedImageUri = data.getData();
                            // Upload ảnh lên Firebase Storage
                            uploadImageToFirebase(selectedImageUri);
                            loadUserProfile();
                        }
                    }
                }
        );
        //đổi avata
        btnChangeUserAvata = view.findViewById(R.id.btn_change_user_avata);
        btnChangeUserAvata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
        btnChangeUserBackground = view.findViewById(R.id.btn_change_user_background_image);
        firestore = FirebaseFirestore.getInstance();
        databaseHelper = new DatabaseHelper(getActivity());
        loadUserProfile();

        return view;
    }

    //lưu hình ảnh
//    private void uploadImageToFirebase(Uri imageUri) {
//        // Tạo một reference duy nhất cho hình ảnh
//        StorageReference fileRef = storageReference.child("user_avata/" + firebaseAuth.getCurrentUser().getUid() + "/avatar.jpg");
//        fileRef.putFile(imageUri).addOnSuccessListener(taskSnapshot -> {
//
//            // Lấy URL của hình ảnh
//            fileRef.getDownloadUrl().addOnSuccessListener(uri -> {
//                // Cập nhật URL của hình ảnh trong Firestore
//                updateUserProfileImage(fileRef.toString());
//            });
//        }).addOnFailureListener(e -> {
//            // Xử lý khi upload thất bại
//            Toast.makeText(getContext(), "Upload failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
//        });
//    }
    private void uploadImageToFirebase(Uri imageUri) {
        // Tạo một reference duy nhất cho hình ảnh
        StorageReference fileRef = storageReference.child("user_avata/" + firebaseAuth.getCurrentUser().getUid() + "/avatar.jpg");
        fileRef.putFile(imageUri).addOnSuccessListener(taskSnapshot -> {

            // Lấy URL của hình ảnh
            fileRef.getDownloadUrl().addOnSuccessListener(uri -> {
                // Cập nhật URL của hình ảnh trong Firestore
                updateUserProfileImage(uri.toString());
            }).addOnFailureListener(e -> {
                // Xử lý khi lấy URL thất bại
                Toast.makeText(getContext(), "Failed to get download URL: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            });
        }).addOnFailureListener(e -> {
            // Xử lý khi upload thất bại
            Toast.makeText(getContext(), "Upload failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        });
    }

    //cập nhật hình ảnh
//    private void updateUserProfileImage(String imageUrl) {
//        //lấy userid từ sqlite database
//        databaseHelper = new DatabaseHelper(getContext());
//        String userId = databaseHelper.getFirstUserId();
//        firestore.collection("User")
//                .whereEqualTo("userId", userId)
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        String docId = "";
//                        for(QueryDocumentSnapshot documentSnapshot : task.getResult()) {
//                            docId = documentSnapshot.getId();
//                        }
//                        firestore.collection("User")
//                                .document(docId)
//                                .update("url_avata", imageUrl)
//                                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                                    @Override
//                                    public void onComplete(@NonNull Task<Void> task) {
//                                        Toast.makeText(getContext(), "Avatar updated successfully", Toast.LENGTH_SHORT).show();
//                                    }
//                                });
//                    }
//                });
//        DocumentReference userRef = firestore.collection("User").document(userId);
//        userRef.update("url_avata", imageUrl).addOnCompleteListener(task -> {
//            if (task.isSuccessful()) {
//                // Cập nhật UI với hình ảnh mới
//                Glide.with(getContext()).load(imageUrl).into(userAvata);
//                Toast.makeText(getContext(), "Avatar updated successfully", Toast.LENGTH_SHORT).show();
//            } else {
//                Toast.makeText(getContext(), "Failed to update avatar" + task.getException(), Toast.LENGTH_SHORT).show();
//                System.out.println(task.getException());
//            }
//        });
//    }
    private void updateUserProfileImage(String imageUrl) {
        // Lấy userId từ SQLite Database
        String userId = databaseHelper.getFirstUserId();

        // Truy vấn Firestore để tìm documentId tương ứng với userId
        firestore.collection("User")
                .whereEqualTo("user_id", userId)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful() && !task.getResult().isEmpty()) {
                            // Lấy documentId
                            DocumentSnapshot documentSnapshot = task.getResult().getDocuments().get(0);
                            String docId = documentSnapshot.getId();

                            // Cập nhật URL avatar
                            firestore.collection("User")
                                    .document(docId)
                                    .update("url_avata", imageUrl)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                // Cập nhật UI với hình ảnh mới
                                                Glide.with(getContext()).load(imageUrl).into(userAvata);
                                                Toast.makeText(getContext(), "Avatar updated successfully", Toast.LENGTH_SHORT).show();
                                            } else {
                                                Toast.makeText(getContext(), "Failed to update avatar", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                        } else {
                            Toast.makeText(getContext(), "User not found", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    private void selectImage() {
        Intent intent = new Intent();
        intent.setType("image/");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        selectImageLauncher.launch(intent);
    }
    private void loadUserProfile() {
        String userId = databaseHelper.getFirstUserId();
        firestore.collection("User")
                .whereEqualTo("user_id", userId)
                .limit(1)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()) {
                            DocumentSnapshot documentSnapshot = task.getResult().getDocuments().get(0);
                            fullName.setText(documentSnapshot.getString("fullname"));
                            phonenumber.setText(documentSnapshot.getString("phone_number"));
                            Glide.with(getContext())
                                    .load(documentSnapshot.getString("url_avata"))
                                    .into(userAvata);
//                            dateOfBirth.setText(documentSnapshot.getString("date_of_birth").toString());
                        }
                    }
                });
    }
}

