package com.alpha.vidyaroha.fragments;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.alpha.vidyaroha.R;
import com.alpha.vidyaroha.javaClass.RequiredObject;

import java.io.ByteArrayOutputStream;

import io.realm.RealmResults;

import static com.alpha.vidyaroha.activities.TabActivity.realm;
import static com.alpha.vidyaroha.activities.TabActivity.requiredObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class TakePicture extends Fragment {

    private final int REQUEST_IMAGE_CAPTURE_FROM_1 = 1;
    private final int REQUEST_IMAGE_CAPTURE_FROM_2 = 2;
    private Button takePicture1, takePicture2;
    private ImageView thumbnailPicture1, thumbnailPicture2;
    private RealmResults<RequiredObject> results = realm.where(RequiredObject.class).findAll();

    public TakePicture() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_take_picture, container, false);

        initViews(view);
        onClickEvents();

        return view;
    }

    // Initializes the elements of the xml.
    private void initViews(View view) {
        takePicture1 = view.findViewById(R.id.take_picture1);
        takePicture2 = view.findViewById(R.id.take_picture2);
        thumbnailPicture1 = view.findViewById(R.id.thumbnail_picture1);
        thumbnailPicture2 = view.findViewById(R.id.thumbnail_picture2);

        if (results.get(0).getPicture1() != null){
            thumbnailPicture1.setImageBitmap(getBitmapFromByteArray(requiredObject.getPicture1()));
        }
        if (results.get(0).getPicture2() != null){
            thumbnailPicture2.setImageBitmap(getBitmapFromByteArray(requiredObject.getPicture2()));
        }
    }

    // Setting onClick events.
    private void onClickEvents() {
        takePicture1.setOnClickListener(onTakePicture1Click);
        takePicture2.setOnClickListener(onTakePicture2Click);
    }


    // Actions to be performed on take picture 1 click.
    private View.OnClickListener onTakePicture1Click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(getActivity(), "Take Picture 1", Toast.LENGTH_SHORT).show();
            Intent captureImage = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (captureImage.resolveActivity(getActivity().getPackageManager()) != null) {
                startActivityForResult(captureImage, REQUEST_IMAGE_CAPTURE_FROM_1);
            }

        }
    };

    // Actions to be performed on take picture 2 click.
    private View.OnClickListener onTakePicture2Click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(getActivity(), "Take Picture 2", Toast.LENGTH_SHORT).show();
            Intent captureImage = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (captureImage.resolveActivity(getActivity().getPackageManager()) != null) {
                startActivityForResult(captureImage, REQUEST_IMAGE_CAPTURE_FROM_2);
            }
        }
    };

    private byte[] getBytesFromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
        return stream.toByteArray();
    }

    private Bitmap getBitmapFromByteArray(byte[] image){
        BitmapFactory.Options options = new BitmapFactory.Options();
        return BitmapFactory.decodeByteArray(image, 0, image.length, options);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE_FROM_1 && resultCode == Activity.RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            realm.beginTransaction();
            results.get(0).setPicture1(getBytesFromBitmap(imageBitmap));
            realm.commitTransaction();
            thumbnailPicture1.setImageBitmap(imageBitmap);
        }
        if (requestCode == REQUEST_IMAGE_CAPTURE_FROM_2 && resultCode == Activity.RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            realm.beginTransaction();
            results.get(0).setPicture2(getBytesFromBitmap(imageBitmap));
            realm.commitTransaction();
            thumbnailPicture2.setImageBitmap(imageBitmap);
        }
    }
}
