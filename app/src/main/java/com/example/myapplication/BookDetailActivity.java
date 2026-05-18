package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class BookDetailActivity extends AppCompatActivity {

    private ImageButton btnBack;
    private ImageView imgBookDetail;
    private TextView tvTitle, tvAuthor, tvPrice, tvStock, tvAboutAuthor, tvDescription;
    private EditText etAddress, etPhone;
    private Button btnBuy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        btnBack = findViewById(R.id.btnBack);
        imgBookDetail = findViewById(R.id.imgBookDetail);
        tvTitle = findViewById(R.id.tvBookTitleDetail);
        tvAuthor = findViewById(R.id.tvBookAuthorDetail);
        tvPrice = findViewById(R.id.tvBookPrice);
        tvStock = findViewById(R.id.tvBookStock);
        tvAboutAuthor = findViewById(R.id.tvAboutAuthor);
        tvDescription = findViewById(R.id.tvBookDescription);
        etAddress = findViewById(R.id.etAddress);
        etPhone = findViewById(R.id.etPhone);
        btnBuy = findViewById(R.id.btnBuy);

        loadBookData();

        btnBack.setOnClickListener(v -> finish());
        btnBuy.setOnClickListener(v -> validateOrder());
    }

    private void loadBookData() {
        int image = getIntent().getIntExtra("image", R.drawable.logo);
        String title = getIntent().getStringExtra("title");
        String author = getIntent().getStringExtra("author");
        String price = getIntent().getStringExtra("price");

        imgBookDetail.setImageResource(image);
        tvTitle.setText(title != null ? title : "Book Title");
        tvAuthor.setText(author != null ? "by " + author : "by Author");
        tvPrice.setText(price != null ? price : "Rp 0");
    }

    private void validateOrder() {
        String address = etAddress.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();

        if (address.isEmpty() || phone.isEmpty()) {
            showErrorDialog("Address and phone number must be filled.");
            return;
        }

        if (!phone.matches("^[0-9]+$")) {
            showErrorDialog("Phone number must be numeric.");
            return;
        }

        showSuccessDialog();
    }

    private void showErrorDialog(String message) {
        new MaterialAlertDialogBuilder(this)
                .setTitle("Validation Error")
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();
    }

    private void showSuccessDialog() {
        new MaterialAlertDialogBuilder(this)
                .setTitle("Success")
                .setMessage("A confirmation email has been sent to your email.")
                .setPositiveButton("OK", (dialog, which) -> {
                    Intent intent = new Intent(BookDetailActivity.this, BookActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                })
                .show();
    }
}