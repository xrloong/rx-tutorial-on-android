package idv.xrloong.rxtutorialonandroid.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import idv.xrloong.rxtutorialonandroid.R;

/**
 * Created by xanadu on 2017/1/4.
 */

public class ObserverFragment extends Fragment {
    @BindView(R.id.text_given_name)         TextView mTextViewGivenName;
    @BindView(R.id.text_family_name)        TextView mTextViewFamilyName;
    @BindView(R.id.text_age)                TextView mTextViewAge;

    private User mUser;

    private static class User {
        private String mGivenName;
        private String mFamilyName;
        private int mAge;

        OnAgeChangedListener mOnAgeChangedListener;
        public interface OnAgeChangedListener {
            void onAgeChanged(int newAge);
        }

        public void setOnAgeChangedListener(OnAgeChangedListener listener) {
            mOnAgeChangedListener = listener;
        }

        private void notifyAgeChanged(int newAge) {
            if(mOnAgeChangedListener != null) {
                mOnAgeChangedListener.onAgeChanged(newAge);
            }
        }

        public String getGivenName() {
            return mGivenName;
        }

        public void setGivenName(String mGivenName) {
            this.mGivenName = mGivenName;
        }

        public String getFamilyName() {
            return mFamilyName;
        }

        public void setFamilyName(String mFamilyName) {
            this.mFamilyName = mFamilyName;
        }

        public int getAge() {
            return mAge;
        }

        public void setAge(int mAge) {
            this.mAge = mAge;
            notifyAgeChanged(mAge);
        }
    }

    public static ObserverFragment newInstance() {
        return new ObserverFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mUser = new User();
        mUser.setGivenName("Donald");
        mUser.setFamilyName("Trump");
        mUser.setAge(70);

        mUser.setOnAgeChangedListener(new User.OnAgeChangedListener() {
            @Override
            public void onAgeChanged(int newAge) {
                mTextViewAge.setText(String.valueOf(user.getAge()));
            }
        });

        bindViewWithData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_simple, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        bindViewWithData();
    }

    private void bindViewWithData() {
        if(mTextViewGivenName != null) {
            setUser(mUser);
        }
    }

    private void setUser(User user) {
        mTextViewGivenName.setText(user.getGivenName());
        mTextViewFamilyName.setText(user.getFamilyName());
        mTextViewAge.setText(String.valueOf(user.getAge()));
    }
}
