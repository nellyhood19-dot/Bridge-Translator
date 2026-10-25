
package com.bridge.translator;

import android.inputmethodservice.InputMethodService;
import android.graphics.Color;
import android.view.View;
import android.view.inputmethod.InputConnection;
import android.widget.Button;
import android.widget.LinearLayout;

public class BridgeKeyboardService extends InputMethodService {

    @Override
    public View onCreateInputView() {
        LinearLayout keyboard = new LinearLayout(this);
        keyboard.setOrientation(LinearLayout.VERTICAL);
        keyboard.setBackgroundColor(Color.rgb(225, 230, 238));

        addRow(keyboard, "qwertyuiop");
        addRow(keyboard, "asdfghjkl");
        addRow(keyboard, "zxcvbnm");

        LinearLayout controls = new LinearLayout(this);
        addKey(controls, "Space", " ");
        addKey(controls, "Delete", "DELETE");
        addKey(controls, "Enter", "\n");
        keyboard.addView(controls);

        return keyboard;
    }

    private void addRow(LinearLayout keyboard, String letters) {
        LinearLayout row = new LinearLayout(this);
        for (char letter : letters.toCharArray()) {
            addKey(row, String.valueOf(letter), String.valueOf(letter));
        }
        keyboard.addView(row);
    }

    private void addKey(LinearLayout row, String label, String value) {
        Button button = new Button(this);
        button.setText(label);
        button.setTextSize(13);
        button.setAllCaps(false);
        button.setPadding(0, 0, 0, 0);

        row.addView(button, new LinearLayout.LayoutParams(
            0, 56, 1f
        ));

        button.setOnClickListener(v -> {
            InputConnection input = getCurrentInputConnection();
            if (input == null) return;

            if (value.equals("DELETE")) {
                input.deleteSurroundingText(1, 0);
            } else {
                input.commitText(value, 1);
            }
        });
    }
}
