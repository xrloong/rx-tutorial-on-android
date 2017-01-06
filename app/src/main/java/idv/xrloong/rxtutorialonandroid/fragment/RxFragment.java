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
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * Created by xanadu on 2017/1/4.
 */

public class RxFragment extends Fragment {
    @BindView(R.id.text_given_name)         TextView mTextViewGivenName;
    @BindView(R.id.text_family_name)        TextView mTextViewFamilyName;
    @BindView(R.id.text_age)                TextView mTextViewAge;

    private User mUser;

    private static class User {
        private String mGivenName;
        private String mFamilyName;
        private int mAge;

        private Subject<Integer> mSubjectAge = PublishSubject.create();

        public Observable<Integer> getObservableAge() {
            return mSubjectAge;
        }

        private void notifyAgeChanged(int newAge) {
            mSubjectAge.onNext(newAge);
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

    public static RxFragment newInstance() {
        return new RxFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mUser = new User();
        mUser.setGivenName("Donald");
        mUser.setFamilyName("Trump");
        mUser.setAge(70);

        mUser.getObservableAge().subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer newAge) throws Exception {
                mTextViewAge.setText(String.valueOf(newAge));
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
