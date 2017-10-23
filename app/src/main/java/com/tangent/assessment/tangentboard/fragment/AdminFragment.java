package com.tangent.assessment.tangentboard.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.tangent.assessment.tangentboard.R;
import com.tangent.assessment.tangentboard.adapter.EmployeeRecyclerViewAdapter;
import com.tangent.assessment.tangentboard.apiservice.RetrofitClient;
import com.tangent.assessment.tangentboard.model.Employee;
import com.tangent.assessment.tangentboard.model.StatisticsData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AdminFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AdminFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AdminFragment extends Fragment implements AdapterView.OnItemClickListener{

    private static final String TAG = AdminFragment.class.getSimpleName();

    private RecyclerView mRecyclerView;

    private EmployeeRecyclerViewAdapter mAdapter;

    private View mView;

    private Spinner mGender, mRace, mBirthDate, mStartDate, mPosition;

    private ArrayAdapter<String> mGenderArray, mRaceArray, mBirthDateArray, mStartDateArray, mPositionArray;

    private ArrayList<Employee> mEmployeeList = new ArrayList<>();

    private Button mQuery;

    private EditText mEmailContains, mUser;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public AdminFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AdminFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AdminFragment newInstance(String param1, String param2) {
        AdminFragment fragment = new AdminFragment();
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
            mView = inflater.inflate(R.layout.fragment_admin, container, false);

            //RECYCLERVIEW REFERENCE
            mRecyclerView = (RecyclerView)mView.findViewById(R.id.admin_rv);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

            //EDITTEXT REFERENCES
            mEmailContains = (EditText)mView.findViewById(R.id.admin_email);
            mUser = (EditText)mView.findViewById(R.id.admin_user);

            //BUTTON REFERENCE
            mQuery = (Button)mView.findViewById(R.id.admin_query);

            //SET BUTTON ONCLICKLISTENER
            mQuery.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //SET RECYCLERVIEW DATA LIST
                    setEmployeeRecyclerView();
                }
            });

            //SPINNER REFERENCES
            mGender = (Spinner)mView.findViewById(R.id.admin_gender);
            mPosition = (Spinner)mView.findViewById(R.id.admin_position);
            mBirthDate = (Spinner)mView.findViewById(R.id.admin_birthdate);
            mStartDate = (Spinner)mView.findViewById(R.id.admin_start_date);
            mRace = (Spinner)mView.findViewById(R.id.admin_race);

            //ARRAYADAPTER REFERENCES
            mGenderArray = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.gender_array));
            mPositionArray = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.position_array));
            mBirthDateArray = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.birth_date_array));
            mStartDateArray = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.start_date_array));
            mRaceArray = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.race_array));

            //SET SPINNER ADAPTERS
            mGender.setAdapter(mGenderArray);
            mPosition.setAdapter(mPositionArray);
            mBirthDate.setAdapter(mBirthDateArray);
            mStartDate.setAdapter(mStartDateArray);
            mRace.setAdapter(mRaceArray);

            //SET SPINNER ONCLICKLISTENERS
            //mGender.setOnItemClickListener(this);
            //mPosition.setOnItemClickListener(this);
            //mBirthDate.setOnItemClickListener(this);
            //mStartDate.setOnItemClickListener(this);
            //mRace.setOnItemClickListener(this);

        }
        return mView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
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

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        int id = view.getId();

        switch(id){
            case R.id.admin_gender:

                break;
            case R.id.admin_position:

                break;
            case R.id.admin_race:

                break;
            case R.id.admin_birthdate:

                break;
            case R.id.admin_start_date:

                break;
        }
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

    protected void setEmployeeRecyclerView(){

        mRecyclerView.setVisibility(View.VISIBLE);

        Map<String, Object> map = new HashMap<String, Object>();

        if(mGender.getSelectedItemPosition() != 0){
            int select = mGender.getSelectedItemPosition();

            switch(select){
                case 1:
                    map.put("gender", "F");
                    break;
                case 2:
                    map.put("gender", "M");
                    break;
            }
        }

        if(mRace.getSelectedItemPosition() != 0){
            int select = mRace.getSelectedItemPosition();

            switch(select){
                case 1:
                    map.put("race", "B");
                    break;
                case 2:
                    map.put("race", "W");
                    break;
                case 3:
                    map.put("race", "C");
                    break;
                case 4:
                    map.put("race", "I");
                    break;
                case 5:
                    map.put("race", "N");
                    break;
            }
        }

        if(mPosition.getSelectedItemPosition() != 0){
            int select = mPosition.getSelectedItemPosition();

            switch(select){
                case 1:
                    map.put("position", 1);
                    break;
                case 2:
                    map.put("position", 2);
                    break;
                case 3:
                    map.put("position", 3);
                    break;

            }
        }

        if(mBirthDate.getSelectedItemPosition() != 0){
            int select = mBirthDate.getSelectedItemPosition();

            switch(select){
                case 1:
                    map.put("birth_date_range", 1);
                    break;
                case 2:
                    map.put("birth_date_range", 2);
                    break;
                case 3:
                    map.put("birth_date_range", 3);
                    break;
                case 4:
                    map.put("birth_date_range", 4);
                    break;
                case 5:
                    map.put("birth_date_range", 5);
                    break;

            }
        }

        if(mStartDate.getSelectedItemPosition() != 0){
            int select = mStartDate.getSelectedItemPosition();

            switch(select){
                case 1:
                    map.put("start_date_range", 1);
                    break;
                case 2:
                    map.put("start_date_range", 2);
                    break;
                case 3:
                    map.put("start_date_range", 3);
                    break;
                case 4:
                    map.put("start_date_range", 4);
                    break;
                case 5:
                    map.put("start_date_range", 5);
                    break;

            }
        }

        if (!mEmailContains.getText().toString().isEmpty()){
            map.put("email__contains", mEmailContains.getText().toString().toLowerCase());
        }

        if (!mUser.getText().toString().isEmpty()){
            map.put("user", Integer.parseInt(mUser.getText().toString()));
        }



        Observable<List<StatisticsData>> observable = RetrofitClient.getInstance(getActivity(), true).getApiService().getEmployeesMap(map);

        observable.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<StatisticsData>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, e.getMessage());
                    }

                    @Override
                    public void onNext(List<StatisticsData> employeeData) {
                        if (!mEmployeeList.isEmpty()){
                            mEmployeeList.clear();
                        }
                        for (StatisticsData data : employeeData){
                            Employee employee = new Employee();
                            employee.setmId(data.getmUser().getId());
                            employee.setmFname(data.getmUser().getFirstName());
                            employee.setmLname(data.getmUser().getLastName());
                            employee.setmPosition(data.getmPosition().getmName());
                            employee.setmEmail(data.getmEmail());
                            employee.setmAge(data.getmAge());

                            //ADD EMPLOYEE OBJECT TO LIST FOR USE IN THE RECYCLERVIEW ADAPTER
                            mEmployeeList.add(employee);
                        }

                        mAdapter = new EmployeeRecyclerViewAdapter(getActivity(), mEmployeeList);

                        //SET RECYCLERVIEW ADAPTER
                        mRecyclerView.setAdapter(mAdapter);

                        //CLEAR VIEWS
                        clearViews();
                    }
                });
    }

    protected void clearViews(){
        mUser.setText("");
        mEmailContains.setText("");
        mGender.setSelection(0);
        mPosition.setSelection(0);
        mRace.setSelection(0);
        mBirthDate.setSelection(0);
        mStartDate.setSelection(0);
    }
}
