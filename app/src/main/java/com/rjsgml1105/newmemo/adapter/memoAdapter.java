package com.rjsgml1105.newmemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rjsgml1105.newmemo.MainActivity;
import com.rjsgml1105.newmemo.R;
import com.rjsgml1105.newmemo.model.Memo;

import java.util.ArrayList;

public class memoAdapter extends RecyclerView.Adapter<memoAdapter.ViewHolder> {

    Context context;
    ArrayList<Memo> memoList;

    public memoAdapter(Context context, ArrayList<Memo> memoList) {
        this.context = context;
        this.memoList = memoList;
    }

    @NonNull
    @Override
    public memoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // xml 파일을 연결하는 작업
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.memo_row, parent, false);
        return new memoAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull memoAdapter.ViewHolder holder, int position) {
        // 뷰에 데이터를 셋팅한다!
        Memo memo = memoList.get(position);

        holder.txtTitle.setText( memo.title);
        holder.txtContent.setText( memo.contents );
    }

    @Override
    public int getItemCount() {
        return memoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtTitle;
        TextView txtContent;
        ImageView imgDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtContent = itemView.findViewById(R.id.txtContents);
            imgDelete = itemView.findViewById(R.id.imgDelete);
        }
    }


}
