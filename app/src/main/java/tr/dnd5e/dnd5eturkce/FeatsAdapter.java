package tr.dnd5e.dnd5eturkce;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.MessageFormat;
import java.util.List;
@Deprecated
public class FeatsAdapter extends ArrayAdapter<Feats> {

    private Activity context;

    FeatsAdapter(Activity context, List<Feats> taskList) {
        super(context, R.layout.row_feats, taskList);
        this.context = context;
    }

    /**
     * customizable toast
     *
     * @param message
     */
    private void toastMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View rowView = convertView;
        MyViewHolder holder;
        if (rowView == null) {
            LayoutInflater inflater = ((Activity) getContext()).getLayoutInflater();
            rowView = inflater.inflate(R.layout.row_feats, null);
        }

        if (rowView.getTag() == null) {
            holder = new MyViewHolder(rowView);
            rowView.setTag(holder);
        } else {
            holder = (MyViewHolder) rowView.getTag();
        }

        String title = getItem(position).getName();
        if (title.length() > 30) {
            holder.titleTextView.setText(MessageFormat.format("{0}...", title.substring(0, 27)));
        } else {
            holder.titleTextView.setText(getItem(position).getName());
        }

//        holder.titleTextView.setText(getItem(position).getTitle());


        String note = getItem(position).getDescription();
        if (note.length() > 70) {
            holder.descriptionTextView.setText(MessageFormat.format("{0}...", note.substring(0, 67)));
        } else {
            holder.descriptionTextView.setText(getItem(position).getDescription());
        }

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

        private TextView  titleTextView;
        private TextView  descriptionTextView;

        MyViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.feats_row_title);
            descriptionTextView = itemView.findViewById(R.id.feats_row_description);
        }
    }

}
