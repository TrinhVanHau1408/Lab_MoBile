package com.example.student;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    // Định nghĩa interface cho sự kiện nhấn nút
    public interface OnItemButtonClickListener {
        void onItemUpdateClick(int position);
        void onItemDeleteClick(int position);
    }
    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    private Activity context;
    private List<Student> listStudent;

    private LayoutInflater layoutInflater;
    private OnItemButtonClickListener OnButtonClickListener;

    private OnItemClickListener itemClickListener;
//    private OnItemClickListener clickListener;
    public StudentAdapter(Activity context, List<Student> listStudent, OnItemButtonClickListener buttonClickListener, OnItemClickListener itemClickListener) {
        this.context = context;
        this.OnButtonClickListener = buttonClickListener;
        this.itemClickListener = itemClickListener;
        this.listStudent = listStudent;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public StudentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // gán view
        View view = LayoutInflater.from(context).inflate(R.layout.item_student, parent, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StudentViewHolder holder, final int position) {
        // Gán dữ lieu
        Student student = listStudent.get(position);


        // Gán sự kiện click cho button trong item
        holder.btnUpdateStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Gọi sự kiện nhấn nút thông qua interface
                if (OnButtonClickListener != null) {
                    OnButtonClickListener.onItemUpdateClick(holder.getAdapterPosition());
                }
            }
        });

        //Gán sự kiện click cho button trong item
        holder.btnDeleteStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Gọi sự kiện nhấn nút thông qua interface
                if (OnButtonClickListener != null) {
                    OnButtonClickListener.onItemDeleteClick(holder.getAdapterPosition());
                }
            }
        });


        holder.txtStudentMasv.setText(String.valueOf(student.getID()));

        if (student.getName()!=null) {
            holder.txtStudentName.setText(student.getName());
        }
        else holder.txtStudentName.setText("");

        if (position%2==0)
        {
            holder.csllParent.setBackgroundResource(R.color.white);
        }
        else
        {
            holder.csllParent.setBackgroundResource(R.color.light_blue);
        }
    }

    @Override
    public int getItemCount() {
        if (listStudent == null) return -1;
        return listStudent.size();
    }

    class StudentViewHolder extends RecyclerView.ViewHolder {

        private TextView txtStudentMasv;
        private TextView txtStudentName;

        private Button btnUpdateStudent;

        private Button btnDeleteStudent;
        private  ConstraintLayout csllParent;
        public StudentViewHolder(View itemView) {
            super(itemView);

            txtStudentMasv = itemView.findViewById(R.id.txt_item_student_masv);
            txtStudentName = itemView.findViewById(R.id.txt_item_student_name);
            csllParent = itemView.findViewById(R.id.csll_parent_item_student);
            btnUpdateStudent = itemView.findViewById(R.id.item_btn_edit_student);
            btnDeleteStudent = itemView.findViewById(R.id.item_btn_delete_student);

            // Bắt sự kiện click trên item
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && itemClickListener != null) {
                        itemClickListener.onItemClick(position);
                    }
                }
            });
        }
    }
}
