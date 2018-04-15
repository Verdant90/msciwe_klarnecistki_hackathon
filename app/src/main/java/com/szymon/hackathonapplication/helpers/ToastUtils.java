package com.szymon.hackathonapplication.helpers;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.szymon.hackathonapplication.R;

public class ToastUtils {

    public static void makeToast(final Context context, final int backgroundColor, final String message, final int textColor, final Drawable icon) {
        Toast toast = new Toast(context);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        View layout = LayoutInflater.from(context).inflate(R.layout.layout_toast_custom,
                null);
        layout.setBackgroundColor(backgroundColor);

        ImageView imageForToast =  layout.findViewById(R.id.image_toast);

        TextView messageTextView =  layout.findViewById(R.id.text_message);
        messageTextView.setText(message);
        messageTextView.setTextColor(textColor);

        imageForToast.setImageDrawable(icon);
        toast.setView(layout);
        toast.show();
    }
}
