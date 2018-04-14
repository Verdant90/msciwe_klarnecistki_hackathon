package com.szymon.hackathonapplication.activities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.szymon.hackathonapplication.R;
import com.szymon.hackathonapplication.helpers.AppResources;
import com.szymon.hackathonapplication.helpers.ChallengeUtils;
import com.szymon.hackathonapplication.models.challenges.Challenge;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChallengeActivity extends Activity {

    @BindView(R.id.list_challenge)
    ListView listView;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge);
        ButterKnife.bind(this);
        AppResources.getInstance();
        setupAdapter();
    }

    private void setupAdapter() {
        ChallengeAdapter challengeAdapter;
        List<Challenge> challengeList = ChallengeUtils.getChallenges();
        challengeAdapter = new ChallengeAdapter (this, 0, challengeList);
        listView.setAdapter(challengeAdapter);
    }

    public void abc(final float position) {
        Toast.makeText(this, position + " !", Toast.LENGTH_LONG).show();

    }
    public class ChallengeAdapter extends ArrayAdapter<Challenge> {
        private Activity activity;
        private List<Challenge> challengeList;
        private LayoutInflater inflater = null;

        public ChallengeAdapter (Activity activity, int textViewResourceId, List<Challenge> challengeList) {
            super(activity, textViewResourceId, challengeList);
            try {
                this.activity = activity;
                this.challengeList = challengeList;

                inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            } catch (Exception e) {

            }
        }

        public int getCount() {
            return challengeList.size();
        }

        public Challenge getItem(Challenge position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        public  class ViewHolder {
            public TextView description;
            public TextView title;
            public TextView reward;
            public ImageView icon;
            public Button startChallengeButton;

        }

        public View getView(final int position, final View convertView, ViewGroup parent) {
            View vi = convertView;
            final ViewHolder holder;
            try {
                if (convertView == null) {
                    vi = inflater.inflate(R.layout.item_challenge, null);
                    holder = new ViewHolder();

                    holder.description = vi.findViewById(R.id.text_challenge_description);
                    holder.title =  vi.findViewById(R.id.text_challenge_title);
                    holder.reward = vi.findViewById(R.id.text_challenge_reward);
                    holder.icon =  vi.findViewById(R.id.image_challenge_icon);
                    vi.setTag(holder);
                } else {
                    holder = (ViewHolder) vi.getTag();
                }


                holder.description.setText(challengeList.get(position).description);
                holder.title.setText(challengeList.get(position).title);
                holder.reward.setText(challengeList.get(position).pointsReward.toString());
                holder.icon.setImageDrawable(challengeList.get(position).icon);
                convertView.findViewById(R.id.button_start_challenge).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(final View v) {
                        abc(getItemId(position));
                    }
                });
            } catch (Exception e) {


            }
            return vi;
        }
    }
}
