package com.tangent.assessment.tangentboard.fragment;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tangent.assessment.tangentboard.R;
import com.tangent.assessment.tangentboard.adapter.EmpReviewRecyclerView;
import com.tangent.assessment.tangentboard.adapter.NextOfKinRecyclerView;
import com.tangent.assessment.tangentboard.adapter.PositionRecyclerView;
import com.tangent.assessment.tangentboard.apiservice.RetrofitClient;
import com.tangent.assessment.tangentboard.database.DatabaseHelper;
import com.tangent.assessment.tangentboard.model.EmployeeNextOfKin;
import com.tangent.assessment.tangentboard.model.EmployeeReview;
import com.tangent.assessment.tangentboard.model.MyEmployeeData;
import com.tangent.assessment.tangentboard.model.Position;

import java.util.ArrayList;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProfileFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {


    private static final String TAG = ProfileFragment.class.getSimpleName();

    private TextView mId, mFirstName, mLastName, mEmail, mUsername, mActive, mStaff, mSuperuser;

    private TextView mIdNumber, mPhoneNumber, mPAddress, mTaxNumber, mWEmail, mPEmail, mGithub;

    private TextView mBirthDate, mStartDate, mEndDate, mIdDoc, mVisaDoc, mIsEmployed, mIsForeigner;

    private TextView mGender, mRace, mYearsWorked, mAge, mNextReview, mDaysToBd, mLeaveRemaining;

    private RecyclerView mPosition, mNextOfKin, mEmpReview;

    private View mView;

    private ArrayList<Position> mPositionList = new ArrayList<>();

    private ArrayList<EmployeeNextOfKin> mNOKList = new ArrayList<>();

    private ArrayList<EmployeeReview> mEmpReviewList = new ArrayList<>();

    private NextOfKinRecyclerView mNokAdapter;

    private PositionRecyclerView mPositionAdapter;

    private EmpReviewRecyclerView mEmpReviewAdapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (mView == null){
            mView = inflater.inflate(R.layout.fragment_profile, container, false);

            //TEXTVIEW REFERENCES
            mId = (TextView)mView.findViewById(R.id.profile_id);
            mLastName = (TextView)mView.findViewById(R.id.profile_lname);
            mFirstName = (TextView)mView.findViewById(R.id.profile_fname);
            mUsername = (TextView)mView.findViewById(R.id.profile_username);
            mEmail = (TextView)mView.findViewById(R.id.profile_email);
            mActive = (TextView)mView.findViewById(R.id.profile_active);
            mStaff = (TextView)mView.findViewById(R.id.profile_staff);
            mSuperuser = (TextView)mView.findViewById(R.id.profile_superuser);
            mIdNumber = (TextView)mView.findViewById(R.id.profile_id_number);
            mPhoneNumber = (TextView)mView.findViewById(R.id.profile_phone);
            mPAddress = (TextView)mView.findViewById(R.id.profile_p_address);
            mTaxNumber = (TextView)mView.findViewById(R.id.profile_tax_number);
            mWEmail = (TextView)mView.findViewById(R.id.profile_wemail);
            mPEmail = (TextView)mView.findViewById(R.id.profile_p_email);
            mGithub = (TextView)mView.findViewById(R.id.profile_github);
            mBirthDate = (TextView)mView.findViewById(R.id.profile_birth_date);
            mStartDate = (TextView)mView.findViewById(R.id.profile_start_date);
            mEndDate = (TextView)mView.findViewById(R.id.profile_end_date);
            mIdDoc = (TextView)mView.findViewById(R.id.profile_id_doc);
            mVisaDoc = (TextView)mView.findViewById(R.id.profile_visa_doc);
            mIsEmployed = (TextView)mView.findViewById(R.id.profile_is_emp);
            mIsForeigner = (TextView)mView.findViewById(R.id.profile_is_foreigner);
            mGender = (TextView)mView.findViewById(R.id.profile_gender);
            mRace = (TextView)mView.findViewById(R.id.profile_race);
            mYearsWorked = (TextView)mView.findViewById(R.id.profile_years_worked);
            mDaysToBd = (TextView)mView.findViewById(R.id.profile_d_to_bd);
            mAge = (TextView)mView.findViewById(R.id.profile_age);
            mNextReview = (TextView)mView.findViewById(R.id.profile_next_review);
            mLeaveRemaining = (TextView)mView.findViewById(R.id.profile_leave_remaining);

            //RECYCLERVIEW REFERENCES
            mPosition = (RecyclerView) mView.findViewById(R.id.profile_position);
            mPosition.setLayoutManager(new LinearLayoutManager(getActivity()));
            mNextOfKin = (RecyclerView) mView.findViewById(R.id.profile_next_of_kin);
            mNextOfKin.setLayoutManager(new LinearLayoutManager(getActivity()));
            mEmpReview = (RecyclerView) mView.findViewById(R.id.profile_emp_review);
            mEmpReview.setLayoutManager(new LinearLayoutManager(getActivity()));

            //FILL FIELDS
            fillFields();
        }
        return mView;
    }

    protected void fillFields(){

        Cursor cursor = getActivity().getContentResolver().query(DatabaseHelper.LOGIN_CONTENT_URI, null, null, null, null);

        if (cursor != null && cursor.getCount() > 0){
            cursor.moveToFirst();
            mId.setText(String.valueOf(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.ID))));
            mFirstName.setText(cursor.getString(cursor.getColumnIndex(DatabaseHelper.FIRST_NAME)));
            mLastName.setText(cursor.getString(cursor.getColumnIndex(DatabaseHelper.LAST_NAME)));
            mUsername.setText(cursor.getString(cursor.getColumnIndex(DatabaseHelper.USERNAME)));
            mEmail.setText(cursor.getString(cursor.getColumnIndex(DatabaseHelper.EMAIL)));

            //CONVERT SQLITE BOOLEAN INT TO BOOLEAN
            try {
                mActive.setText(String.valueOf(getBoolean(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.IS_ACTIVE)))));
                mStaff.setText(String.valueOf(getBoolean(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.IS_STAFF)))));
                mSuperuser.setText(String.valueOf(getBoolean(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.IS_SUPERUSER)))));
            }catch(Exception e){
                Log.e(TAG, e.getMessage());
                mActive.setText("");
                mStaff.setText("");
                mSuperuser.setText("");
            }
            cursor.close();
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    public boolean getBoolean(int booleanInt) throws Exception{

        if (booleanInt == 0){
            return false;
        }else if (booleanInt == 1){
            return true;
        }else{
            throw new Exception("Invalid boolean integer");
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    protected void getMyEmployeeData(){

        Observable<MyEmployeeData> observable = RetrofitClient.getInstance(getActivity(), true).getApiService().getEmployeesMe();
        observable.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MyEmployeeData>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(MyEmployeeData myEmployeeData) {
                        mPositionList.add(myEmployeeData.getmPosition());
                        mEmpReviewList.addAll(myEmployeeData.getmEmployeeReview());
                        mNOKList.addAll(myEmployeeData.getmEmployeeNextOfKinList());


                        mNokAdapter = new NextOfKinRecyclerView(getActivity(), mNOKList);
                        mNextOfKin.setAdapter(mNokAdapter);

                        mPositionAdapter = new PositionRecyclerView(getActivity(), mPositionList);
                        mPosition.setAdapter(mPositionAdapter);

                        mEmpReviewAdapter = new EmpReviewRecyclerView(getActivity(), mEmpReviewList);
                    }
                });
    }
}
