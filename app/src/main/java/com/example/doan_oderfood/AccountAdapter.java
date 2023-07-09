package com.example.doan_oderfood;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.AccountViewHolder> {

    private List<Account> accountList;
    private OnEditClickListener editClickListener;
    private OnDeleteClickListener deleteClickListener;

    public AccountAdapter(List<Account> accountList, OnEditClickListener editClickListener, OnDeleteClickListener deleteClickListener) {
        this.accountList = accountList;
        this.editClickListener = editClickListener;
        this.deleteClickListener = deleteClickListener;
    }

    @NonNull
    @Override
    public AccountViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_account, parent, false);
        return new AccountViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AccountViewHolder holder, int position) {
        Account account = accountList.get(position);
        holder.bind(account);
    }

    @Override
    public int getItemCount() {
        return accountList.size();
    }
    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
        notifyDataSetChanged();
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public class AccountViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewUsername;
        private TextView textViewPassword;
        private TextView textViewIsAdmin;
        private Button buttonEdit;
        private Button buttonDelete;

        public AccountViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewUsername = itemView.findViewById(R.id.textViewUsername);
            textViewPassword = itemView.findViewById(R.id.textViewPassword);
            textViewIsAdmin = itemView.findViewById(R.id.textViewIsAdmin);
            buttonEdit = itemView.findViewById(R.id.buttonEdit);
            buttonDelete = itemView.findViewById(R.id.buttonDelete);
        }

        public void bind(Account account) {
            textViewUsername.setText("Username: " + account.getUsername());
            textViewPassword.setText("Password: " + account.getPassword());
            textViewIsAdmin.setText("isAdmin: " + account.getIsadmin());

            buttonEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (editClickListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            editClickListener.onEditClick(position);
                        }
                    }
                }
            });

            buttonDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (deleteClickListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            deleteClickListener.onDeleteClick(position);
                        }
                    }
                }
            });
        }
    }

    public interface OnEditClickListener {
        void onEditClick(int position);
    }

    public interface OnDeleteClickListener {
        void onDeleteClick(int position);
    }
}
