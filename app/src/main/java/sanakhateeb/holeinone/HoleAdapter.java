package sanakhateeb.holeinone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class HoleAdapter extends BaseAdapter {
    private final Hole[] mHoles;
    private final Context mContext;

    public HoleAdapter(Context context, Hole[] holes)
    {
        mHoles = holes;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mHoles.length;
    }

    @Override
    public Object getItem(int position) {
        return mHoles[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;   //not used
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;

        if(convertView == null)
        {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.hole_list_item, parent, false);
            holder = new ViewHolder();
            holder.holeLabel = (TextView)convertView.findViewById(R.id.hole_label);
            holder.holeNumText = (TextView)convertView.findViewById(R.id.num_hole_text);
            holder.minusButton = (Button)convertView.findViewById(R.id.minus_button);
            holder.positiveButton = (Button)convertView.findViewById(R.id.plus_button);

            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder)convertView.getTag();
        }

        Hole hole = mHoles[position];
        holder.holeLabel.setText("Hole " + hole.getHoleNum() + ":");
        holder.holeNumText.setText(hole.getScore() + "");

        holder.minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newScore = mHoles[position].getScore() - 1;
                if (newScore < 0)
                {
                    mHoles[position].setScore(0);
                    holder.holeNumText.setText("0");
                }
                else
                {
                    mHoles[position].setScore(newScore);
                    holder.holeNumText.setText(newScore + "");
                }
            }
        });

        holder.positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newScore = mHoles[position].getScore() + 1;
                mHoles[position].setScore(newScore);
                holder.holeNumText.setText(newScore + "");

            }
        });

        return convertView;
    }

    private static class ViewHolder {
        TextView holeLabel;
        TextView holeNumText;
        Button minusButton;
        Button positiveButton;
    }
}
