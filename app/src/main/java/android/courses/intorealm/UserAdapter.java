package android.courses.intorealm;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import io.realm.RealmBaseAdapter;
import io.realm.RealmResults;

/**
 * Created by Podisto on 05/04/2016.
 */
public class UserAdapter extends RealmBaseAdapter<User> implements ListAdapter {

    private static class UserViewHolder {
        private TextView pseudo;
        private ImageView avatar;
    }

    public UserAdapter(Context context, int resId, RealmResults<User> realmResults, boolean automaticUpdate) {
        super(context, realmResults, automaticUpdate);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null)
            convertView = inflater.inflate(R.layout.row_user, parent, false);

        UserViewHolder viewHolder = (UserViewHolder) convertView.getTag();
        if(viewHolder == null) {
            viewHolder = new UserViewHolder();
            viewHolder.pseudo = (TextView) convertView.findViewById(R.id.pseudo);
            viewHolder.avatar = (ImageView) convertView.findViewById(R.id.avatar);
            convertView.setTag(viewHolder);
        }

        User item = realmResults.get(position);
        viewHolder.pseudo.setText(item.getFirstName());
        viewHolder.avatar.setImageDrawable(new ColorDrawable(item.getColor()));

        return convertView;
    }

    public RealmResults<User> getRealmResults() {
        return realmResults;
    }

}
