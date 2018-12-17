package com.shawn.nichol.bakingapp.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shawn.nichol.bakingapp.Data.ExtractRecipeData;
import com.shawn.nichol.bakingapp.R;

public class RecipeListAdapter extends RecyclerView.Adapter {
    private static final String LOGTAG = "RecipeAdapter";
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_row,
                parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ListViewHolder) holder).bindView(position);
    }

    @Override
    public int getItemCount() {
        return ExtractRecipeData.recipeList.size();

    }

    private class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mRecipeTextView;

        public ListViewHolder(View itemView) {
            super(itemView);
            mRecipeTextView = (TextView) itemView.findViewById(R.id.recipe_tv);
        }

        public void bindView(int position) {
            mRecipeTextView.setText(ExtractRecipeData.recipeList.get(position));
        }

        public void onClick(View view) {
            // Leave Blank
        }

    }
}
