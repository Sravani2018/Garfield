package com.example.garfield;

import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import de.hdodenhof.circleimageview.CircleImageView;


public class MessageViewHolder extends RecyclerView.ViewHolder {

    private static final String TAG = "MessageViewHolder";

    TextView messageTextView;
    ImageView messageImageView;
    TextView messengerTextView;
    CircleImageView messengerImageView;

    TextView ansTextView;
    ImageView ansImageView;
    TextView ansrTextView;
    CircleImageView ansrImageView;

    public MessageViewHolder(View v) {
        super(v);
        messageTextView = (TextView) itemView.findViewById(R.id.RightmessageTextView);
        messageImageView = (ImageView) itemView.findViewById(R.id.RightmessageImageView);
        messengerTextView = (TextView) itemView.findViewById(R.id.RightmessengerTextView);
        messengerImageView = (CircleImageView) itemView.findViewById(R.id.RightmessengerImageView);
        ansTextView = (TextView) itemView.findViewById(R.id.LeftmessageTextView);
        ansImageView = (ImageView) itemView.findViewById(R.id.LeftmessageImageView);
        ansrTextView = (TextView) itemView.findViewById(R.id.LeftmessengerTextView);
        ansrImageView = (CircleImageView) itemView.findViewById(R.id.LeftmessengerImageView);
    }

    public void bindMessage(Chatbot CBMessage) {


        if (CBMessage.getText() != null) {
            if (CBMessage.getName().equals("Bot")){
                ansTextView.setText(CBMessage.getText());
                ansTextView.setVisibility(TextView.VISIBLE);
                ansrImageView.setVisibility(ImageView.VISIBLE);
                messageTextView.setVisibility(TextView.GONE);
                messengerImageView.setVisibility(ImageView.GONE);
                messengerImageView.setVisibility(ImageView.GONE);
            }
            else {
                messageTextView.setText(CBMessage.getText() );
                messageTextView.setVisibility(TextView.VISIBLE);
                Glide.with(messengerImageView.getContext()).load(CBMessage.getPhotoUrl()).into(messengerImageView);
                messengerImageView.setVisibility(ImageView.VISIBLE);
                ansTextView.setVisibility(TextView.GONE);
                ansrImageView.setVisibility(ImageView.GONE);

            }




        } else if (CBMessage.getImageUrl() != null) {
            String imageUrl = CBMessage.getImageUrl();
            if (imageUrl.startsWith("gs://")) {
                StorageReference storageReference = FirebaseStorage.getInstance()
                        .getReferenceFromUrl(imageUrl);

                storageReference.getDownloadUrl()
                        .addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                String downloadUrl = uri.toString();
                                Glide.with(messageImageView.getContext())
                                        .load(downloadUrl)
                                        .into(messageImageView);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Getting download url was not successful.", e);
                            }
                        });
            } else {
                Glide.with(messageImageView.getContext())
                        .load(CBMessage.getImageUrl())
                        .into(messageImageView);
            }

            messageImageView.setVisibility(ImageView.VISIBLE);
            messageTextView.setVisibility(TextView.GONE);

        }
    };
}