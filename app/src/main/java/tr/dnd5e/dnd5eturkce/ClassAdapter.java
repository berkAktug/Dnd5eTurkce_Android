package tr.dnd5e.dnd5eturkce;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.text.MessageFormat;
import java.util.List;

public class ClassAdapter extends ArrayAdapter<Class> {

    private Activity context;

    ClassAdapter(Activity context, List<Class> classList) {
        super(context, R.layout.row_class, classList);
        this.context = context;
    }
    private TextView tv_tittle;
    private TextView tv_note;

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View rowView = convertView;
        MyViewHolder holder;
        if (rowView == null) {
            LayoutInflater inflater = ((Activity) getContext()).getLayoutInflater();
            rowView = inflater.inflate(R.layout.row_class, null);
        }

        if (rowView.getTag() == null) {
            holder = new MyViewHolder(rowView);
            rowView.setTag(holder);
        } else {
            holder = (MyViewHolder) rowView.getTag();
        }

        holder.titleTextView.setText(getItem(position).getName());

//        String description = getItem(position).getDescription();
//        if (note.length() > 70) {
//            holder.descriptionTextView.setText(MessageFormat.format("{0}...", note.substring(0, 67)));
//        } else {
        holder.descriptionTextView.setText(getItem(position).getDescription());
//        }

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "You have clicked on row item", Toast.LENGTH_SHORT).show();
//                rowOnClick(getItem(position));
            }
        });

        return rowView;
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView titleTextView;
        private TextView descriptionTextView;

        MyViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.class_row_title);
            descriptionTextView = itemView.findViewById(R.id.class_row_description);
        }
    }

}
