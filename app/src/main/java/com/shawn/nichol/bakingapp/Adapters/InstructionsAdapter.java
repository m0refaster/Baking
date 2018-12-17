package com.shawn.nichol.bakingapp.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shawn.nichol.bakingapp.Data.InstructionsExtractSteps;
import com.shawn.nichol.bakingapp.R;

public class InstructionsAdapter extends RecyclerView.Adapter  {

   private static final String LOGTAG = "InstructionsAdapter";

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.instructions_row,
                parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ListViewHolder) holder).bindView(position);
    }

    @Override
    public int getItemCount() {

        return InstructionsExtractSteps.stepsIdList.size();
    }

    private class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mInstructionsTextView;
        private TextView mShortDescriptionTextView;

        public ListViewHolder(View itemView) {
            super(itemView);
            mInstructionsTextView = (TextView) itemView.findViewById(R.id.instructions_tv_row);
            mShortDescriptionTextView = (TextView)
                    itemView.findViewById(R.id.instructions_shortdescription_tv_row);
        }

        public void bindView(int position) {
            // TODO change recipe list
            mInstructionsTextView.setText(InstructionsExtractSteps.stepsIdList.get(position));
            mShortDescriptionTextView.setText(InstructionsExtractSteps.stepsShortDescriptionList.get(position));
        }

        public void onClick(View view) {
            // Leave Blank
        }
    }
}
