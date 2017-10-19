package developer.retrofitexample.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import developer.retrofitexample.R;
import developer.retrofitexample.model.UserModel;

/**
 * Created by appaz on 10/19/17.
 */

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {

    List<UserModel> mItems;
    Context context;

    public CardAdapter(Context context) {
        super();
        mItems = new ArrayList<UserModel>();
        this.context = context;
    }

    public void addData(UserModel userModel) {
        mItems.add(userModel);
        notifyDataSetChanged();
    }

    public void clear() {
        mItems.clear();
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        UserModel userModel = mItems.get(position);
        holder.login.setText(userModel.getLogin());
        holder.repos.setText("repos: " + userModel.getPublicRepos());
        holder.blog.setText("blog: " + userModel.getBlog());
        if (!userModel.getAvatar_url().equals("null")) {
            Picasso.with(context).load(userModel.getAvatar_url()).into(holder.avatar);
        }

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView login;
        public TextView repos;
        public TextView blog;
        public ImageView avatar;

        public ViewHolder(View itemView) {
            super(itemView);
            login = (TextView) itemView.findViewById(R.id.login);
            repos = (TextView) itemView.findViewById(R.id.repos);
            blog = (TextView) itemView.findViewById(R.id.blog);
            avatar = (ImageView) itemView.findViewById(R.id.avatar);
        }
    }
}
