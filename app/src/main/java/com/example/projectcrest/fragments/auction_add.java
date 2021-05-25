package com.example.projectcrest.fragments;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TimePicker;

import com.example.projectcrest.R;
import com.example.projectcrest.pages.LandingPage;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;
import java.util.Locale;

import static android.Manifest.permission_group.CAMERA;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link auction_add#newInstance} factory method to
 * create an instance of this fragment.
 */
public class auction_add extends Fragment {

    FirebaseAuth firebaseAuth;

    private static final int SELECT_PICTURE = 10;
    private static final int PERMISSION_CODE = 11;
    ImageButton auction_add_imageViewButton;

    FirebaseStorage storage;
    StorageReference storageReference;


    //References to all Auction Add Page EditText
    EditText auction_add_itemNameTxt;
    EditText auction_add_descriptionTxt;
    EditText auction_add_initialPriceTxt;
    EditText auction_add_startTimeTxt;
    EditText auction_add_endTimeTxt;

    //Reference to ImageView
    private ImageView auction_add_image;

    final Calendar myCalendar = Calendar.getInstance();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Uri imageUri;


    public auction_add() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment auction_add.
     */
    // TODO: Rename and change types and number of parameters
    public static auction_add newInstance(String param1, String param2) {
        auction_add fragment = new auction_add();
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
        View v = inflater.inflate(R.layout.fragment_auction_add, container, false);

        auction_add_imageViewButton = v.findViewById(R.id.auction_add_imageView_button);

        auction_add_imageViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View buttonView) {
                imageSelectAction(v);
            }
        });

        dateTimeAction(v);

        return v;
    }

    private void imageSelectAction(View v) {
        auction_add_imageViewButton = v.findViewById(R.id.auction_add_imageView_button);
        auction_add_image = v.findViewById(R.id.auction_add_imageView);

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, ""), SELECT_PICTURE);

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                if (data != null && data.getData() != null) {
                    Uri selectedImageUri = data.getData();

                    auction_add_image.setImageURI(selectedImageUri);

                    uploadImage();
                }
            }
        }
    }

    private void uploadImage() {
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
    }

    private void dateTimeAction(View v) {
        auction_add_startTimeTxt = v.findViewById(R.id.auction_add_startTimePicker);
        auction_add_endTimeTxt = v.findViewById(R.id.auction_add_endTimePicker);

        auction_add_startTimeTxt.setInputType(InputType.TYPE_NULL);

        auction_add_startTimeTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateTimeDialog(auction_add_startTimeTxt);
            }
        });

        auction_add_endTimeTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateTimeDialog(auction_add_endTimeTxt);
            }
        });
    }

    private void showDateTimeDialog(EditText timeTxt) {
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        calendar.set(Calendar.MINUTE, minute);

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd HH:mm");

                        timeTxt.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                };

                new TimePickerDialog(getContext(), timeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false).show();
            }
        };

        new DatePickerDialog(getContext(), dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }
}
