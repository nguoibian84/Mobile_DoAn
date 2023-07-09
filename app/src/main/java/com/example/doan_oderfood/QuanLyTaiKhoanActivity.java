package com.example.doan_oderfood;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class QuanLyTaiKhoanActivity extends AppCompatActivity implements AccountAdapter.OnEditClickListener, AccountAdapter.OnDeleteClickListener {

    private RecyclerView recyclerView;
    private AccountAdapter accountAdapter;
    private List<Account> accountList;
    private List<Account> searchResults;

    private EditText editTextSearch;
    private Button buttonSearch;

    ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_tai_khoan);

        recyclerView = findViewById(R.id.recyclerViewAccounts);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DBHelper dbHelper = new DBHelper(this);
        accountList = dbHelper.getAllAccounts();
        searchResults = new ArrayList<>(); // Initialize search results list

        accountAdapter = new AccountAdapter(accountList, this, this);
        recyclerView.setAdapter(accountAdapter);

        editTextSearch = findViewById(R.id.editTextSearch);
        buttonSearch = findViewById(R.id.buttonSearch);
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchAccounts();
            }
        });

        back = findViewById(R.id.icon_back3);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AccountAdminActivity.class);
                startActivity(intent);
            }
        });
    }
    public void onEditClick(int position) {
        Account account = getAccountByPosition(position);
        if (account != null) {
            Intent intent = new Intent(QuanLyTaiKhoanActivity.this, EditAccountActivity.class);
            intent.putExtra("username", account.getUsername());
            intent.putExtra("password", account.getPassword());
            intent.putExtra("isAdmin", account.getIsadmin());
            startActivity(intent);
        }
    }

    public void onDeleteClick(int position) {
        Account account = getAccountByPosition(position);
        if (account != null) {
            DBHelper dbHelper = new DBHelper(QuanLyTaiKhoanActivity.this);
            dbHelper.deleteAccount(account);

            // Remove account from appropriate list (accountList or searchResults)
            if (accountAdapter.getAccountList() == accountList) {
                accountList.remove(position);
            } else {
                searchResults.remove(position);
            }

            accountAdapter.notifyItemRemoved(position);
        }
    }

    private Account getAccountByPosition(int position) {
        if (accountAdapter.getAccountList() == accountList) {
            return accountList.get(position);
        } else {
            return searchResults.get(position);
        }
    }

    private void searchAccounts() {
        String keyword = editTextSearch.getText().toString().trim();

        // Clear previous search results
        searchResults.clear();

        // Check if keyword is empty
        if (keyword.isEmpty()) {
            accountAdapter.setAccountList(accountList);
            accountAdapter.notifyDataSetChanged();
            return;
        }

        // Loop through the accountList and add accounts that match the search keyword to searchResults
        for (Account account : accountList) {
            if (account.getUsername().toLowerCase().contains(keyword.toLowerCase())) {
                searchResults.add(account);
            }
        }

        accountAdapter.setAccountList(searchResults);
        accountAdapter.notifyDataSetChanged();
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }
}