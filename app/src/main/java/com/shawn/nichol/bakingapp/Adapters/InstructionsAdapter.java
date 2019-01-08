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
        private final TextView mInstructionsTextView;
        private final TextView mShortDescriptionTextView;

        private ListViewHolder(View itemView) {
            super(itemView);
            mInstructionsTextView = itemView.findViewById(R.id.instructions_steps_text_view);
            mShortDescriptionTextView = itemView.findViewById(R.id.instructions_short_description_tv_row);
        }

        private void bindView(int position) {
            String steps = "STEPS: " + InstructionsExtractSteps.stepsIdList.get(position);
            mInstructionsTextView.setText(steps);
            mShortDescriptionTextView.setText(InstructionsExtractSteps.stepsShortDescriptionList.get(position));
        }

        public void onClick(View view) {
            // Leave Blank
        }
    }
}
